package Feb11;

public class MultipleCatch {
    public static void main(String[] args) {
        try {
            int array[] = new int[10];

            array[10] = 30 / 0;

        }
        catch (ArrayIndexOutOfBoundsException e) {

            System.out.println(e.getMessage());

        }catch (ArithmeticException e) {

            System.out.println(e.getMessage());

        }
    }
}

