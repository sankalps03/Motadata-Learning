package Mar7;

import java.util.Stack;

public class BackSpaceStringCompare {

    public static void main(String[] args){

        String text1 = "ad#c" ;

        String text2 ="ab#c";

        System.out.println(backspaceCompare(text1,text2));

    }

    public static boolean backspaceCompare(String s, String t) {

        boolean equal = false;
        try {

             equal= stacked(s).equals(stacked(t));

        }catch (Exception exception){

            exception.printStackTrace();
        }
        return equal;
    }

    public static String stacked(String S){

        Stack<Character> text = new Stack();

        try {

            for (char element : S.toCharArray()) {

                if (element != '#') {

                    text.push(element);
                } else if (!text.empty()) {

                    text.pop();
                }
            }
        }catch (Exception exception){

            exception.printStackTrace();
        }
        return String.valueOf(text);
    }
}
