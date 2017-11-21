import java.util.Arrays;

public class ArrayParams {
    public class Test
    {
        String a = "";
        public void method()
        {
            for(int i = 0; i < 3; i++)
            {
                System.out.print(i);
            }
            System.out.print("");
        }
    }
     // in default changelist
    
    public static void main(String[] args) {
//        meth(null);
//        meth(null, null);
//        meth(null, "string");
//        meth("string", null);
//        meth(null, null, null, "asdf");
        meth(2, 1, "");
    }

    static void meth(int a, int b, String... arr) {
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
