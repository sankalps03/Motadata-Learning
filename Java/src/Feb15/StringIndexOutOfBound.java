package Feb15;

public class StringIndexOutOfBound {
    public static void main(String[] args){

        try{
            String name = "I will be back";

            System.out.println(name.charAt(15));
        }
        catch (StringIndexOutOfBoundsException s){

            System.out.println(s);
        }
    }
}
