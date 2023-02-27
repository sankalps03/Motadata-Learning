package Feb27.TreeSet;

import java.util.SortedSet;

import java.util.TreeSet;

public class CreateTreeSet {

    public static void main(String[] args) {

        try {

            SortedSet<String> fruits = new TreeSet<>();

            fruits.add("Banana");

            fruits.add("Apple");

            fruits.add("Pineapple");

            fruits.add("Orange");

            System.out.println("Fruits Set : " + fruits);

            fruits.add("Apple");

            System.out.println("After adding duplicate element \"Apple\" : " + fruits);

            fruits.add("banana");

            System.out.println("After adding \"banana\" : " + fruits);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
