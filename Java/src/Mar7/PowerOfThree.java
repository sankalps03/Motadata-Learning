package Mar7;

public class PowerOfThree {

    public static void main(String[] args){

        System.out.println(isPowerOfThree(27));
    }
    public static boolean isPowerOfThree(int number) {

        int remainder = 0;

        boolean isTrue = false;


        try {

            if (number != 0) {

                while (remainder == 0) {

                    if (number == 1) {

                        isTrue = true;

                        break;
                    }
                    remainder = number % 3;

                    number = number / 3;
                }
            }

        }
        catch (Exception exception){

            exception.printStackTrace();
        }
        return isTrue;
    }
}
