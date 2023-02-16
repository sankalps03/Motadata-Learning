package Feb16;

public class StaticAndInitializerBlock {
    static {
        System.out.println("static block executed");

        print();
    }
    {
        System.out.println("Instance initializer block 1");
    }

    {
        System.out.println("Instance initializer block 2");
    }

    public StaticAndInitializerBlock() {
        System.out.println("Class constructor");
    }

    public static void main(String[] args) {
        StaticAndInitializerBlock testObject = new StaticAndInitializerBlock();
        System.out.println("Main Method");
        testObject.display();
    }
    static void print(){
        System.out.println("print method called");
    }
    void display(){
        System.out.println("display method called");
    }
}
