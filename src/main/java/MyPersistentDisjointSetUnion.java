import java.io.*;

public class MyPersistentDisjointSetUnion
{
    private final static String WhSp = "\\s"; // whitespace
    private final static int MAXQ = 100_005, // p_array roots[] Queries
            MAXS = 10_000_000; // 1e8 limit for elements

    private static int an, cc;
    private static int roots[] = new int[MAXQ]; // roots for trees
    private static int lch[] = new int[MAXS], rch[] = new int[MAXS], cnt; // counter

    static int new_node(int left, int right)
    {
        lch[cnt] = left;
        rch[cnt] = right;
        return cnt++;
    }

    static int build(int n)
    {
        if (n == 1)
            return new_node(-1, -1);
        int m = n / 2;
        return new_node(build(m), build(n - m));
    }

    private static int get(int root, int n, int i)
    {
        if (n == 1)
            return lch[root];// returns value(weight) of vertex
        int m = n / 2;
        if (i < m)
            return get(lch[root], m, i);
        else
            return get(rch[root], n - m, i - m);
    }

    //v - vertex, n - deep lenght, i - needed vertex, x - value to set
    private static int set(int v, int n, int i, int x, int copy)
    {
        //path that I already have from previous tree
        if (i < 0 || i >= n)
            return v;
        //last deep element
        if (n == 1)
            return new_node(x, x);
        int m = n / 2;
        //recursive building tree to the needed to set vertext
        if (v > copy)
            cnt--;
        int r = rch[v];
        return new_node(set(lch[v], m, i, x, copy), set(r, n - m, i - m, x, copy));
    }

    static class Two
    {
        int c_i, i;

        Two(int c, int i)
        {
            this.c_i = c;
            this.i = i;
        }
    }

    private static Two find_set(int v, int i)
    {
        int c_i = get(v, an, i);
        if (c_i < 0)
            return new Two(c_i, i);
        return find_set(v, c_i);
    }

    private static int union_set(int v, int a, int b)
    {
        //for not repeating get operation after if
        Two ta = find_set(v, a);
        Two tb = find_set(v, b);
        a = ta.i;
        b = tb.i;
        if (a != b)
        {
            //i>c_i
            if (ta.c_i > tb.c_i)
            {
                int tmp = a;
                a = b;
                b = tmp;
            }
            int copy = v + 1;// TODO probably better use cnt;
            int ver = set(v, an, a, ta.c_i + tb.c_i, copy + cc);
            return set(ver, an, b, a, copy);
        }
        else
        {
            return v;
        }
    }

    public static void main(String... args)
    {
        long time = System.currentTimeMillis();
        StringBuilder answer = new StringBuilder();
        String line, ss[];
        int k, // roots count
                a, // first element
                b, // second element
                v; // teporary for Version
        try
        {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            ss = bufferedReader.readLine().split(WhSp);
            an = Integer.parseInt(ss[0]) + 1;
            cc = (int) Math.ceil(Math.sqrt(an) + 1);
            k = Integer.parseInt(ss[1]);
            roots[0] = build(an);
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
                    Two id_a = find_set(roots[v], a);
                    if (id_a.c_i == -1)
                    {
                        answer.append("NO\n");
                        continue;
                    }
                    Two id_b = find_set(roots[v], b);
                    answer.append((id_a.i == id_b.i) ? "YES\n" : "NO\n");
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
