package baka.to.test;

import java.util.ArrayList;
import java.util.List;

public class T
{

public static class Sparrow extends Bird
{
}

public static class Bird
{
}

    public static void main(String[] args)
    {
        List<? extends Bird> birds = new ArrayList<>();
//        birds.add();
//        birds.add(new Sparrow());
//        birds.add(new Sparrow());
    }
}