import java.nio.charset.Charset;

public class AmazingSwitch {

    public static void main(String[] args)
    {
        System.out.println(Charset.forName("utf-8"));
        System.out.println(Charset.forName("UTF-8"));
        
        // Amazing switch
        switch (1) {
            case 0:
                int i=1;
            case 1:
                i=5;
        }
    }
}
