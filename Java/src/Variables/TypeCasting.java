package Variables;

public class TypeCasting {
    // byte -> short -> char -> int -> long -> float -> double (Automatically)
    //double -> float -> long -> int -> char -> short -> byte (Done manually)
    byte by = 10;
    char ch = 'a';
    short sh= 24;
    int in= 44;
    long lg= 522234;
    float fl= 20.21f;
    double dle = 40.54635;

    public static void main(String[] args){
        TypeCasting tc = new TypeCasting();
        tc.wideCasting();
        tc.narrowCasting();

    }

    void wideCasting(){
        short s1 = by;

        System.out.println("byte to short :byte: "+by+" short: "+ s1);

        int i1 = sh;

        System.out.println("int to short :short: "+sh+" int: "+ i1);

        int i2 =ch;

        System.out.println("char to int :char: "+ch+" int: "+ i2);

        int i3 = by;

        System.out.println("byte to int :byte: "+by+" int: "+ i3);

        long l1 = in;

        System.out.println("int to long :int: "+in+" long: "+ l1);

        float f1 = lg;

        System.out.println("long to float :long: "+in+" float: "+ l1);

        double d1 = fl;

        System.out.println("float to double: float: "+ fl+" double: "+ d1);
    }

    void narrowCasting(){
        byte s1 = (byte) sh;

        System.out.println("short to byte :short: "+sh+" short: "+ s1);

        short i1 = (short) in;

        System.out.println("int to short :int: "+in+" int: "+ i1);

        char i2 = (char) in;

        System.out.println("int to char :int: "+in+" char: "+ i2);

        byte i3 = (byte) in;

        System.out.println("int to byte :int: "+in+" byte: "+ i3);

        int l1 = (int) lg;

        System.out.println("long to int :long: "+lg+" long: "+ l1);

        long f1 = (long) fl;

        System.out.println("long to float :long: "+in+" float: "+ l1);

        float d1 = (float) dle;

        System.out.println("double to float: double: "+ fl+" float: "+ d1);

    }

}
