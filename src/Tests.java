import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Tests
{
    public static void main(String[] args)
    {
        testFinallySout();
    }
    
    static void testFinallySout() {
        System.out.println(testFinally());
    }

    //fucking finally block
    static int testFinally() {
        try{
            if(1==1) throw new Error();
        }catch(Exception e){
            return 0;
        }
        finally
        {
            return 1;
        }
    }
    
    static void testNull() {
        String s = null;
        String s2 = s+"a";
        System.out.println(s2);
    }
    
    static class B {}
    static class A {}
    static class AA extends A {}
    
    public static void genericExtends() {
        B b = new B();
        A a = new A();
        AA aa = new AA();
        List<? extends A> list = new ArrayList<>();
        method(a.getClass());
//        list.add(new Object());
    }
    
    public static void method(Class<? extends A> clas) {
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
