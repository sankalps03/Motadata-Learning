package Feb23.LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreateLinkedList {
    public static void main(String[] args) {

        try {

            LinkedList<String> friends = new LinkedList<>();

            friends.add("Rajeev");

            friends.add("John");

            friends.add("David");

            friends.add("Chris");

            System.out.println("Initial LinkedList : " + friends);

            friends.add(3, "Lisa");

            friends.addFirst("Steve");

            friends.addLast("Jennifer");

            System.out.println("After add LinkedList : " + friends);


            List<String> familyFriends = new ArrayList<>();

            familyFriends.add("Jesse");

            familyFriends.add("Walt");

            friends.addAll(familyFriends);

            System.out.println("After addAll(familyFriends) : " + friends);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
