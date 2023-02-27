package Feb27.LinkedHashMap;

import java.util.LinkedHashMap;

public class AccessElements {
    public static void main(String[] args) {

        try {

            LinkedHashMap<String, Integer> numbers = new LinkedHashMap<>();

            numbers.put("One", 1);

            numbers.put("Two", 2);

            numbers.put("Three", 3);

            System.out.println("LinkedHashMap: " + numbers);

            System.out.println("Key/Value mappings: " + numbers.entrySet());

            System.out.println("Keys: " + numbers.keySet());

            System.out.println("Values: " + numbers.values());

            int value1 = numbers.get("Three");

            System.out.println("Returned Number: " + value1);

            int value2 = numbers.getOrDefault("Five", 5);

            System.out.println("Returned Number: " + value2);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
