package hackerrank;

public class BinominalDistribution {
    public static void main(String[] args)
    {
        int n = 6, x = 3;
        double p=1.09, q=1;
        double sum = p + q;
        double ans = binomialDistribution(x,n,p/sum);
        System.out.printf("%.3f\n", ans);
    }

    // e.g. n=6, at least 3 -> 3..6
    static double binomialDistribution(int x,int n,double p) {
//		System.out.println("n = [" + n + "], x = [" + x + "], p = [" + p + "]");
        double ans = 0;
        for (int i = x; i <= n; i++)
        {
            final double comb = combinations(n,i);
//			System.out.println("comb = " + comb);
            final double v = Math.pow(p, i) * Math.pow(1 - p, n - i);
//			System.out.println("v = " + v);
            ans += comb * v;
        }
        return ans;
    }

    static double combinations(double n, double x) {
        return factorial(n) / (factorial(x) * factorial(n - x));
    }

    static double factorial(double i) {
        return i < 2 ? 1 : i* factorial(i-1);
    }
}
