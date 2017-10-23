public class ForTests {
    public static void method() {
        System.out.println("hello we have problem here");
        if (true) throw new RuntimeException("some \"hello\" Message");
    }

    public static void main(String[] args) {
        String s = "String\nsecond\nsecond\nHello\n";
        s = s.replace('\n',' ');
        s = "String\nsecond\nthird\nHello\n";
        s = s.replace("\n","");
        System.out.println(s);
        System.out.println("start \"hello\" end");
    }
}
