package jit.generics;//: generics/ExplicitTypeSpecification.java

import java.util.List;
import java.util.Map;

public class ExplicitTypeSpecification {
  static void f(Map<Person, List<Pet>> petPeople) {}
  public static void main(String[] args) {
    f(New.map());
  }
} ///:~
