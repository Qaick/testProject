public class TestJava {
    public static void main(String[] args) {
        finalParameters();
    }

    static void finalParameters() {
        String s = "not Final String";
        System.out.println(s.hashCode());
        finalParam(s);
        s = "changed";
        finalParam(s);
    }
    
    static void finalParam(final String s) {
        System.out.println(s.hashCode());
        System.out.println(s);
    }
}
