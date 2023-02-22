package Feb22;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayListSort {

    public static void main(String[] args) {

        try {

            List<String> names = new ArrayList<>();

            names.add("Lisa");

            names.add("Jennifer");

            names.add("Mark");

            names.add("David");

            System.out.println("Names : " + names);

            // Sort an ArrayList using its sort() method. You must pass a Comparator to the ArrayList.sort() method.
            names.sort(new Comparator<String>() {
                @Override
                public int compare(String name1, String name2) {
                    return name1.compareTo(name2);
                }
            });

            System.out.println("Sorted Names : " + names);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
