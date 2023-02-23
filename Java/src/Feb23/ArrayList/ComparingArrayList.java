package Feb23.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class ComparingArrayList {

    public static void main(String args[])
    {

        ArrayList<String> firstList=new ArrayList<>();

        firstList.add("Apple");

        firstList.add("Pears");

        firstList.add("Guava");

        firstList.add("Mango");

        System.out.println(firstList);

        List<String> secondList=new ArrayList<String>();

        secondList.add("Apple");

        secondList.add("Guava");

        secondList.add("Mango");

        secondList.add("Pears");

        System.out.println(secondList);

        System.out.println(firstList.equals(secondList));

        secondList.add("Papaya");

        System.out.println(firstList.equals(secondList));
    }
}
