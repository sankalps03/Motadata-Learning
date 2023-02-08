package Methods;

public class OverloadingDataTypes {
    void show(int x)
    {
        System.out.println("In int" + x);
    }
    void show(String s)
    {
        System.out.println("In String" + s);
    }
    void show(byte b)
    {
        System.out.println("In byte" + b);
    }

    public static void main(String[] args)
    {
        byte a = 25;

        OverloadingDataTypes obj = new OverloadingDataTypes();

        obj.show(a);

        obj.show("hello");

        obj.show(250);

        obj.show('A');

        obj.show("A");

    }
}
