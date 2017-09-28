package java8;

public interface Test {
    static void method() {
        System.out.println("hello world");
    }

    default void menthod3() {
        System.out.println("default print");
    }

    static void main(String[] args) {
        
    }
}
