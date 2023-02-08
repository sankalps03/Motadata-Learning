public abstract class base {

    static void hello(){
System.out.println("Hello a");
    }


    public static void main(String[]args){
        base b= new derived();
        b.hello();
    }
}
class derived extends base{
    static void hello(){
        System.out.println("hello b");
    }
}



