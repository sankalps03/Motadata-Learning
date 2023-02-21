package Feb15;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class TryWithResources {
    public static void main(String[] args) {

        try (FileOutputStream stream = new FileOutputStream("file.txt"); BufferedReader bReader = new BufferedReader(new FileReader("sankalp.txt"))) {

            String text;

            while ((text = bReader.readLine()) != null) {

                byte array[] = text.getBytes();

                stream.write(array);

            }
            System.out.println("File content copied");
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
