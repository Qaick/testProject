import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 10k time: 619
 */
@SuppressWarnings("Duplicates")
public class MakeMagic {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        int elem=0, linesCount=0, counter = 1;
        Integer state;
        int[] system;
        ArrayList<Integer> questions = new ArrayList<>();
        String[] strLines = new String[0];
        int[] linesRefs = new int[0];
        //        int[][] intLines = new int[0][0];
        int[] calls = new int[0];
        List<int[]> snapshots = null;
        int root1, root2;
        String[] ss;
        StringBuilder answer = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            ss = bufferedReader.readLine().split("\\s");
            elem = Integer.valueOf(ss[0]);
            linesCount = Integer.valueOf(ss[1]);
            strLines = new String[linesCount+2];
            linesRefs = new int[linesCount+1];
            //            intLines =
            calls = new int[linesCount+1];
            calls[0] = linesCount+1;//like 0 state is forever in memory
            snapshots = new ArrayList<>(linesCount + 1);
            snapshots.add(new int[elem + 1]);//0 snapshot
            while ((strLines[counter] = bufferedReader.readLine()) != null) {
                if (strLines[counter].charAt(0) == '?') {
                    state = Integer.valueOf(strLines[counter].split("\\s")[1]);
                    questions.add(state);
                    calls[counter] = 1;
                }
                linesRefs[counter] = Integer.valueOf(strLines[counter].split("\\s")[1]);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //make recursive from questions calls and add in call array
        for (int integer: questions) {
            while (linesRefs[integer] != 0 && calls[integer] == 0) {//
                calls[integer]++;
                integer = linesRefs[integer];
            }
            calls[integer]++;
        }//perfect calls created! Congratulations!

        //go as usual algorithm but with optimization of memory. e.g. after call(=0) of snapshot make it null
        for (int i = 1; i <= linesCount; i++) {
            if (calls[i] != 0) {
                ss = strLines[i].split("\\s");
                state = Integer.valueOf(ss[1]);
                root1 = Integer.valueOf(ss[2]);
                root2 = Integer.valueOf(ss[3]);
                if (strLines[i].charAt(0) == '+') {
                    calls[state]--;
                    if(calls[state] == 0) {//if this state is not needed anymore i can just delegate it to another and delete the reference
                        system = snapshots.get(state);
                        snapshots.set(state, null);//saving the memory
                        //                            System.gc();//TODO enable it
                    } else {//else i need to copy it
                        system = new int[elem + 1];
                        System.arraycopy(snapshots.get(state), 1, system, 1, elem);
                    }
                    snapshots.add(system);
                    //
                    if (system[root1] == 0) {
                        system[root1] = -1;
                    } else while (system[root1] != -1) root1 = system[root1];

                    if (system[root2] == 0) {
                        system[root2] = -1;
                    } else while (system[root2] != -1) root2 = system[root2];

                    if ((root1 != root2) || root1 == -1) system[root2] = root1;//if root1==-1 => root2 = -1
                } else {
                    system = snapshots.get(state);
                    calls[state]--;
                    if(calls[state] == 0) snapshots.set(state, null);//saving the memory
                    snapshots.add(null);
                    //
                    if (root1 == root2) answer.append("YES\n");
                    else if (system[root1] == 0 || system[root2] == 0) answer.append("NO\n");
                    else {
                        while (system[root1] != -1) root1 = system[root1];
                        while (system[root2] != -1) root2 = system[root2];
                        if (root1 == root2) answer.append("YES\n");
                        else answer.append("NO\n");
                    }
                }
            } else {
                snapshots.add(null);
            }
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
            bufferedWriter.write(answer.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }
}