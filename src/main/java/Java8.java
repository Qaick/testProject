import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Java8 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> arrayClass = String[].class;
        System.out.println(arrayClass);
        Class<?> namedClass = Class.forName("[L" + String.class.getName() + ";");
        System.out.println(namedClass);
        System.out.println(arrayClass == namedClass);
        System.out.println(new boolean[0].getClass()); // [Z
        System.out.println(new byte[0].getClass());
        System.out.println(new short[0].getClass());
        System.out.println(new int[0].getClass());
        System.out.println(new long[0].getClass()); // [J
        System.out.println(new char[0].getClass()); // 16bit
        System.out.println(new float[0].getClass());
        System.out.println(new double[0].getClass()); // 8 primitive types
        System.out.println(new Integer[0].getClass()); // [LclassFullName;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] arr = Arrays.stream(input.split(" ")).mapToInt(Integer::valueOf).toArray();
        System.out.println();
    }
}
