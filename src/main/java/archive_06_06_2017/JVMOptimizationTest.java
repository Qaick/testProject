package archive_06_06_2017;

/**
 * Checking how work oracle jvm optimization.
 * VMoption: -XX:+PrintCompilation
 */
public class JVMOptimizationTest
{
    public static void main(String[] args)
    {
        long max = 1_000_000, result = 0;
        for (int i = 0; i < max; i++)
        {
            long start = System.nanoTime();
            for (int j = 0; j < 10000; j++)
            {
                result += i+j;
            }
            long end = System.nanoTime();
            if (max-10000<i)
                System.out.println("result: "+(end-start)+" "+result);
        }
    }
}
