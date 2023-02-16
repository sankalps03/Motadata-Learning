package Feb16;

class Parent
{
    public void method1()
    {
        System.out.println("Class Parent method");
    }
}
 class Child extends Parent {

    public void method2() {

        System.out.println("Class Child method");
    }
}
public class SingleInheritance {

        public static void main(String args[])
        {
            Child testObj = new Child();

            testObj.method1();

            testObj.method2();
        }
}
