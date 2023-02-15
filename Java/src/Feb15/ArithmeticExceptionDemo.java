package Feb15;

public class ArithmeticExceptionDemo {
    public static void main(String[] args){

        try {
            int num1 =30 , num2= 0;

            int dividend = num1/num2;

            System.out.println("Result= "+ dividend);
        }
        catch (ArithmeticException e){

            System.out.println(e);
        }
    }

}
