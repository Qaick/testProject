import java.util.Arrays;
import java.util.List;

public class HelloW {
    
    private static class Inner{
        private int someValue = 1;
        int getSomeValue() {return someValue;}
    }
    public static void main(String[] args) {
        Inner hi = new Inner();
        hi.someValue = 4;
        System.out.println("someValue = " + hi.getSomeValue());
        if (++hi.someValue % 5 == 0) {
            System.out.println("OK");
        }
        
        System.out.println("hello");
        List<String> list = Arrays.asList("hey", "no", "tototo");
        final String[] a = new String[list.size()];
        System.out.println(a);
        System.out.println(list.toArray(a));
    }
}
