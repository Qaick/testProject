package com.olehb.tests;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JavaLogger {
    private static Logger logger = Logger.getLogger(JavaLogger.class.getName());
//    private static Logger logger = Logger.getLogger("com.wombat.nose");
//    private static Handler fh;

//    static {
//        try {
//            fh = new ConsoleHandler();
//            fh.setLevel(Level.ALL);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

            System.getenv();
            System.out.println(System.getenv());
            // Send logger output to our FileHandler.
//            logger.addHandler(fh);
            // Request that every detail gets logged.
//            logger.setLevel(Level.ALL);
            // Log a simple INFO message.
//            logger.info("doing stuff");
//            logger.fine("done");

        Arrays.stream(logger.getHandlers()).forEach(handler -> handler.setLevel(Level.ALL));
        System.getSecurityManager();
//        logger.setLevel(Level.ALL); // no logger manager - no permission
//        System.setSecurityManager(new SecurityManager());
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.ALL);
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
        logger.config("config");
        
        logger.info("info");
        logger.warning("warning");
        logger.severe("severe");
    }
}
