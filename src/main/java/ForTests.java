public class ForTests {
    // testing push commit, revert etc.
    public static void method() {
        System.out.println("hello we have problem here");
        if (true) throw new RuntimeException("some \"hello\" Message");
    }
    // test ff from master
    public static void main(String[] args) {
        String s = "String\nsecond\nsecond\nHello\n";
        s = s.replace('\n',' ');
        s = "String\nsecond\nthird\nHello\n";
        s = s.replace("\n","");
        System.out.println(s);
        System.out.println("start \"hello\" end");
    }
    //change from
}
