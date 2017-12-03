import java.util.Map;

public class MyJava9Class {
    public static void main(String[] args) {
        Map<String, String> map = Map.of("key", "value");
        Map<String, String> map1 = Map.ofEntries(Map.entry("key1","val1"));
        
    }
}
