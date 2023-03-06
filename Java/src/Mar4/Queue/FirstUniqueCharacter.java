package Mar4.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class FirstUniqueCharacter {

    public static String firstNonRepeatingCharacter(String str) {

        StringBuilder resultantString = new StringBuilder();

        int[] characterFrequency = new int[26];

        Queue<Character> queue = new LinkedList<Character>();

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            queue.add(ch);

            characterFrequency[ch - 'a']++;

            while (!queue.isEmpty()) {

                if (characterFrequency[queue.peek() - 'a'] > 1)

                    queue.remove();
                else {

                    resultantString.append(queue.peek() + " ");

                    break;
                }
            }

            // if there is no non-repeating character
            if (queue.isEmpty())
                resultantString.append("-1 ");
        }

        return resultantString.toString();
    }

    public static void main(String[] args) {

        String str = "aabcbcd";

        String result = firstNonRepeatingCharacter(str);

        System.out.println(result);

        System.out.println(firstUniqChar(str));

        if (firstUniqChar(str) != -1)

        System.out.println(str.charAt(firstUniqChar(str)));
    }

    public static int firstUniqChar(String s) {

        int index = -1;

        int len = s.length();

        int i = 0;

        while(i < len){

            char c = s.charAt(i);

            int firstIndex = s.indexOf(c);

            int lastIndex = s.lastIndexOf(c);

            if(firstIndex == lastIndex){

                index = i;

                break;

            }
            i++;
        }
        return index;

    }
}

