package Feb23.ArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnmodifiableArrayList {
    public static void main(String[] args) {

        try {

            List<String> fruitList = new ArrayList<String>();

            fruitList.add("Mango");

            fruitList.add("Banana");

            fruitList.add("Apple");

            fruitList.add("Strawberry");

            fruitList.add("Pineapple");

            List<String> unmodifiableList = Collections.unmodifiableList(fruitList);

            // this will throw unsupported operation exception
            unmodifiableList.add("Guava");

            System.out.println(fruitList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
