package Operators;

public class Operators
{
    public static void main(String[] args)
    {
        int num1 = 10;

        int num2 = 15;

        Operators operation = new Operators();

        try
        {
            operation.arithmeticOperators(num1,num2);

            operation.assignmentOperators(num1);

            operation.relationalOperators(num1,num2);

            operation.logicalOperators(num1,num2);

            operation.unaryOperators(num1,num2);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    void arithmeticOperators(int num1, int num2)
    {
        System.out.println("a + b = " + (num1 + num2));

        System.out.println("a - b = " + (num1 - num2));

        System.out.println("a * b = " + (num1 * num2));

        System.out.println("a / b = " + (num1 / num2));

        System.out.println("a % b = " + (num1 % num2));

    }

    void assignmentOperators(int num1)
    {
        int variable;

        variable = num1;
        System.out.println("variable using =: " + variable);

        variable += num1;
        System.out.println("variable using +=: " + variable);

        variable *= num1;
        System.out.println("variable using *=: " + variable);

        variable -= num1;
        System.out.println("variable using -=: "+ variable);

        variable /= num1;
        System.out.println("variable using /=: "+ variable);

        variable %= num1;
        System.out.println("variable using %=: "+ variable);
    }

    void relationalOperators(int num1, int num2)
    {
        System.out.println("is num1 == num2: "+ (num1 == num2));

        System.out.println("is num1 != num2: "+(num1 != num2));

        System.out.println("is num1 > num2: "+(num1 > num2));

        System.out.println("is num1 < num2: "+(num1 < num2));

        System.out.println("is num1 >= num2: "+(num1 >= num2));

        System.out.println("is num1 <= num2"+(num1 <= num2));
    }

    void logicalOperators(int num1, int num2)
    {
        System.out.println("(num1 > num2) && (num1 > num2): "+((num1 > num2) && (num1 > num2)));

        System.out.println("(num1 > num2) && (num1 < num2): "+((num1 > num2) && (num1 < num2)));

        System.out.println("(num1 < num2) || (num1 > num2): "+((num1 < num2) || (num1 > num2)));

        System.out.println("(num1 > num2) || (num1 < num2): "+((num1 > num2) || (num1 < num2)));

        System.out.println("(num1 < num2) || (num1 < num2): "+((num1 < num2) || (num1 < num2)));

        System.out.println("!(num1 == num2): "+(!(num1 == num2)));

        System.out.println("!(num1 > num2)"+(!(num1 > num2)));
    }

    void unaryOperators(int num1, int num2)
    {
        int variable1, variable2;
        System.out.println("Value of a: " + num1);

        // increment operator
        variable1 = ++num1;
        System.out.println("After increment: " + variable1);

        System.out.println("Value of b: " + num2);

        // decrement operator
        variable2 = --num2;
        System.out.println("After decrement: " + variable2);
    }
}
