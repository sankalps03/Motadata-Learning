package Mar6.HashTables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

    public static void main (String[] args){

        String[] inputArray = {"eat","tea","tan","ate","nat","bat"};

        groupAnagrams(inputArray);

    }
    public static String genKey(String str){

        char[] arr = str.toCharArray();

        Arrays.sort(arr);

        return new String(arr);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        if(strs.length == 0)

            return result;

        HashMap<String,List<String>> map = new HashMap<>();

        for(int i=0; i< strs.length; i++){

            String key = genKey(strs[i]);

            if(!map.containsKey(key)){

                map.put(key,new ArrayList<String>());
            }

            map.get(key).add(strs[i]);
        }

        result.addAll(map.values());

        System.out.println(result);

        return result;
    }
}
