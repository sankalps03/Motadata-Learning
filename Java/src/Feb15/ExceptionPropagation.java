package Feb15;

public class ExceptionPropagation {

    public static void main(String[] args){

        ExceptionPropagation testObject = new ExceptionPropagation();

        testObject.handle();

    }
    void divide()
    {
        int data = 50 / 0;
        System.out.println("divide");
    }

    void propagate()
    {
        divide();
        System.out.println("propagate");
    }

    void handle()
    {
        try {
            propagate();

            System.out.println("handle");
        }
        catch (Exception e) {

            System.out.println(e +" Exception handled");
        }
    }


}

