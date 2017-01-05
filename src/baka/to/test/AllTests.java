package baka.to.test;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class AllTests
{
    public static void main(String[] args)
    {
        testSynchronizedMethod();
    }
    
    static void testSynchronizedMethod() {
        class SynchronizedCounter {
            private int c = 0;
            public synchronized void increment() {
                System.out.println("inc1 in");
                c++;
            }
            public synchronized void decrement() {
                c--;
            }
            public synchronized int value() {
                return c;
            }
        }
        SynchronizedCounter sc = new SynchronizedCounter();
        Thread test = new Thread(() ->{
            sc.increment();
            System.out.println("inc1 done");
        });
        Thread test2 = new Thread(() ->{
            sc.decrement();
            System.out.println("inc2 done");
        });
        test.start();
        test2.start();
    }

    static void testSingleton()
    {

    }

    static void testRAM()
    {
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
                .getTotalPhysicalMemorySize();
        System.out.println(memorySize / 1024 / 1024);//k m g
    }

    static void testBytecodeStringConcatenation()
    {
        ArrayList<Integer> al;
        B.k();
        String s = "baka.to.test.MyPanel" + " " + "hello";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++)
        {
            sb.append("baka.to.test.MyPanel " + "b" + 2344 + s);
        }
    }

    public static void newMethod() throws RuntimeException
    {//CAN'T make someone to process RuntimeException
        System.out.println("hehe");
    }

    static void testError()
    {
        if (true)
            throw new Error();
        System.out.println("hello");

    }

    volatile boolean working = true;

    static void testNotify()
    {
        final Object syncObj = new Object();
        final AllTests at = new AllTests();

        Thread test = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (syncObj)
                {
                    try
                    {
                        System.out.println("thread: " + Thread.currentThread().getName());
                        System.out.println("wait");
//                        Thread.sleep(300);
                        if (at.working)
                            syncObj.wait();
                        System.out.println("wait end");
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        test.start();
        try
        {
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        synchronized (syncObj)
        {
            System.out.println("thread: " + Thread.currentThread().getName());
            System.out.println("notif");
            test.interrupt();
            try
            {
                Thread.sleep(300);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            at.working = false;
            syncObj.notify();
            System.out.println("notif end");
        }
    }

    static void tryCatchFinally()
    {
        try
        {
            int zero = 0;
            int her = 1 / zero;
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("finally");
        }
    }
    //powerless class
    //        class Kill {
    //            static int a;
    //        }

    abstract class Alo
    {

        int x;

        Alo(int x)
        {
            this.x = x;
        }
    }

    Alo getAlo()
    {
        return new Alo(1)
        {
        };
    }

    private static class A
    {
        String s = "paramount pictures";

        A()
        {

        }
    }

    static A getA()
    {
        return new A();
    }
    static class B
    {
        static void k()
        {
            System.out.println(AllTests.getA());
        }
    }
    static class ThreadA extends Thread {
        static ThreadB b;
        public static void main(String[] args){
            b = new ThreadB();
            b.start();
            Thread c = new ThreadA();
            c.start();
            synchronized(b){
                try{
                    System.out.println("Waiting for b to complete...");
                    b.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("Total is: " + b.total);
            }
        }
        @Override
        public void run() {
            synchronized(b){
                try{
                    System.out.println("threadA");
                    b.wait();
                    System.out.println("haha");
                    sleep(900);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                

                System.out.println("ATotal is: " + b.total);
            }
        }
    }
    static class ThreadB extends Thread{
    int total;
    @Override
    public synchronized void run(){
            for(int i=0; i<100 ; i++){
                total += i;
            }
        System.out.println("calculated");
            notifyAll();
    }
}
}
