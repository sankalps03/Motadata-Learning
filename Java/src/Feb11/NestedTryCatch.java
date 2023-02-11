package Feb11;

public class NestedTryCatch {
    public static void main(String args[]) {
        // Here ArrayIndexOutOfBoundsException is thrown
        try {
            int a[]=new int[10];
            System.out.println(a[12]);
            try {
                System.out.println("Division");
                int res = 100/ 0;
            }
            catch (ArithmeticException ex2) {
                System.out.println("Sorry! Division by zero isn't feasible!");
            }
        }
        catch (ArrayIndexOutOfBoundsException ex1) {
            System.out.println("ArrayIndexOutOfBoundsException");
        }

    }
}
