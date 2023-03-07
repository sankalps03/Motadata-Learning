package Mar7;

public class EqualDigitCountValue {

    public static void main(String[] args){

        String num = "1210";

        System.out.println(digitCount(num));

    }
    public static boolean digitCount(String num) {

        try {

        int[] count = new int[10];

        for (char element : num.toCharArray()){

            ++count[element - '0'];
        }

        for (int iterator = 0; iterator < num.length(); ++iterator){


            if (count[iterator] != num.charAt(iterator) - '0'){


                return false;
            }
        }
        }catch (Exception exception){

            exception.printStackTrace();
        }

        return true;
    }
}
