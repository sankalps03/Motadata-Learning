package ControlFlow;

import java.util.Random;

public class ForStatement {
    public static void main(String[] args)
    {
        Random random = new Random();
        int line = random.nextInt(10)+1;
        for (int star=0; star<line; star++)
        {
            System.out.print(" ");

        for (int j=0; j<=star; j++ )
        {
            System.out.print("* ");
        }

        System.out.println();
        }
    }
}

