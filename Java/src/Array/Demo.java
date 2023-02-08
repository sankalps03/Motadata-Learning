package Array;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args){
        Demo arr = new Demo();
        arr.arrays();
    }
    static void  arrays(){
        int[] scores  = new int[5];

        Integer[] marks = new Integer[]{88, 78, 98,99, 89};

        int[] percent = {67,87, 78, 90, 85};

        scores[0]= 90;

        scores[1]= 91;

        scores[2]= 92;

        scores[3]= 93;

        scores[4]= 94;

        List<Integer> list = Arrays.asList(marks);

        Arrays.sort(percent);
        int key = 78;

        System.out.println(scores);

        System.out.println("maths: "+scores[0]);

        System.out.println("physics: "+scores[1]);

        System.out.println("chemistry: "+scores[2]);

        System.out.println("FOP: "+scores[3]);

        System.out.println("OS: "+scores[4]);

        System.out.println(marks);

        System.out.println("marks "+ list);

        System.out.println(percent);

        System.out.println(key+" found at index "+ Arrays.binarySearch(percent,key));

        System.out.println("length "+ scores.length);

    }
}
