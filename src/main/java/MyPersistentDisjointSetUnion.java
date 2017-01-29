import java.io.*;

public class MyPersistentDisjointSetUnion
{
    final static String WhSp = "\\s"; // whitespace
    final static int MAXQ = 100_005, // p_array roots[] Queries
            MAXS = 10_000_000; // 1e8 limit for elements

    static int core_n;
    static p_array roots[] = new p_array[MAXQ]; // roots for trees
    static int lch[] = new int[MAXS], rch[] = new int[MAXS], cnt; // counter

    static int new_node(int left, int right)
    {
        assert (cnt < MAXS);
        lch[cnt] = left;
        rch[cnt] = right;
        return cnt++;
    }

    static class p_array {
        int root;
        int build(int n)
        {
            if (n == 1)
                return new_node(-1, -1);
            int m = n / 2;
            return new_node(build(m), build(n - m));
        }
        p_array() {
            root = build(core_n);
        }
        p_array(int root) {
            this.root = root;
        }

        int get(int v, int n, int i)
        {
            if (n == 1)
                return lch[v];// returns value(weight) of vertex
            int m = n / 2;
            if (i < m)
                return get(lch[v], m, i);
            else
                return get(rch[v], n - m, i - m);
        }
        int get(int i) { return get(root,core_n,i);}

        //v - vertex, core_n - deep lenght, i - needed vertex, x - value to set
        int set(int v, int n, int i, int x)
        {
            //path that I already have from previous tree
            if (i < 0 || i >= n)
                return v;
            //last deep element
            if (n == 1)
                return new_node(x, x);
            int m = n / 2;
            //recursive building tree to the needed to set vertext
            return new_node(set(lch[v], m, i, x), set(rch[v], n - m, i - m, x));
        }
        p_array set(int i, int x) {return new p_array(set(root,core_n,i,x));}
    }

    static int find_set(p_array root, int i)
    {
        int c_i = root.get(i);
        if (c_i < 0)
            return i;
        return find_set(root, c_i);
    }

    static p_array union_set(p_array root, int a, int b)
    {
        a = find_set(root, a);
        b = find_set(root, b);
        if (a != b)
        {
            int aa = root.get(a);
            int bb = root.get(b);
            //b>a
            if (aa > bb)
            {
                int tmp = a;
                a = b;
                b = tmp;
            }
            p_array ver = root.set(a, aa + bb);
            return ver.set(b, a);
        }
        else
        {
            return root;
        }
    }

    public static void main(String... args)
    {
        long time = System.currentTimeMillis();
        StringBuilder answer = new StringBuilder();
        String line, ss[];
        //        int     core_n, // elements count
        int k, // roots count
                a, // first element
                b, // second element
                v; // teporary for Version
        try
        {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            ss = bufferedReader.readLine().split(WhSp);
            core_n = Integer.parseInt(ss[0]) + 1;
            k = Integer.parseInt(ss[1]);
            roots[0] = new p_array();
            for (int i = 1; i <= k; ++i)
            {
                line = bufferedReader.readLine();
                ss = line.split(WhSp);
                v = Integer.parseInt(ss[1]);
                a = Integer.parseInt(ss[2]);
                b = Integer.parseInt(ss[3]);

                if (line.charAt(0) == '+')
                {
                    roots[i] = union_set(roots[v], a, b);
                }
                else
                {
                    int id_a = find_set(roots[v], a);
                    int id_b = find_set(roots[v], b);
                    answer.append((id_a == id_b) ? "YES" : "NO").append('\n');
                }
            }

            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
            bufferedWriter.write(answer.toString());
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
        System.out.println("cnt: " + cnt);
    }
}
