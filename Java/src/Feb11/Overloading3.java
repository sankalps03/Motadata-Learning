package Feb11;

public class Overloading3 {
    public static void main(String args[]){

        Overloading3 overloaded1 = new Overloading3();

        // this will throw a compiler error in case of (10,10)
        overloaded1.add(10, 10L);
    }
    public void add(int a, long b)
    {
        long sum = a+b;
        System.out.println("int , long data type" + sum);

    }
    //two float parameters
    public void add(long a, int b)
    {
        long sum = a+b;
        System.out.println("long , int data type" + sum);
    }
}
