package git.tests;

public class CherryPick {
    static int a = 8;

    public static void main(String[] args) {
        a = a * 7;
        for (int i = 0; i < 5; i++) {
            a = a * 3;
        }
        System.out.println(a);
    }
}
