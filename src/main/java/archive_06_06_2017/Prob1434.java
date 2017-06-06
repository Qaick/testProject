package archive_06_06_2017;

import java.util.Scanner;

public class Prob1434 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long k = in.nextLong(), summ = 1;
        // max triangles = 10^12 - 22893 last element
        int idx=1, arr[] = new int[22894];

        arr[1]=1;
        while (k>summ) {
            arr[++idx] = arr[idx-2]+idx;
            summ+=arr[idx];
        }
        System.out.println(idx);
    }
}
