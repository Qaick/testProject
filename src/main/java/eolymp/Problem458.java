package eolymp;

import java.util.Scanner;

/**
 * Created by olehb on 27.01.17.
 */
public class Problem458 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt(), h = in.nextInt();
        String[] img = new String[h*2];
        for (int i = 0; i < h*2; i++) {
            img[i] = in.next();
        }
        String template = in.next();
        StringBuilder sb = new StringBuilder();
        String vagina="";
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                vagina = ""+img[i].charAt(j)+img[h+i].charAt(j);
                switch (vagina) {
                    case "00":
                        sb.append(template.charAt(0));
                        break;
                    case "01":
                        sb.append(template.charAt(1));
                        break;
                    case "10":
                        sb.append(template.charAt(2));
                        break;
                    case "11":
                        sb.append(template.charAt(3));
                        break;

                }
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
