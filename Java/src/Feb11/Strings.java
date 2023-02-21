package Feb11;

public class Strings {
    public static void main(String[] args) {
        Strings object = new Strings();

        object.stringConcatenate("Sankalp",null);

        String sLiteral1 = "sankalp";

        String sLiteral2 = "sankalp";

        object.stringConcat(sLiteral1,sLiteral2);

        String sObj1= new String("sankalp");

        String sObj2 = new String("sankalp");

        object.comparison(sObj1,sObj2);
        object.comparison(sLiteral1,sLiteral2);
    }
    int stringLength(String string){

        if (string == null){

            return 0;
        }

        else {

        int length = string.length();

        return length;

        }
    }

    String stringConcatenate(String string1, String string2){

        // this method will also concatenate null value as "null" string ex: "sankalp"+ null string = sankalpnull
        String concatenated = string1+string2;

        return concatenated;

    }
    void stringConcat(String string1, String string2){

        // this uses concat method for concatenation throws null pointer exception
        try {

        if(string1 == null || string2 == null){

            System.out.println("null strings can't be concatenated using concat() method");
        }
        else {

            // here strings are concatenated and saved in new string
            String concatenated = string1.concat(string2);

            // this returns old value of string1 as the new string is created while concatenating, but it's not assigned to string1
            string1.concat(string2);

            System.out.println("concat string1"+ string1);

            System.out.println("concatenated "+concatenated);
        }

        }catch (NullPointerException nullPointerException){

            System.out.println(nullPointerException);
        }
    }

    void comparison(String string1, String string2){

        //== checks if the reference to string objects are equal or not.
        // Here, string1 and string2 are two different references
        boolean result1 = (string1 == string2);

        System.out.println("Using == operator: " + result1);

        // using equals() method
        //equals() checks if the content of the string object are equal
        boolean result2 = string1.equals(string2);

        System.out.println("Using equals(): " + result2);

    }


}
