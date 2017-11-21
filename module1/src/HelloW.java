import java.util.Arrays;
import java.util.List;

public class HelloW {
    public static void main(String[] args) {
        System.out.println("hello");
        List<String> list = Arrays.asList("hey", "no", "tototo");
        final String[] a = new String[list.size()];
        System.out.println(a);
        System.out.println(list.toArray(a));
    }
}
