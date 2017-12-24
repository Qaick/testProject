package eolymp;

import java.math.BigInteger;
import java.util.Scanner;

public class Prob116 {
    static BigInteger c;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(),b=a;
        c = BigInteger.valueOf(in.nextInt());
        BigInteger bi = BigInteger.valueOf(a);
        while (!bi.mod(c).equals(BigInteger.ZERO)) {
            b++;
            bi = bi.multiply(BigInteger.valueOf((int)Math.pow(10, length(b)))).add(BigInteger.valueOf(b));
        }
        System.out.println(b);
    }

    static int length(int i){
        int cnt=0;
        while(i>0) {
            i /= 10;
            cnt++;
        }
        return cnt;
    }
}