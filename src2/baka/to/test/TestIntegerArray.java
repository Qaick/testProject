package baka.to.test;

import java.util.HashMap;

public class TestIntegerArray
{
    
    interface I1{
        void g();
    }
    interface I2{
        void g();
    }
    class B implements I1{
        public void g(){}
    }
    class C extends B implements I2{}
    
    
    static int testSize = 5_000_000;
    public static void main(String[] args)
    {
        HashMap<Integer, Integer> integ = new HashMap<>(testSize);
        long time = System.currentTimeMillis();
        for (int i = 1; i <= testSize; i++)
        {
            integ.put(Integer.valueOf(i), Integer.valueOf(i));
            integ.containsKey(Integer.valueOf(i));
        }
        System.out.println("time integ: " + (System.currentTimeMillis()-time));
    }

//    public static void main(String[] args)
//    {
//        int[][] cash = new int[testSize+1][1];
//        HashMap<int[], int[]> arr = new HashMap<>(testSize);
//        long time = System.currentTimeMillis();
//        for (int i = 0; i <= testSize; i++)
//        {
//            cash[i] = new int[]{i};
//        }
//        for (int i = 1; i <= testSize; i++)
//        {
//            arr.put(cash[i], cash[i]);
//            arr.containsKey(cash[i]);
//        }
//        System.out.println("time array: " + (System.currentTimeMillis()-time));
//    }
}
