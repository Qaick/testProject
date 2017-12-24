package eolymp;

import java.io.FileInputStream;
import java.util.Scanner;

public class Prob1472 {
    
    static int[][] map;
    static int[][] air;
            
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("src/main/java/eolymp/Test1472");
        Scanner in = new Scanner(fis);
        while (in.hasNext()) {
            map = new int[4][4];
            String s = in.next() + in.next() + in.next() + in.next();
            int i = 0, steps = 0;
            int sy = -1, sx = -1, ex = -1, ey = -1; // start, end
            for (char c : s.toCharArray()) {
                switch (c) {
                    case '#':
                        // stone
                        map[i / 4][i % 4] = 1;
                        break;
                    case '.':
                        // empty room

                        break;
                    case 'S':
                        // start
                        map[i / 4][i % 4] = 2;
                        sy = i / 4;
                        sx = i % 4;
                        break;
                    case 'E':
                        // end
                        map[i / 4][i % 4] = 3;
                        ey = i / 4;
                        ex = i % 4;
                        break;
                }
                i++;
            }
            // is my boy trapped?
            if ((sy <= 0 || map[sy - 1][sx] == 1)
                    && (sy >= 3 || map[sy + 1][sx] == 1)
                    && (sx <= 0 || map[sy][sx - 1] == 1)
                    && (sx >= 3 || map[sy][sx + 1] == 1)) {
                System.out.println("Trapped!");
            } else {
                // route
                // 2 ways to the end
                air = new int[4][4];
                
                
                while (ex != sx || ey != sy) {
                    if (ex - sx == 0) {
                    } else if (ex - sx > 0) {
                        for (int j = sx; j < ex; j++) {
                            
                        }
                        sx++;
                    } else {
                        sx--;
                    }
                    if (ey - sy == 0) {
                    } else if (ey - sy > 0) {
                        sy++;
                    } else {
                        sy--;
                    }
                }
                while (ex != sx || ey != sy) {
                    if (ex - sx == 0) {
                        steps--;
                    } else if (ex - sx > 0) {
                        sx++;
                    } else {
                        sx--;
                    }
                    steps++;
                    if (ey - sy == 0) {
                        steps--;
                    } else if (ey - sy > 0) {
                        sy++;
                    } else {
                        sy--;
                    }
                    steps++;
                }
                System.out.format("Escaped in %d minute(s).\n", steps);
            }
            in.next(); // empty line
        }
    }

    /**
     * Throwing rockets in the space oddity. Air isn't so empty anymore.
     * Should be 2 cases.
     */
    void magic(int x1, int y1, int x2, int y2) {
        if (x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (y1 > y2) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        // now lower is for sure lower value
        // 1...
        // ....
        // ...2
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                
            }
        }
//        map[x1][]
    }
}
