package Feb27.HashSet;

import java.util.HashSet;
import java.util.Set;

public class CreateHashSet {
    public static void main(String[] args) {

        try {

            Set<String> daysOfWeek = new HashSet<>();

            daysOfWeek.add("Monday");

            daysOfWeek.add("Tuesday");

            daysOfWeek.add("Wednesday");

            daysOfWeek.add("Thursday");

            daysOfWeek.add("Friday");

            daysOfWeek.add("Saturday");

            daysOfWeek.add("Sunday");

            daysOfWeek.add("Monday");

            System.out.println(daysOfWeek);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
