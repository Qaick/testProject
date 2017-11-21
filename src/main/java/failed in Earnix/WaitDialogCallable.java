import com.earnix.eo.client.Msg;
import com.earnix.eo.client.util.ClientUtils;
import com.earnix.eo.client.util.EOCallable;
import com.earnix.eo.dataaccess.ConfigurationExchange;
import com.earnix.eo.dataaccess.operations.CancelProgressHelperOperation;
import com.earnix.eo.gui.common.EOCursorUtils;
import com.earnix.eo.gui.common.EOPanel;
import com.earnix.eo.gui.dialog.ConfirmDialog;
import com.earnix.eo.gui.dialog.EODialog;
import com.earnix.eo.gui.errorreporting.EOExceptionHandler;
import com.earnix.eo.gui.taskbar.TaskbarProgressBarBuilder;
import com.earnix.eo.gui.util.button.DialogButton;
import com.earnix.eo.gui.util.button.DialogButtonsPanel;
import com.earnix.eo.gui.util.layout.StackLayout;
import com.earnix.utils.*;
import com.earnix.utils.progress.ProgressHelperImpl.ProgressData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Note: The EORunnable job will be executed in its own thread, so it CANNOT have GUI code inside.
 * Note: If the EORunnable job finishes before <code>PRE_SHOW_DELAY</code> msec, the dialog will not be shown.  
 * @author Eli Rosenblum
 */
@SuppressWarnings("serial")
public class WaitDialogCallable<V> extends EODialog
{
	/**
	 * Label that may only increase its preferred width, not to decrease it. Needed to show time values, which may grow
	 * in width over time, but we don't want the label to shrink event when time values get shorter (e.g. 1min 59sec ->
	 * 2min) since it creates unpleasant visual effect.
	 */
	private static class TimeLabel extends EOLabel
	{
		private int maxWidth = 0;

		TimeLabel()
		{
			super();
		}

		@Override
		public Dimension getPreferredSize()
		{
			//no width reduction:
			Dimension s = super.getPreferredSize();
			if (s.width > maxWidth)
			{
				maxWidth = s.width;
			}
			return new Dimension(maxWidth, s.height);
		}
	}

	public static final String WAIT_MSG = "Please wait. The requested operation is in progress...";
	public static final long PRE_SHOW_DELAY = 500; //msec

	private Timer elapsedTimer;
	private final Object lock = new Object();
	private final AtomicBoolean isJobCompleted = new AtomicBoolean(false);
	private final AtomicBoolean isJobCancelled = new AtomicBoolean(false);
	//
	private EOException eoException;
	private RuntimeException runtimeException;

	/** contains additional info message (stage of the operation) */
	private EOLabel additionalInfoLabel = new EOLabel(" ");

	private final ComputationProgress computationProgress = new ComputationProgress();

	private Thread jobThread;

//	public WaitDialogCallable(JDialog owner, EORunnable job, String msg, WaitDialogProgressReporter progressReporter)
//	{
//		this(owner, job, msg, null, progressReporter, false);
//	}

	public WaitDialogCallable(JDialog owner, EOCallable job, String msg, Icon icon,
			WaitDialogProgressReporter progressReporter, boolean showCancelButton)
	{
		super(owner);
		init(job, msg, icon, progressReporter, showCancelButton);
	}

	public WaitDialogCallable(JFrame owner, EOCallable job, String msg, Icon icon,
			WaitDialogProgressReporter progressReporter, boolean showCancelButton)
	{
		super(owner);
		init(job, msg, icon, progressReporter, showCancelButton);
	}

//	public WaitDialogCallable(JFrame owner, EORunnable job, String msg, WaitDialogProgressReporter progressReporter)
//	{
//		this(owner, job, msg, null, progressReporter, false);
//	}

	/**
	 * @return false if exception was thrown during job execution or the job was cancelled by a user, true o/w
	 */
	public boolean isJobCompleted()
	{
		return isJobCompleted.get();
	}

	public boolean isJobCancelled()
	{
		return isJobCancelled.get();
	}

	@Override
	protected int getDialogDefaultCloseOperation()
	{
		return DO_NOTHING_ON_CLOSE;
	}

	private V v;
	
