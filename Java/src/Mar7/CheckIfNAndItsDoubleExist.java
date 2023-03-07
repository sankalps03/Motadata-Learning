package Mar7;

import java.util.HashSet;
import java.util.Set;

public class CheckIfNAndItsDoubleExist {
    public static void main(String[] args) {

        int [] arr = {7,1,15,11};

        boolean ans=checkIfExist(arr);

        System.out.println(ans);
    }

    public static boolean checkIfExist(int[] arr) {

        Set<Integer> seen = new HashSet<>();

        try{

        for (int iterator : arr) {

            if (seen.contains(2 * iterator) || (iterator % 2 == 0 && seen.contains(iterator / 2)))

                return true;

            seen.add(iterator);
        }
        }catch (Exception exception){

            exception.printStackTrace();
        }
        return false;
    }

}
