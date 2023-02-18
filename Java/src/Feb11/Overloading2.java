package Feb11;

public class Overloading2 {

    public static void main(String args[]){

        Overloading2 overloaded = new Overloading2();

        // this will call int method
        overloaded.add(10,10);
    }
       int add(int a, int b)
    {
        int sum = a+b;
        System.out.println("int data type" + sum);
        return sum;


    }
    //two float parameters
     long add(long a, long b)
    {
        long sum = a+b;
        System.out.println("long data type" + sum);
        return sum;
    }
}
