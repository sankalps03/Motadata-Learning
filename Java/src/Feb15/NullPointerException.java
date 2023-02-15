package Feb15;

public class NullPointerException {

    public static void main(String[] args){

        try {
            String name = null;

            System.out.println(name.charAt(0));
        }
        catch (java.lang.NullPointerException n){

            System.out.println(n);
        }
    }
}
