package Mar4.Stack;

import java.util.Stack;

public class ValidParenthesis {
    public static void main(String[] args){

        System.out.println(checkParenthesis("(((([[]]{{}}))))"));

    }

    static boolean checkParenthesis(String parenthesis){

        Stack<Character> validator = new Stack<Character>();

        for (int iterator =0 ; iterator < parenthesis.length();iterator++ ){

            char element = parenthesis.charAt(iterator);

            if ( element == '(' || element == '[' || element == '{') {

                validator.push(element);

                continue;
            } else if ((element == ')') && !validator.isEmpty() || validator.peek() == '[')
                {
                    validator.pop();

                }
             else if ((element == ']') && !validator.isEmpty() || validator.peek() == '[') {

                validator.pop();
            }
            else if ((element == '}') && !validator.isEmpty() || validator.peek() == '}') {

                validator.pop();

            }else {
                return false;
            }
        }

        return validator.isEmpty();
    }
}
