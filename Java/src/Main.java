public class Main {
    public static void main(String[] args)
    {
        // Using Console to input data from user
        char[] name = System.console().readPassword();
        String pass = String.valueOf(name);

        System.out.println("You entered string " + pass);
    }
}