package Feb15;

import java.io.File;
import java.io.FileReader;

public class FileNotFoundExceptionDemo {
    public static void main(String[] args){

        File file = new File("file.txt");

        FileReader reader = null;
        try
        {
            reader = new FileReader(file);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }


    }
}
