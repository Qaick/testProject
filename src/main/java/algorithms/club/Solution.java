package algorithms.club;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x[] = new int[n+1], y[] = new int[n+1];
        for (int i = 0; i < n; i++)
        {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int x0=in.nextInt(),y0=in.nextInt();
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            if (intersect(x0,y0,x[i],y[i],x[i+1],y[i+1])){
                count++;
            }
        }
        System.out.println(count%2>0?"Inside":"Outside");
    }

    private static boolean intersect(int x0, int y0, int x1, int y1, int x2, int y2)
    {
        if (y1==y2) return false;
        if (y2<y1) {
            int tmp = y1;
            y1=y2;
            y2=tmp;
            tmp = x1;
            x1=x2;
            x2=tmp;
        }
        if (y0==y1) return false;
        if (y2==y2) return x0 <=x2;
        return true;
        
    }
}