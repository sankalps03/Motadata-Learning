package Feb27.TreeSet;

import java.util.TreeSet;

public class RemoveTreeSet {

    public static void main(String[] args) {

        try {

            TreeSet<Integer> numbers = new TreeSet<>();

            numbers.add(10);

            numbers.add(15);

            numbers.add(20);

            numbers.add(25);

            numbers.add(30);

            numbers.add(42);

            numbers.add(49);

            numbers.add(50);

            System.out.println("numbers TreeSet : " + numbers);

            boolean isRemoved = numbers.remove(49);

            if (isRemoved) {

                System.out.println("After Removing 49 : " + numbers);
            }

            numbers.removeIf(number -> number % 3 == 0);

            System.out.println("After removeIf() : " + numbers);


            Integer num = numbers.pollFirst();

            System.out.println("Removed first element " + num + " from the TreeSet : " + numbers);

            num = numbers.pollLast();

            System.out.println("Removed last element " + num + " from the TreeSet : " + numbers);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
