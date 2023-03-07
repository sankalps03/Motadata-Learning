package Mar7;

public class StrictlyIncreasingArray {

    public static void main(String[] args){

        int [] numbers = {1,20,10,5,7};

        System.out.println(canBeIncreasing(numbers));
    }


    public static boolean canBeIncreasing(int[] nums) {

        boolean removed = false;

        try{

        for (int iterator = 1; iterator < nums.length; ++iterator)

            if (nums[iterator - 1] >= nums[iterator]) {

                if (removed)

                    return false;

                removed = true;

                if (iterator > 1 && nums[iterator - 2] >= nums[iterator])

                    nums[iterator] = nums[iterator - 1];
            }
        }
        catch (Exception exception){

            exception.printStackTrace();
        }

        return true;
    }
}
