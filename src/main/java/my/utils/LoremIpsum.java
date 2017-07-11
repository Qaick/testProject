package my.utils;

import java.util.Scanner;

public class LoremIpsum {
    static final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Lorem length(max " + lorem.length() + ")?");
        System.out.println(lorem.subSequence(0, in.nextInt()));
    }
}
