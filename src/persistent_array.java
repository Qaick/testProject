import java.io.*;

public class persistent_array {
    private final static String WhSp = "\\s"; // whitespace
    private final static int MAXQ = 100_005, // p_array lines[] Queries
            MAXS = 10_000_000; // 1e8 limit for elements

    private static p_array lines[] = new p_array[MAXQ];
    private static int lch[] = new int[MAXS],
            rch[] = new int[MAXS],
            cnt; // counter

    static int new_node(int l, int r) {
        assert (cnt < MAXS);
        lch[cnt] = l;
        rch[cnt] = r;
        return cnt++;
    }

    static class p_array {
        int n, root;

        int build(int n) {
            if (n == 1)
                return new_node(-1, -1);
            int m = n / 2;
            return new_node(build(m), build(n - m));
        }

        p_array(int n) {
            this.n = n;
            root = build(n);
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
        }

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

    private static int find_set(p_array root, int a) {
        int p = root.get(a);
        if (p < 0)
            return a;
        return find_set(root, p);
    }

    private static p_array union_set(p_array root, int a, int b) {
        a = find_set(root, a);
        b = find_set(root, b);
        if (a != b) {
            int cnt_a = root.get(a);
            int cnt_b = root.get(b);
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
        String line, ss[];
        int     n, // elements count
                k, // lines count
                a, // first element
                b, // second element
                v; // teporary for Version
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            ss = bufferedReader.readLine().split(WhSp);
            n = Integer.parseInt(ss[0]);
            k = Integer.parseInt(ss[1]);
            lines[0] = new p_array(n + 1);
            for (int i = 1; i <= k; ++i) {
                line = bufferedReader.readLine();
                ss = line.split(WhSp);
                v = Integer.parseInt(ss[1]);
                a = Integer.parseInt(ss[2]);
                b = Integer.parseInt(ss[3]);

                if (line.charAt(0) == '+') {
                    lines[i] = union_set(lines[v], a, b);
                } else {
                    int id_b = find_set(lines[v], b);
                    int id_a = find_set(lines[v], a);
                    answer.append((id_a == id_b) ? "YES" : "NO").append('\n');
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