package eolymp;

import java.util.Scanner;

public class Prob1614 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt(), y1 =in.nextInt(),
                x2=in.nextInt(), y2=in.nextInt(),
                x3=in.nextInt(), y3=in.nextInt();
        // translate my things to vectors
        //1-2,1-3
        //2-1 2-3
        //3-1 3-2
        double some = angle(x1,y1,x2,y2,x3,y3);
        double some2 = angle(x2,y2,x1,y1,x3,y3);
        double some3 = angle(x3,y3,x1,y1,x2,y2);
        if (some2>some) some=some2;
        if (some3>some) some=some3;
        System.out.println(String.format("%1$.6f",some));
    }

    static double angle(int w1,int w2,int w3, int w4,int w5,int w6){
        int x1=w3-w1,y1=w4-w2,
        x2=w5-w1,y2=w6-w2;
        double mult = x1*x2+y1*y2;
        double lmult = Math.sqrt(x1*x1+y1*y1)*Math.sqrt(x2*x2+y2*y2);
        double some = Math.toDegrees(Math.acos(mult/lmult));
        return some;
    }
}
