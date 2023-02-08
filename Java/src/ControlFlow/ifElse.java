package ControlFlow;

import java.util.Random;


public class ifElse {
    public static void main(String []args)
    {
        Random random = new Random();

        int number = random.nextInt(100);

        if(number<50)
        {
            System.out.println("fail");
        }
        else if(number>=50 && number<60)
        {
            System.out.println("D grade");
        }
        else if(number>=60 && number<70)
        {
            System.out.println("C grade");
        }
        else if(number>=70 && number<80)
        {
            System.out.println("B grade");
        }
        else if(number>=80 && number<90)
        {
            System.out.println("A grade");
        }
        else if(number>=90 && number<100)
        {
            System.out.println("A+ grade");
        }
        else
        {
            System.out.println("Invalid!");
        }
    }
}
