package java8;

import jit.generics.Individual;
import jit.generics.Person;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class EarnixTechTalk {

    public static void main(String[] args) {
        // lambda and functional interface
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "hello", "somethig", "password", "user");
        
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        
        list.sort((o1, o2) -> 0);

        list.sort((o1, o2) -> o1.length()); // it knows there is string
        
        // anonymous class
        FileFilter fileFilter = (File pathname) -> false;
        FileFilter fileFilter2 = pathname -> pathname.getName().endsWith(".java");
        
        // method references
        Function<Person, Integer> f = person -> person.hashCode();
        Function<Person, Integer> f2 = Individual::hashCode;
        
        BinaryOperator<Integer> sum = Integer::sum;

        Consumer<String> sout = System.out::println;
        sout.accept("printed string");
        sout.accept(sout.toString());
        
        m2(k -> k);
        
        Comparator<String> comparator = (s1, s2) -> 0;
        
        // Streams
        list.stream()                           // not terminal
                .map(p -> p.length())           // not terminal
                .forEach(System.out::println);  // terminal, will trigger all the work with the stream data
        
        
    }
    
    void CSFP() {
        // Consumer - consumes an object, doesn't return
        // Supplier - provides an object, takes no parameter
        // Function - takes an object, returns another object
        // Predicate - takes an object, returns boolean
    }
    
    void functionalInterface(FuncInterface funcInterface) {
    }
    
    static void m2(FuncInt funcInt) {
        System.out.println("m2");
    }
    
    interface FuncInt{
        int m1(int k);
    }
}
