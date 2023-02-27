package Feb27.LinkedHashMap;

import java.util.LinkedHashMap;

public class InsertElements {

    public static void main(String[] args) {

        try {

            LinkedHashMap<String, Integer> evenNumbers = new LinkedHashMap<>();

            evenNumbers.put("Two", 2);

            evenNumbers.put("Four", 4);

            System.out.println("Original LinkedHashMap: " + evenNumbers);

            evenNumbers.putIfAbsent("Six", 6);

            System.out.println("Updated LinkedHashMap(): " + evenNumbers);

            LinkedHashMap<String, Integer> numbers = new LinkedHashMap<>();

            numbers.put("One", 1);

            numbers.putAll(evenNumbers);

            System.out.println("New LinkedHashMap: " + numbers);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
