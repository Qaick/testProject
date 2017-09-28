package eolymp;

import java.util.Scanner;

public class Prob2809 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), x = in.nextInt();
        int a=1,b=-1;
        int[] arr = new int[n];
        do {
            b++;
            if(b>a){
                b=0;
                a++;
            }
            arr[0]=a;
            arr[1]=b;
            for (int i = 2; i < n; i++) {
                arr[i]=arr[i-1]+arr[i-2];
            }
        } while (arr[n-1] != x);
        System.out.println(a+" "+b);
    }
}
