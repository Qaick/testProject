package archive_06_06_2017;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import org.bytedeco.javacv.*;
//import org.bytedeco.javacpp.avutil;
//import org.bytedeco.javacv.Frame;

public class FrameGrabGrab {

//    public static void main(String[] args) throws FrameGrabber.Exception {
//        System.out.println("Hello ffmpeg");
////        grabFrames("C:/Users/bovo/Desktop/test/video.mp4");
//        grabFrames("video.mp4");
//    }
//
//    static int cover_number = 3;
//
//    // ffmpeg -i input.flv -vf fps=1 out%d.png
//    // %04d
//    private static List<File> grabFrames(String videoPath) throws FrameGrabber.Exception {
//
//        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoPath);
//
//        frameGrabber.start();
//
//        int frames = frameGrabber.getLengthInFrames();
//        int forOneCover = frames / cover_number;
//        Random rand = new Random();
//        List<File> frameFiles = new ArrayList<>();
//        try {
//            int frameNumber = frameGrabber.getLengthInFrames();
//            for (int j = 0; j < cover_number; j++) {
//                int idx = j * frameNumber / cover_number;
//                frameGrabber.setFrameNumber(idx == 0 ? 1 : idx);
//                Frame f = frameGrabber.grab();
//                Object o = f.createIndexer().array();
//                final BufferedImage image = new Java2DFrameConverter().getBufferedImage(f);
//                File file = File.createTempFile("Image", ".png");
//                ImageIO.write(image, "png", file);
//                frameFiles.add(file);
//            }
//            for (int ii = 0; ii < cover_number; ii++) {
//                int coverIndex = ii * forOneCover + rand.nextInt(forOneCover);
//                // just in case that not gets over
//                while (coverIndex >= frames) coverIndex--;
//            }
//            frameGrabber.stop();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return frameFiles;
//    }


}
