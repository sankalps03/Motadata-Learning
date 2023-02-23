package Feb23.ArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ReverseArraylist {

    public static void main(String[] args) {

        try {

            List<String> list = new ArrayList<>();

            list.add("Mango");

            list.add("Banana");

            list.add("Mango");

            list.add("Apple");

            System.out.println("Before Reversing");

            System.out.println(list);

            Collections.reverse(list);

            System.out.println("After Reversing");

            System.out.println(list);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
