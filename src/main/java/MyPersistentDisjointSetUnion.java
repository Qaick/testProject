import java.io.*;

public class MyPersistentDisjointSetUnion
{
    private final static String WhSp = "\\s"; // whitespace
    private final static int MAXQ = 100_005, // p_array roots[] Queries
            MAXS = 10_000_000; // 1e8 limit for elements

    private static int an;
    private static int roots[] = new int[MAXQ]; // roots for trees
    private static int lch[] = new int[MAXS], rch[] = new int[MAXS], cnt; // counter

    static int new_node(int left, int right)
    {
        lch[cnt] = left;
        rch[cnt] = right;
        return cnt++;
    }

    static int build_starting_tree(int n)
    {
        if(n == 1) return new_node(-1, -1);
        int m = n/2;
        return new_node(build_starting_tree(m), build_starting_tree(n-m));
    }

    private static int find_set(int v,int weight, int i)
    {
        int c_i = get(v, an, weight,i);
        if (c_i < 0) return i;
        else return find_set(v,c_i,i);
    }

    private static int set(int v,int n, int weight,int i)
    {
        if (weight<0||weight>=n)
            return v;
        if (n==1)
            return new_node(i,i);
        int m = n/2;
        if (weight<=m)
            return get(lch[v],m,weight,i);
        else
            return get(rch[v],n-m,weight-m,i);
    }


    private static int union_set(int v, int a, int b, int end_number)
    {
        int c_a = find_set(v,a, a);
        int c_b = find_set(v, b, a);
        //b>a
        if(a>b){
            int tmp = a;
            a= b;
            b=tmp;
        }
//        int ver = set(v,c_b, end_number);
//        return set(ver,c_a, c_b);
        return 0;
    }

    private static int get(int ver, int a, int end_number, int koef)
    {
//        if (end_number < 0 || end_number >=n)
//            return end_number;
//        if (n==1)
//            return new_node(koef,koef);
        return 0;
    }


    public static void main(String... args) {
        long time = System.currentTimeMillis();
        StringBuilder answer = new StringBuilder();
        String line, ss[];
//        int     an, // elements count
         int       k, // roots count
                a, // first element
                b, // second element
                v; // teporary for Version
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            ss = bufferedReader.readLine().split(WhSp);
            an = Integer.parseInt(ss[0]);
            k = Integer.parseInt(ss[1]);
            roots[0] = build_starting_tree(an);
            for (int i = 1; i <= k; ++i) {
                line = bufferedReader.readLine();
                ss = line.split(WhSp);
                v = Integer.parseInt(ss[1]);
                a = Integer.parseInt(ss[2])-1;
                b = Integer.parseInt(ss[3])-1;

                if (line.charAt(0) == '+') {
//                    roots[i] = union_set(roots[v], a, b);
                } else {
                    //optimize if -1 there is no same set
                    int id_b = find_set(roots[v], b, a);
                    int id_a = find_set(roots[v], a, a);
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
