package java8;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class GUITalk {
    
    public static void main(String[] args) {
        m(1);
        ArrayList<Integer> t = new ArrayList();
        t.add(1);
        t.add(0);
        t.remove(Integer.valueOf(0).intValue());
        System.out.println(t);
        
//        list.forEach(Item::apply);
//        list.removeIf(Item::isBad);
        LocalDate localDate = LocalDate.of(2014, Month.DECEMBER, 15);
    }
    
    static void m(double i) {
        System.out.println("double");
    }

    static void m(Integer i) {
        System.out.println("Integer");
    }
}
