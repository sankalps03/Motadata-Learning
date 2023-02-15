package Feb15;
// Java Program to Illustrate Exception Handling with Method Overriding
// Where SuperClass declares an exception and SubClass declares a child exception
// of the SuperClass declared Exception

class ParentClass {
     void method() throws RuntimeException{

        System.out.println("Super Class");
    }
}
class ChildClass extends ParentClass {
    // if we declare exception that is not child of runtime exception in subclass method here we get a compilation error
    // subclass can also be declared without exception

     void method() throws  ArithmeticException{

        System.out.println("Sub class");
    }
}
public class ExceptionWithMethodOverriding2 {
    public static void main(String[] args){

        ParentClass testObject = new ChildClass();

        testObject.method();

    }

}
