public class ahoy {
    public static void main(String[] args) {
        double costA, costB;
        
        int k = 5;
        double lam = 2.5;
        double ans = Math.pow(lam, k)*Math.pow(2.71828, -lam)/fact(k);
        
        System.out.printf("%.3f\n", ans);
    }

    static double comb(double n, double x) {
        return fact(n) / (fact(x) * fact(n - x));
    }

    static double fact(double i) {
        return i < 2 ? 1 : i*fact(i-1);
    }
}
