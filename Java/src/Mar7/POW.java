package Mar7;

public class POW {

    public static void main(String[] args){

        try {

        System.out.println(mmyPow(14.00, 12));

        System.out.println(myPow(34.00, 12));

        }
        catch (Exception exception){

            exception.printStackTrace();
        }

    }
    public static double myPow(double base, int expo) {

        double power = 1;

        if(expo >= 0){

            if (expo == 0){
                return 1;
            } else if (base == 0 && expo!=0) {
                return 0;

            }else {

                for (int iterator = 0; iterator < expo; ++iterator) {

                    power = power * base;
                }
                return power;
            }
        }
        else {
            for(int iterator =expo ; iterator < 0; ++iterator){

            power = power/base;
        }
        return power;
        }

    }

    public static double mmyPow(double base, int expo) {
        if (expo == 0){

            return 1;
        }
        if (expo < 0){

            return 1 / mmyPow(base, -expo);
        }
        if (expo % 2 == 1){
            return base * mmyPow(base, expo - 1);
        }
        return mmyPow(base * base, expo / 2);
    }
}

