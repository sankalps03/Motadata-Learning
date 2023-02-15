package Feb15;

public class NumberFormatExceptionDemo {
    public static void  main(String[] args){
        try {

            int num = Integer.parseInt("sankalp");

            System.out.println(num);
        }
        catch (NumberFormatException n){

            System.out.println(n);
        }
    }
}
