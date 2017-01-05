import java.io.*;

/**
 * Created by olehb on 14.12.16.
 */
@SuppressWarnings("duplication")
public class persistent_array {
    final static String WhSp = "\\s";//whitespace

    /*  <persistent array>  */
    final static int MAXN = 100_005, //for creating int[] data
            MAXQ = MAXN, //p_array root[] Queries
            MAXS = 10_000_000;//1e8 limit for elements

    static p_array root[] = new p_array[MAXQ];

    //n - elements count, s - lines count
    static int lch[] = new int[MAXS],
            rch[] = new int[MAXS],
            cnt,//counter
            n, //elements count
            k, //lines count
    //            data[] = new int[MAXN],//used to setup first connections of system
            cnt_a, cnt_b;
    //data is binary tree where last element is element of problem

    static int new_node(int l, int r) {
        assert (cnt < MAXS);
        lch[cnt] = l;
        rch[cnt] = r;
        return cnt++;
    }

    static class p_array {
        int n, root;

        int build(int start, int n) {
            if (n == 1)
                return new_node(-1, -1);

            int m = n / 2;
            return new_node(build(0, m), build(m, n - m));
        }

        p_array(int n) {
            this.n = n;
            root = build(0, n);
        }

        p_array(int n, int root) {
            this.n = n;
            this.root = root;
        }

        int get(int v, int n, int i) {//vertex, number of all, i what to get
            if (n == 1)
                return lch[v];

            int m = n / 2;
            if (i < m) {
                return get(lch[v], m, i);
            } else {
                return get(rch[v], n - m, i - m);
            }
            //            return i < m ? get(lch[v], m, i) : get(rch[v], n - m, i - m);
        }

        // get the value at potition i.
        int get(int i) {
            return get(root, n, i);
        }

        int set(int v, int n, int i, int x) {
            if (i < 0 || i >= n)
                return v;

            if (n == 1)
                return new_node(x, x);

            int m = n / 2;
            return new_node(set(lch[v], m, i, x), set(rch[v], n - m, i - m, x));
        }

        // get the resultant array of setting value x to position i.
        p_array set(int i, int x) {
            return new p_array(n, set(root, n, i, x));
        }
    }

    static int find_set(p_array root, int a) {
        int p = root.get(a);
        if (p < 0)
            return a;

        int ans = find_set(root, p);
        //data = data.set(a, ans);
        return ans;
    }

    static p_array union_set(p_array root, int a, int b) {
        a = find_set(root, a);
        b = find_set(root, b);

        if (a != b) {
            cnt_a = root.get(a);
            cnt_b = root.get(b);

            if (cnt_a > cnt_b) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            p_array ans = root.set(a, cnt_a + cnt_b);
            ans = ans.set(b, a);
            return ans;
        } else
            return root;
    }

    public static void main(String... args) {
        long time = System.currentTimeMillis();
        StringBuilder answer = new StringBuilder();
        int w=0,
                a, //first element
                b, //second element
                v; //teporary for Version
        String s;
        try {
            String line, ss[];
            final BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            ss = bufferedReader.readLine().split(WhSp);
            n = Integer.parseInt(ss[0]);
            k = Integer.parseInt(ss[1]);
            //            lch = new int[s + 1];
            //            rch = new int[s + 1];
            root[0] = new p_array(n + 1);
            for (int i = 1; i <= k; ++i) {
                line = bufferedReader.readLine();
                ss = line.split(WhSp);
                v = Integer.parseInt(ss[1]);
                a = Integer.parseInt(ss[2]);
                b = Integer.parseInt(ss[3]);

                if (line.charAt(0) == '+') {
                    root[i] = union_set(root[v], a, b);
                    w++;
                } else {
                    int id_b = find_set(root[v], b);
                    int id_a = find_set(root[v], a);
                    answer.append(((id_a == id_b) ? "YES" : "NO") + '\n');
                }
            }

            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
            bufferedWriter.write(answer.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }
}