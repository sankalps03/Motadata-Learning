package Variables;

public class PrintAllVariables {
    boolean bool;
    byte by;
    char ch;
    short sh;
    int in;
    long lg;
    float fl;
    double dle;

    public static void main(String[] args){

        PrintAllVariables pV = new PrintAllVariables();

        pV.printAll();
    }

    void printAll(){

        System.out.println("boolean: "+bool+"\n"+ "byte "+by+"\n"+"short "+sh+"\n"
        +"int "+in+"\n"+"char "+ch+"\n"+"long "+lg+"\n"+"float "+fl+"\n"+"double "+fl+"\n");
    }

}
