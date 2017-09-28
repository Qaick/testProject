package hackerrank;

public class BinomialDistribution {
    public static void main(String[] args)
    {
        int n = 10,x = 2;
        double p=12, q=88;
        double sum = p + q;
        double ans = binomialDistribution(x,n,n,p/sum);
        System.out.printf("%.3f\n", binomialDistribution(0,x,n,p/sum));
        System.out.printf("%.3f\n", ans);
    }

    static double binomialDistribution(int x, int y, int n, double p) {
//		System.out.println("n = [" + n + "], x = [" + x + "], p = [" + p + "]");
        double ans = 0;
        for (int i = x; i <= y; i++)
        {
            final double comb = comb(n,i);
//			System.out.println("comb = " + comb);
            final double v = Math.pow(p, i) * Math.pow(1 - p, n - i);
//			System.out.println("v = " + v);
            ans += comb * v;
        }
        return ans;
    }

    static double comb(double n, double x) {
        return fact(n) / (fact(x) * fact(n - x));
    }

    static double fact(double i) {
        return i < 2 ? 1 : i*fact(i-1);
    }
}