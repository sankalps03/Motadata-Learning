package Feb15;

public class ArrayIndexOtOfBound {
    public static void main(String[] args){
        try {
            int array[] = new  int[5];

            array[5]= 10;
        }
        catch (ArrayIndexOutOfBoundsException a){

            System.out.println(a);
        }


    }
}
