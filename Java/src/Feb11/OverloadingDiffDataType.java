package Feb11;

public class OverloadingDiffDataType {
    public void add(int a, int b)
    {
        int sum = a+b;
        System.out.println("int data type" + sum);

    }
    public void add(float a, float b)
    {
        float sum = a+b;
        System.out.println("float data type" + sum);
    }
    public void add(int a, float b)
    {
        float sum = a+b;
        System.out.println("Method with (int, float) param list." + sum);

    }
    public void add(float a, int b)
    {
        float sum = a+b;
        System.out.println("Method with (float, int) param list."+ sum);
    }
}

class JavaExample
{
    public static void main(String args[])
    {
        OverloadingDiffDataType object = new OverloadingDiffDataType();

        //This will call the method add with two int params
        object.add(5, 15);

        //This will call the method add with two float params
        object.add(5.5f, 2.5f);

        // This will call the method where first parameter is int
        // and the second parameter is float
        object.add(10, 10.5f);

        // This will call the method where first parameter is float
        // and the second parameter is int
        object.add(1.5f, 1);
    }
}
