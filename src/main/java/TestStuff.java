import java.util.ArrayList;
import java.util.List;

public class TestStuff {
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();
        list.add("before first");
        list.add("first");
        
        System.out.println(String.format("%10s |%5s| end", "first", list.get(1)));
    }
    static class FooObject {
        public static void saySomething() {
            System.out.println("Hi there!");
        }
    }

    static class Main {
        public static void main(String[] args) {
            int[] foo = null;
            System.out.println(foo.length);
            
        }
    }
}
