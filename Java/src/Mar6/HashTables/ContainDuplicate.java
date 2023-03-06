package Mar6.HashTables;

import java.util.HashMap;

public class ContainDuplicate {

    public static void main(String[] args){

       int[] nums = {1,2,3,1};

        System.out.println(containsDuplicate(nums));

    }

    public static boolean containsDuplicate(int[] nums) {

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++)
        {
            if(!map.containsKey(nums[i]))
            {
                map.put(nums[i],1);
            }
            else
            {
                return true;
            }
        }
        return false;
    }

}
