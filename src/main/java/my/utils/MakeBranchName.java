package my.utils;

import java.util.Scanner;

public class MakeBranchName
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Line for branch name: ");
        String name = in.nextLine();
        name = name.replaceAll(" ", "_");
        System.out.println(name);
    }
}
