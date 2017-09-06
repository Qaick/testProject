package jit.generics;//: generics/SuperTypeWildcards.java
import java.util.*;

public class SuperTypeWildcards {
  static void writeTo(List<? super Apple> apples) {
    apples.add(new Apple());
    apples.add(new Jonathan());
    apples.add(new JJonathan());
    // apples.add(new Fruit()); // Error
  }

  public static void main(String[] args) {
    writeTo(new ArrayList<Fruit>());
  }
} ///:~
