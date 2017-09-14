import java.util.Arrays;

public class ArrayParams {
    public static void main(String[] args) {
        meth(null);
        meth(null, null);
        meth(null, "string");
        meth("string", null);
        meth(null, null, null, "asdf");
    }
    
    static void meth(String... arr) {
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
