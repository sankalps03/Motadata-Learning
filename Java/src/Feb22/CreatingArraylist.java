package Feb22;
import java.util.ArrayList;
import java.util.List;

public class CreatingArraylist {

    public static void main(String[] args) {

        try {

            List<String> animals = new ArrayList<>();

            // add method adds element at the end of the list

            animals.add("Lion");

            animals.add("Tiger");

            animals.add("Cat");

            System.out.println(animals);

            animals.add("Dog");

            System.out.println(animals);

            // Adding an element at a particular index in an ArrayList
            animals.add(2, "Elephant");

            System.out.println(animals);

        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
