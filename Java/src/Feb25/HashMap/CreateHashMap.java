package Feb25.HashMap;

import java.util.HashMap;
import java.util.Map;

public class CreateHashMap {
    public static void main(String[] args) {

        try {

            Map<String, Integer> numberMapping = new HashMap<>();

            numberMapping.put("One", 1);

            numberMapping.put("Two", 2);

            numberMapping.put("Three", 3);

            numberMapping.putIfAbsent("Four", 4);

            System.out.println(numberMapping);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
