package eolymp;

import java.util.Scanner;

public class Prob5104_TODO {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n=in.nextInt(),m=in.nextInt(), ans=1;
        if (n==1||m==1){
            ans=1;
        }
        else if (n==2||m==2){
            if (m<n){
                int tmp = m;
                m=n;
                n=tmp;
            }
            //n==2
            ans=m;
        }
        else{
            if (m<n){
                int tmp = m;
                m=n;
                n=tmp;
            }
            // n < m
            int[][] arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                arr[i][0]=1;
            }
            for (int i = 0; i < m; i++) {
                arr[0][i]=1;
            }
            for (int i = 1; i < n-1; i++) {
                for (int j = 1; j < m; j++) {
                    arr[i][j] = arr[i-1][j]+arr[i][j-1];
                }
            }
            for (int i = 1; i < m - 1; i++) {
                ans+=arr[n-2][i];
            }
//            ans = arr[n-2][m-1];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.print(arr[i][j]+" ");
//                }
//                System.out.println();
//            }
        }
        System.out.println(ans);
    }
}
