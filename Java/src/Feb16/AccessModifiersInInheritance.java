package Feb16;

 class BaseClass{
     private int num = 30;
     protected int num2 = 50;

     private void method1(){
         System.out.println("Base class method1");
         System.out.println("num1: "+ num+" num2: "+ num2);
     }
     protected void method2(){
         System.out.println("Base class method2");
         System.out.println("num1: "+ num+" num2: "+ num2);

     }
}
class Derived extends BaseClass{

}
public class AccessModifiersInInheritance {

     public static void main(String[] args){
         BaseClass baseTestObject = new BaseClass();

         Derived derivedTestObject= new Derived();

         // The private method or variable of the class can't be accessed out of the class
         // even using object of same in other class
         baseTestObject.num2 = 45;

         baseTestObject.method2();

         derivedTestObject.num2 = 60;

         derivedTestObject.method2();
     }
}
