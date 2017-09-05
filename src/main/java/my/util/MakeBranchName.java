package my.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class MakeBranchName
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Line for branch name: ");
        String branchName = in.nextLine();
        String next = in.nextLine();
        if (next != null) {
            branchName = branchName + '_' + next;
        }
        branchName = StringUtils.remove(branchName, ':');
        branchName = branchName.replaceAll(" ", "_");
        System.out.println(branchName);
    }
}
