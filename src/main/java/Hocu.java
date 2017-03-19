import org.apache.commons.lang3.StringUtils;

public class Hocu {
    public static void main(String[] args) {
        System.out.println(Double.POSITIVE_INFINITY != 0.0);
        System.out.println(Double.valueOf(Double.NaN));
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY);
        
        roundingBig();
        System.out.println("   ".trim().length() > 0);
        try{
            if (true) throw new RuntimeException("runtime exception");
        } catch (Exception e) {
            // it's not main thread
            e.printStackTrace();
        }
        System.out.println("All right.");
    }

    /**
     * rounding to 1 
     */
    static void roundingBig() {
        int a = 1;
        double b = 0.9999999999999999;//17 symbols
        System.out.println("double max:"+Double.MAX_VALUE);
        System.out.println(Double.MAX_EXPONENT);
        System.out.println(Double.MIN_VALUE);
        System.out.println((int)(b*a));
        System.out.println(a+">"+b+"? - "+(a>b));
    }
}
