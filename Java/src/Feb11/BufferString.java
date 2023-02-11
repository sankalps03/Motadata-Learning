package Feb11;

public class BufferString {
    public static void main(String[] args){
        StringBuffer stringBuffer = new StringBuffer("sankalp");

        StringBuffer stringBuffer1 = new StringBuffer("sankalp");

        BufferString buffer = new BufferString();

        buffer.comparison(stringBuffer,stringBuffer1);

        buffer.stringAppend(stringBuffer,stringBuffer1);

        buffer.stringLength(stringBuffer1);


    }
    int stringLength(StringBuffer string){

        if (string == null){

            return 0;
        }

        else {

            int length = string.length();

            return length;

        }
    }

    void stringAppend(StringBuffer string1, StringBuffer string2){
        //here strings are concatenated and saved in string1
        string1.append(string2);
        System.out.println("concat string1 : "+ string1);

        // here strings are concatenated and saved in new string
        String concatenated = String.valueOf(string1.append(string2));



        System.out.println("concatenated : "+concatenated);
        }


    void comparison(StringBuffer string1, StringBuffer string2){

        //== checks if the reference to string objects are equal or not.
        // Here, string1 and string2 are two different references
        boolean result1 = (string1 == string2);

        System.out.println("Using == operator: " + result1);

        // using equals() method
        //equals() checks if the content of the string object are equal
        boolean result2 = string1.equals(string2);

        System.out.println("Using equals(): " + result2);

        boolean result3 = (string1.toString() == string2.toString());

        System.out.println("comparing converting to string == : "+ result3);

        boolean result4 = (string1.toString().equals(string2.toString()));

        System.out.println("comparing converting to string equals: "+ result4);

    }
}
