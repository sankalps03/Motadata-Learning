package Feb23.LinkedList;

import java.util.LinkedList;

public class RemoveElementsFromLinkedList {
    public static void main(String[] args) {
        try {

            LinkedList<String> programmingLanguages = new LinkedList<>();

            programmingLanguages.add("Assembly");

            programmingLanguages.add("Fortran");

            programmingLanguages.add("Pascal");

            programmingLanguages.add("C");

            programmingLanguages.add("C++");

            programmingLanguages.add("Java");

            programmingLanguages.add("C#");

            programmingLanguages.add("Kotlin");

            System.out.println("Initial LinkedList = " + programmingLanguages);

            String element = programmingLanguages.removeFirst();

            System.out.println("Removed the first element " + element + "  " + programmingLanguages);

            element = programmingLanguages.removeLast();

            System.out.println("Removed the last element " + element + "  " + programmingLanguages);


            boolean isRemoved = programmingLanguages.remove("C#");

            if (isRemoved) {

                System.out.println("Removed C#  " + programmingLanguages);
            }
            programmingLanguages.removeIf(programmingLanguage -> programmingLanguage.startsWith("C"));

            System.out.println("Removed elements starting with C  " + programmingLanguages);

            programmingLanguages.clear();

            System.out.println("Cleared the LinkedList  " + programmingLanguages);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
