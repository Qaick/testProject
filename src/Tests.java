import java.lang.reflect.Method;

public class Tests
{
    public static void main(String[] args)
    {
        typeCasting();
    }

    private static void typeCasting()
    {
        Object t = new Tests();
        try
        {
            Method m = t.getClass().getMethod("dtypeCasting");
            Object lon = m.invoke(t);
            System.out.println(lon);

            //reflective call of private method
            m = t.getClass().getDeclaredMethod("dtC2");
            lon = m.invoke(t);
            System.out.println(lon);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public long dtypeCasting() {
        return 17L;
    }
    private long dtC2() {
        return 18L;
    }
}
