package jeeconf2017;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Quiz3 {
    public static void main(String[] args) {
        IntStream stream = IntStream.of(4,4,6,1,34,5,9);;
        OptionalInt first = stream.filter(x -> x<0).findFirst();
        System.out.println(first.isPresent());
        if(first == null) System.out.println(first);
        else System.out.println(first.getAsInt());
    }
}
