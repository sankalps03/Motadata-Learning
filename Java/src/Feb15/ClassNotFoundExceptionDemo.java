package Feb15;

public class ClassNotFoundExceptionDemo {

    public static void main(String[] args) {

        try{
            Class.forName("Sankalp");

        } catch (ClassNotFoundException e) {

            System.out.println(e);
        }
    }

}
