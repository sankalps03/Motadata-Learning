package objectAndClass;

public class CurrencyConverter {
    double rupee = 80.59;
    double dhiram = 3.00;
    double real = 4;
    double  chilenPeso =595;
    double mexicanPeso =18;
    double yen = 107;
    double australlian$ = 2;

    void printCurrencies(){

        System.out.println("Rupee: "+rupee+ "\n" +"Dhiram: "+ dhiram+"\n" +"Real: "+real+"\n"
                + "Clilen Peso: "+chilenPeso+"\n"+"Mexican Peso: "+mexicanPeso+"\n"+"Yen: "+yen+"\n"+"Australlian$: "+australlian$);
    }

    public static void main (String[] args){

        CurrencyConverter cc = new CurrencyConverter();
        cc.printCurrencies();
    }

}