	private void init(final EOCallable job, String msg, Icon icon, final WaitDialogProgressReporter progressReporter,
			boolean showCancelButton)
	{
		Debug.assertEO(job);

		ExecutorService exec = Executors.newCachedThreadPool();

		Debug.assertEO(msg);
		//start the job as soon as possible:



		final Future<V> ret = exec.submit(job);
		// TODO blocking until gets
		
		jobThread = new Thread()
		{
			@Override
			public void run()
			{
				// use synchronization to ensure memory consistency ('happens-before' relationship)
				// between this thread and EDT thread:
				synchronized (lock)
				{
					try
					{
						try
						{
							v = ret.get();
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						catch (ExecutionException e)
						{
							e.printStackTrace();
						}
					}
//					catch (OperationCancelledException ex)
//					{
//						isJobCancelled.set(true);
//					}
//					catch (EOException ex)
//					{
//						setEOException(ex);
//					}
					catch (Throwable ex)
					{
						Debug.trace(ex);
						setRuntimeException(new RuntimeException(EOExceptionHandler.ERROR_MSG, ex));
					}
					isJobCompleted.set(!isJobCancelled.get() && (getRuntimeException() == null)
							&& (getException() == null));
				}
				//
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						// use synchronization to ensure memory consistency ('happens-before' relationship)
						// between job thread and EDT thread:
						synchronized (lock)
						{
							if (isShowing())
							{
								elapsedTimer.stop();
								TaskbarProgressBarBuilder.getInstance().stopProgress();
								//
								dispose();
							}
						}
					}
				});
			}
		};
		jobThread.setPriority(Thread.MIN_PRIORITY);
		jobThread.start();
		//
		getContentPane().setLayout(new BorderLayout());
		EOPanel mainPanel = new EOPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(3, 0, 3, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		setTitle(Msg.getStr("Earnix_Optimizer"));
		setModal(true);
		setResizable(false);
		setCursor(EOCursorUtils.WAIT_CURSOR);
		//
		EOPanel textPanel = new EOPanel(new StackLayout());
		textPanel.setBorder(new EmptyBorder(5, 5, 5, 10));
		textPanel.add(new EOLabel(msg));
		textPanel.add(additionalInfoLabel);
		//
		final EOLabel timeLabel = new TimeLabel();
		timeLabel.setText(Msg.getStr("Elapsed_Time_T", DateUtils.formatTimePeriod(0)));
		//
		final long startTime = System.currentTimeMillis();
		textPanel.add(timeLabel);
		//
		final EOLabel remainingTimeLabel=new TimeLabel();
		final EOLabel progressLabel = new EOLabel();
		final EOProgressBar progressBar = new EOProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		if (progressReporter != null && progressReporter.showProgress())
		{
			if (progressReporter.showRemainingTime())
				textPanel.add(remainingTimeLabel);
			textPanel.add(progressLabel);
			EOPanel p = new EOPanel(new BorderLayout());
			p.add(progressBar, BorderLayout.CENTER);
			p.setBorder(new EmptyBorder(3, 5, 4, 5));
			mainPanel.add(p, BorderLayout.SOUTH);
		}
		//
		elapsedTimer = new Timer(ConfigurationExchange.PING_INTERVAL_MILLISECONDS, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				long currentTime = System.currentTimeMillis();
				double progress = 0.0;
				boolean lastUpdateTimeInitialized = (computationProgress.getLastUpdateTime() > 0);
				long newLastUpdateTime = lastUpdateTimeInitialized ? computationProgress.getLastUpdateTime()
						: startTime;
				if (progressReporter != null)
				{
					// may be null if the query is already ended/not yet started.
					ProgressData progressData = progressReporter.getProgress();
					if (progressData != null)
					{
						progress = progressData.getProportionCompleted();
						// additional message
						final MessageEntry currentStage = progressData.getCurrentStage();
						String progressMsg = currentStage != null ? currentStage.getText(getLocale()) : " ";
						if (progress > 1.0 || progress < 0.0)
						{
							Debug.trace("Invalid progress: " + progress + " " + progressMsg);
						}
						additionalInfoLabel.setText(progressMsg);
						TaskbarProgressBarBuilder.getInstance().setProgress(progress);
						// last update time
						if (computationProgress.getProgress() != progress)
							newLastUpdateTime = currentTime;
					}
				}
				computationProgress.setState(startTime, currentTime, newLastUpdateTime, progress);
				computationProgress.setupProgressComponents(progressBar, progressLabel, remainingTimeLabel, timeLabel);
				// [BUG FIX]: resize because the new time may not fit in the old label size:
				// perform pack only if resize needed
				if (getWidth() < getPreferredSize().getWidth())
				{
					pack();
				}
			}
		});
		//
		mainPanel.add(textPanel, BorderLayout.CENTER);
		//
		JLabel iconLabel = new JLabel();
		iconLabel.setBorder(new EmptyBorder(5, 15, 5, 15));
		if (icon != null)
			iconLabel.setIcon(icon);
		mainPanel.add(iconLabel, BorderLayout.WEST);
		// buttons panel
		if (showCancelButton)
		{
			EOPanel cancelPanel = new EOPanel(new EmptyBorder(5, 0, 10, 10), new BorderLayout());
			final DialogButton cancelButton = DialogButtonsPanel.createCancelButton();
			cancelPanel.add(cancelButton, BorderLayout.EAST);
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// ask for confirmation
					if (!progressReporter.isCancelShouldBeConfirmed()
							|| ConfirmDialog.showConfirmDialog(WaitDialogCallable.this, Msg.getStr("WaitDialog.ConfirmCancel")))
					{
						cancelButton.setEnabled(false);
						cancelButton.revalidate();
						cancelButton.repaint();
						//
						SwingUtilities.invokeLater(new Runnable()
						{
							@Override
							public void run()
							{
								jobThread.interrupt(); // cancel local activity (if any)

								Runnable customCancel = progressReporter.getCustomCancelOperation();
								if (customCancel != null)
								{
									customCancel.run();
								}
								else
								{
									// cancel the operation on server:
									Boolean cancelAcknowledged = ClientUtils
											.executeRemoteOperation2(new CancelProgressHelperOperation(progressReporter
													.getProgressID()));
									// email from Amit Amely (30 Dec 2015):
									// For the rare case where cancel is clicked too soon, before the main operation object is fully delivered to the server, the cancel operation 
									// will return false – meaning that no progress helper is running with the given ID.
									// In any other case the returned value will be true.
									if (Boolean.FALSE.equals(cancelAcknowledged))
									{
										// re-enable cancel:
										cancelButton.setEnabled(true);
										cancelButton.revalidate();
										cancelButton.repaint();
									}
								}
							}
						});
					}
				}
			});
			getContentPane().add(cancelPanel, BorderLayout.SOUTH);
		}
		//
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowOpened(WindowEvent e)
			{
				elapsedTimer.start();
			}
		});
	}

	@Override
	public void show()
	{
		// stop EDT to give chance to the job thread to finish before we open
		// the dialog (to skip dialog opening for short jobs).
		ClientUtils.threadSleep(PRE_SHOW_DELAY);
		//
		if (jobThread.isAlive())
		{
			autoLocation = false;
			autoPackIfUnresizable = false;
			pack(); // need this to have the correct dialog size values
			// set the width to be 150% of the preferred width
			setSize((int) (getWidth() * 1.5), getHeight());
			GUIUtils.centralizeOnScreen(this);
			super.show();
		}
		//
		if (getRuntimeException() != null)
		{
			throw getRuntimeException();
		}
	}

	/**
	 * Perform time-consuming operation in modal dialog with progress/cancel support.<br>
	 *
	 * @param win Dialog's owner window.
	 * @param runnable Operation to execute. 
	 * @param _progressReporter Operation progress reporter (optional).  
	 * @param _icon Job specific icon (optional). 
	 * @param _showCancelButton
	 * @return {@code true} if the {@code runnable} finished its job, {@code false} if it was canceled by the user. 
	 * @throws EOException thrown by the {@code runnable} during execution.
	 * @throws RuntimeException Wrapping non-{@code EOException} thrown by the {@code runnable} during execution.
	 */
	public static <V> V doInWaitDialog(Window win, EOCallable runnable, WaitDialogProgressReporter _progressReporter,
			Icon _icon, boolean _showCancelButton) throws EOException
	{
		Debug.assertEO(runnable);
		//
		WaitDialogCallable<V> d;
		if (win == null)
		{
			win = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
		}
		if (win instanceof JFrame)
			d = new WaitDialogCallable((JFrame) win, runnable, WAIT_MSG, _icon, _progressReporter, _showCancelButton);
		else
			d = new WaitDialogCallable((JDialog) win, runnable, WAIT_MSG, _icon, _progressReporter, _showCancelButton);
		d.show();
		if (d.getException() != null)
		{
			throw d.getException();
		}
		return d.v;
//		return d.isJobCompleted();
	}

	public static <V> V doInWaitDialog(Window win, EOCallable runnable) throws EOException
	{
		return doInWaitDialog(win, runnable, true);
	}

	public static <V> V doInWaitDialog(Window win, EOCallable runnable, boolean showCancelButton) throws EOException
	{
		return doInWaitDialog(win, runnable, new DefaultWaitDialogProgressReporter(runnable.getProgressID()), null,
				showCancelButton);
	}

	/**
	 * Note: this method should be called only after the dialog was disposed (so lock synchronization 
	 * was performed).
	 * @return EOException that was thrown during the job execution.
	 */
	public EOException getException()
	{
		return eoException;
	}

	private void setEOException(EOException ex)
	{
		this.eoException = ex;
	}

	private RuntimeException getRuntimeException()
	{
		return runtimeException;
	}

	private void setRuntimeException(RuntimeException ex)
	{
		this.runtimeException = ex;
	}
}