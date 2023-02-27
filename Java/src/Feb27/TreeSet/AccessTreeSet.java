package Feb27.TreeSet;

import java.util.TreeSet;

public class AccessTreeSet {

    public static void main(String[] args) {

        try {

            TreeSet<String> students = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

            students.add("Julia");

            students.add("Robert");

            students.add("Mark");

            students.add("Steven");

            System.out.println("Students TreeSet : " + students);

            System.out.println("Number of elements in the TreeSet : " + students.size());

            String name = "Julia";

            if (students.contains(name)) {

                System.out.println("TreeSet contains the element : " + name);

            } else {

                System.out.println("TreeSet does not contain the element : " + name);
            }

            System.out.println("First element : " + students.first());

            System.out.println("Last element : " + students.last());

            name = "Robert";

            System.out.println("Element just greater than " + name + " : " + students.higher(name));

            System.out.println("Element just lower than " + name + " : " + students.lower(name));

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
