package com.olehb.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Logger_slf4j_etc {
    static final Logger log = LoggerFactory.getLogger(Logger_slf4j_etc.class);
    public static void main(String[] args) {
        double costA, costB;
        
        System.setProperty("testproper", "");
        System.out.println(System.getProperty("testproper","default"));
        
        log.debug("debug");
        log.trace("trace");
        
        log.info("info");
        log.error("error");
        log.warn("warn");
        
        
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
            
        }
        
        System.out.println(1/0.0);
        System.out.println(1.0/0);
        System.out.println(1/0);
        int k = 5;
        double lam = 2.5;
        double ans = Math.pow(lam, k)*Math.pow(2.71828, -lam)/fact(k);
        
        System.out.printf("%.3f\n", ans);
    }

    static double comb(double n, double x) {
        return fact(n) / (fact(x) * fact(n - x));
    }

    static double fact(double i) {
        return i < 2 ? 1 : i*fact(i-1);
    }
}
