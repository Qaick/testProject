import java.util.ArrayList;
import java.util.List;

public class TestStuff {
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();
        list.add("first");
        
        System.out.println(String.format("%10s |%5s| end", "first", list.get(1)));
    }
}
