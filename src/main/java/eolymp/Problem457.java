package eolymp;

import java.util.*;

/**
 * Created by olehb on 27.01.17.
 */
public class Problem457 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> distances = new LinkedList<>();
        int n = in.nextInt();
        int distance;
        int min_values;
        for (int i = 0; i < n; i++) {
            switch (in.next()) {
                case "ADD":
                    Integer elem = in.nextInt(), inList;
                    for (int j = 0; j < list.size(); j++) {
                        inList = list.get(j);
                        if (!inList.equals(elem) && elem < inList && (j==0 || elem < list.get(j-1))) {
                            list.add(j,elem);
                            int left = Integer.MAX_VALUE;
                            int right = left;

                            if (j!=0) left = elem-list.get(j-1);
//                            if ()
//                            distances.add()
                            break;
                        }
                    }
//                    if (!list.contains(elem)) {
//                        for (int j = 0; j < list.size(); j++) {
//                            if (list.get(j))
//                        }
//                    }
                    break;
                case "DEL":
                    elem = in.nextInt();
                    int idx = list.indexOf(elem);
                    list.remove(elem);
                    distances.remove(idx);
                    break;
                case "FIND":
//                    System.out.println(distance);
            }
        }
    }
}
