package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by olehb on 03.02.17.
 */
public class AntsAlgorithm {
    Random rand = new Random();
    int n;
    double alfa = 1, beta = 2; // alfa for tau, beta for range
    int[][] transTable; // transitions table, including range(0 - unreachable) L
    double[][] tau;
    int[] walked;
    double sumL;

    AntsAlgorithm(int n){
        this.n = n;
        walked = new int[n];
    }
//    double probability(int i, int j) {
//        double sum=0;
//        for (int k = 0; k < n; k++) {
//            sum+=Math.pow(transTable[i][k], beta)*Math.pow(tau[i][k], alfa);
//        }
//        return 100*()/sum;
//    }

    int getNext(int i) {
        double[] probabilities = new double[n];
        double sum = 0;
        for (int j = 0; j < n; j++) {
            if (walked[j] == 0) probabilities[j] = Math.pow(1.0/transTable[i][j], beta) * Math.pow(tau[i][j], alfa);
            sum += probabilities[j];
        }

        for (int j = 0; j < n; j++) {
//            System.out.println("pro:"+probabilities[j]+"sum:"+sum);
            probabilities[j] = probabilities[j] / sum;
        }
        double go = rand.nextDouble();
        double way = 0;
        int ans=0;
//        System.out.println("go "+go);
        for (int j = 0; j < n; j++) {
            way += probabilities[j];
//            System.out.println("prob: "+j+" "+probabilities[j]);
            if (go <= way){
                ans = j;
                break;
            }
        }
        return ans;
    }

    String getWay(){
        StringBuilder path = new StringBuilder();
        int[] ipath = new int[5];
        int prev = 0;
        path.append(prev+" ");
        walked[prev] = 1;
        int trip=0;
        ipath[0]=prev;
        for (int i = 1; i < n; i++) {
            int next = getNext(prev);
            ipath[i]=next;
            trip+=transTable[prev][next];
            prev = next;
            walked[prev] = 1;
            path.append(prev+" ");
        }
        // reset reached vertexes
        walked = new int[n];
        // calculate feromone from this ant
        System.out.println("trip "+trip);
        double deltatau = 175.0/trip;
        for (int i = 0; i < n-1; i++) {
            tau[ipath[i]][ipath[i+1]] +=deltatau;
            tau[ipath[i+1]][ipath[i]] +=deltatau;
        }
        return path.toString();
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int n = 5;
        double[][] tau = new double[n][n];
        int[][] rang = new int[n][n];
        rang[0][1] = 38;
        rang[0][2] = 74;
        rang[0][3] = 59;
        rang[0][4] = 45;
        rang[4][1] = 72;
        rang[4][2] = 85;
        rang[4][3] = 42;
        rang[3][1] = 61;
        rang[3][2] = 49;
        rang[2][1] = 46;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rang[i][j] > 0) {
                    rang[j][i] = rang[i][j];
//                    tau[i][j] = rand.nextInt(10);
                    tau[i][j] = 1;
                    tau[j][i] = tau[i][j];
                }
//                System.out.print(tau[i][j]+" ");
            }
//            System.out.println();
        }
        AntsAlgorithm aa = new AntsAlgorithm(n);
        aa.transTable = rang;
        aa.tau = tau;
        ArrayList<String> etc = new ArrayList<>();
        int[] arr = new int[100];
        for (int i = 0; i < 1000; i++) {
            String s = aa.getWay();
            if (!etc.contains(s)) etc.add(s);
            else {
                arr[etc.indexOf(s)]++;
            }
            System.out.println(s);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tau[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(Arrays.toString(arr));
    }

    public static int perebor() {
        return 0;
    }
}
