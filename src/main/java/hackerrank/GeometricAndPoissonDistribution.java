package hackerrank;

public class GeometricAndPoissonDistribution {
    
    public static void main(String[] args)
    {
        int k = 3;
        double lam=2;
        System.out.printf("%.3f\n", poissonDistribution(k, lam));
    }
    
    static double poissonDistribution(int k,double lam){
        double e = 2.71828;
        double ans = Math.pow(lam,k)*Math.pow(e,-lam) / fact(k);
        return ans;
    }

    static double fact(double i) {
        return i < 2 ? 1 : i*fact(i-1);
    }

    static double geometricDistribution(int n, double p){
        return Math.pow(1-p, n-1) * p;
    }
}
