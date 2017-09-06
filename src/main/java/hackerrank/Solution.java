package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        interquartileRange();
    }

    static void interquartileRange() {

    }

    // отклонение
    static void standardDeviation() {

    }

    static void quartiles() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            reader.readLine();
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[] arr = Arrays.stream(input.split(" ")).mapToDouble(Double::valueOf).toArray();
        Arrays.sort(arr);

        double q1, q2, q3;
        int q1e, //size of first part, and the second
                q3s;
        if (arr.length % 2 == 1) {
            q1e = arr.length / 2;
            q3s = arr.length / 2 + 1;
            q2 = arr[arr.length / 2];
        } else {
            q1e = arr.length / 2;
            q3s = arr.length / 2;
            q2 = (arr[arr.length / 2 - 1] + arr[arr.length / 2]) / 2;
        }
        if (q1e % 2 == 1) {
            q1 = arr[q1e/2];
            q3 = arr[q1e/2+q3s];
        } else {
            q1 = (arr[q1e / 2 - 1] + arr[q1e / 2]) / 2;
            q3 = (arr[q1e / 2 - 1+q3s] + arr[q1e / 2+q3s]) / 2;
        }

        System.out.printf("%.0f\n%.0f\n%.0f", q1, q2, q3);
    }

    static void meanMedianMode() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            reader.readLine();
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[] arr = Arrays.stream(input.split(" ")).mapToDouble(Double::valueOf).toArray();

        double sum = 0;
        // mean - the average value of all (x1+x2+x3)/3
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        double mean = sum / arr.length;
        // median - middle of sorted array (if 2 take the average value)
        Arrays.sort(arr);
        double median = arr.length % 2 == 1 ? arr[arr.length / 2] : (arr[arr.length / 2 - 1] + arr[arr.length / 2]) / 2;
        // mode - value that occurs most (if many take smallest)
        double mode = 0, maxOccurs = 0, now = 0, nowOccurs = 0;
        for (double element : arr) {
            if (element == now) {
                nowOccurs++;
            } else {
                now = element;
                nowOccurs = 1;
            }
            if (nowOccurs > maxOccurs) {
                maxOccurs = nowOccurs;
                mode = now;
            }
        }
        System.out.printf("%.1f\n%.1f\n%.0f", mean, median, mode);
    }
}
