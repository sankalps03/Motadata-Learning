package Feb15;
// Java Program to Illustrate Exception Handling
// with Method Overriding
// Where SuperClass doesn't declare any exception and
// SubClass declare Unchecked exception

 class SuperClass {
    void method(){

        System.out.println("Super Class");
    }
}
class SubClass extends SuperClass {
    // if we declare checked exception in subclass method here we get a compilation error

    void method() throws  ArithmeticException{
        System.out.println("Sub class");
    }
}
public class ExceptionWithMethodOverriding {
    public static void main(String[] args){

        SuperClass testObject = new SubClass();

        testObject.method();
    }

}
