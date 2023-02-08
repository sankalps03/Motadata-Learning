package ControlFlow;

import java.util.Random;

public class Ternary
{
    public static void main(String[] args)
    {
        Random random = new Random();
        int marks = random.nextInt(100);
        String result = (marks > 40) ? "pass" : "fail";

        System.out.println("You " + result + " the exam.");
}
}
