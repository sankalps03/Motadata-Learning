package Methods;

public class Overloading {
    public int multiply(int a, int b)
    {
        int prod = a * b;

        return prod;
    }

    public int multiply(int a, int b, int c)
    {
        int prod = a * b * c;

        return prod;
    }

    public static void main(String[] args){
        Overloading parameters = new Overloading();

        int product1 = parameters.multiply(10,65);

        int product2 = parameters.multiply(45,54,65);

        System.out.println("two integers "+product1);

        System.out.println("Three integers "+product2);
    }


}
