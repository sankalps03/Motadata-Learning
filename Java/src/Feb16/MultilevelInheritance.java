package Feb16;

class Grand
{
    public void method1()
    {
        System.out.println("Class Grand method");
    }
}
 class Parent1 extends Grand
{
    public void method2()
    {
        System.out.println("Class Parent method");
    }
}
class Child1 extends Parent1
{
    public void method3()
    {
        System.out.println("Class Child method");
    }

}
public class MultilevelInheritance {
    public static void main(String [] args)
    {
        Child1 childTestObj = new Child1();

        Parent1 parentTestObj = new Parent1();

        childTestObj.method3();

        childTestObj.method2();

        childTestObj.method1();

        parentTestObj.method2();

        parentTestObj.method1();


    }
}
