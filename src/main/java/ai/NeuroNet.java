package ai;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by olehb on 04.02.17.
 */
public class NeuroNet {
    Random rand = new Random();
    double[] koef = {0.25, 0.25, 0.25, 0.25, 0.25, 0.25, 0.25, 0.25};
    // learn how to control input
    // if 1 i3 goes(+0.01) 1 in some time
    // if 0 i3 goes(-0.01) 0 in some time

    private int next(double[] input) {
        double[] arr = new double[8];
        System.arraycopy(input, 0,arr,0,arr.length);
        if (input[3] <= 0) {
            input[3] = 0;
            input[7] = 1;
        }
        else input[3] = 1;
        
        double output = 0;
        double sum = 0;
        for (int i = 0; i < koef.length; i++) {
            output+=koef[i] * arr[i];
            sum+=koef[i];
        }
        if (output/sum < rand.nextDouble()) {
            
        }
    }


    public static void main(String[] args) {
        // 3 dimensional net
//        double[][][] net = {
//                {{1,0},{1,2}},
//                {{1,2},{1,2}},
//                {{1,2},{1,2}}
//        };
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        NeuroNet net = new NeuroNet();

        double[] observations = {1, 1, 1, 1};// they are alot more complicated. But for now 1 greater than zero 0 - lower
        while (true) {
            observations = game.play(net.next(observations));
            if (game.isDone()) {
                net.gameOver();
                game = new Game();
                in.nextLine();
            }
        }
    }


    static class Game {
        double[] arr = {0, 0, 0, 1};

        public boolean isDone() {
            if (arr[3] >= 1 || arr[3] <= -1) return true;
            else return false;
        }

        double[] play(int i) {
            assert (i == 1 || i == 0);
            if (i == 1) {
//                arr[0]+=-0.01;
//                arr[1]+=0.01;
//                arr[2]+=0.2;
                arr[3] += -0.1;
            } else if (i == 0) {
//                arr[0]+=0.01;
//                arr[1]+=-0.01;
//                arr[2]+=-0.2;
                arr[3] += 0.1;
            }
            return arr;
        }
    }
}
