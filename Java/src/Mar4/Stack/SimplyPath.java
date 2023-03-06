package Mar4.Stack;

import java.util.Stack;

public class SimplyPath {

    public static void main(String[]args){

        System.out.println(simplify("home/sankalp/Documents"));
    }

    static String simplify (String path){

        String [] pathArray = path.split("/");

        Stack <String> pathStack= new Stack<String>();

        for (String string : pathArray){

            if (string.equals("..")){

                if(!pathStack.isEmpty()){

                    pathStack.pop();

                }
            } else if (string.equals(".") || string.equals("") || string.equals(" ")) {

                continue;
                
            }
            else {

                pathStack.push("/"+string);
            }
        }
        String newPath = new String();

        while (!pathStack.isEmpty()){

            newPath = pathStack.pop()+newPath;
        }
        if (newPath.equals("")){
            return "/";
        }
        return newPath;
    }
}
