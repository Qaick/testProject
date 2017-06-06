package archive_06_06_2017;

import java.util.Scanner;

public class Prob1214 {
    static int k;
    static long eighteen = (long)1e18;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a=4000000000000000000L,b=2;
        System.out.println(a*b);
        long n=in.nextInt();
        k=in.nextInt();
        final long fact = fact(n);
        System.out.println(fact <=0||fact>eighteen?"overflow": fact);
    }
    static long fact(long n) {
        if (k>=n) return n;
        return n*fact(n-k);
    }
}
