package remove;

public class ClassB {
    public static void main(String[] args) {
        ClassA.Inner inner = new ClassA().new Inner();
        System.out.println(inner.getSix());
    }
}
