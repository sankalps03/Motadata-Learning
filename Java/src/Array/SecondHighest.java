package Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondHighest {

    public static void main(String[] args){

        InputStreamReader input =new InputStreamReader(System.in);

        BufferedReader br = new BufferedReader(input);
        try {

        int [] array = new int[10];

        System.out.println("Enter the numbers followed by spaces");

        String line = br.readLine();

        String[] strs = line.split("");

        for(int i=0;i<10;i++)
            {
                array[i]=Integer.parseInt(strs[i]);
            }
        findSecondHighest(array);

        }
        catch (NumberFormatException nfe){
            System.out.println(nfe);
        }
        catch (IOException io){
            System.out.println(io);
        }
        finally {

            try {

            br.close();

            }
            catch (Exception e){}
        }
    }
    private static int findSecondHighest(int[] array) {
        int highest = Integer.MIN_VALUE;

        int secondHighest = Integer.MIN_VALUE;

        for (int i : array) {

            if (i > highest) {

                secondHighest = highest;

                highest = i;

            } else if (i > secondHighest) {

                secondHighest = i;
            }
        }
        System.out.println("secondhighest:" + secondHighest);
        return secondHighest;
    }
}
