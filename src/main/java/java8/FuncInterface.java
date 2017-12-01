package java8;

// single abstract method
@FunctionalInterface
public interface FuncInterface<T> {
    int compare(T o1, T o2);
    boolean equals(Object obj); // already has implementation
}
