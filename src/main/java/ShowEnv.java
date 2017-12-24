import java.util.Map;

public class ShowEnv {
    public static void main(String[] args)  {
//        System.out.println(InetAddress.getLocalHost().getHostName());
        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }
}
