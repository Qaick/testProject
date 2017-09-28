package codility;

import java.io.*;
import java.util.*;

public class SafeNetwork {
    public static void main(String[] args) throws Exception {
        final String SAFE = "Safe";
        final String UNSAFE = "Unsafe";

        BufferedReader br = new BufferedReader(new FileReader("graphs.txt"));

        String[] inputNumbers = br.readLine().split(" ");
        int computers = Integer.parseInt(inputNumbers[0]);
        int connections = Integer.parseInt(inputNumbers[1]);
        int[][] graph = new int[computers][computers]; // adjacency matrix - матрица смежности
        int v0, v1;
        for (int i = 0; i < connections; i++) {
            inputNumbers = br.readLine().split(" ");
            v0 = Integer.parseInt(inputNumbers[0]);
            v1 = Integer.parseInt(inputNumbers[1]);
            graph[v0][v1] = 1;
            graph[v1][v0] = 1;
        }

        int[][] graphCopy = new int[computers][computers];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, graphCopy[i], 0, computers);
        }

    }
}
