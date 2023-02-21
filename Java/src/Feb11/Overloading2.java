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
     double add(double a, double b)
    {
        double sum = a+b;
        System.out.println("long data type" + sum);
        return sum;
    }
}
