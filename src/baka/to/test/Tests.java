package baka.to.test;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Tests
{
    public static void main(String[] args)
    {
        getPhysicalMemory();
    }
    
    static void getPhysicalMemory() {
        try
        {
            Runtime.getRuntime().maxMemory();
            Class operatingSystemMXBean = Class.forName("com.sun.management.OperatingSystemMXBean");
            Method getRAM = operatingSystemMXBean.getMethod("getTotalPhysicalMemorySize");
            Object ram = getRAM.invoke(ManagementFactory.getOperatingSystemMXBean());
            if (ram.getClass().equals(Long.class))
            {
                System.out.println(((Long) ram).longValue() / 1048576 + " " + "MB");
            }
            else
                System.out.println("Some error in getting total physical memory.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    static void nullInForeach() {
        int[] list = null;
        for (int a : list) {}
    }

    static void testFinallySout()
    {
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
