package Feb16;

class Parent2 {
    String name;

    static void method()
    {
        System.out.println("Method from Parent");
    }
}
class Child2 extends Parent2 {
    int id;

    static void method()
    {
        System.out.println("Method from Child");
    }
}
public class UpCasting {

    public static void main(String[] args)
    {
        // Upcasting
        Parent2 refObject = new Child2();

        refObject.name = "Sankalp";

        System.out.println(refObject.name);

        //parent class method is overridden method hence this will be executed
        refObject.method();

        // Trying to Down casting Implicitly
        // Child c = new Parent(); - > compile time error

        // Downcasting Explicitly
        Child2 downcastedObj = (Child2)refObject;

        downcastedObj.id = 1;

        System.out.println(downcastedObj.name);

        System.out.println(downcastedObj.id);

        downcastedObj.method();
    }
}
