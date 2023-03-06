package Mar6.HashTables;

import java.util.*;

public class DuplicatesInArray {

    public static void main(String[] args){

        int[] nums ={4,3,2,7,8,2,3,1};

        System.out.println(findDuplicates(nums));


    }

    public static List<Integer> findDuplicates(int[] nums) {

        List<Integer> ans = new ArrayList<>();

        Set<Integer> map = new HashSet<>();

        for(int i=0;i<nums.length;i++)
        {
            if(!map.contains(nums[i]))
            {
                map.add(nums[i]);
            }
            else
            {
                ans.add(nums[i]);
            }

        }
        return ans;
    }
}
