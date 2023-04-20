package Feb22;

import java.util.Random;

public class MergeSort {

    public static void main(String[] args) {

        try {

            Random random = new Random();

            int[] array = new int[8];

            for (int index = 0; index < array.length; index++) {

                array[index] = random.nextInt(1000) + 1;
            }
            printArray(array);

            sort(array, 0, array.length - 1);

            System.out.println("Sorted Array");

            printArray(array);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }


   static void merge (int array[], int left, int mid , int right){

        int sizeLeft = mid-left+1;

        int sizeRight = right-mid;

        int tempArrayRight[] = new int[sizeRight];

        int tempArrayLeft[] = new int[sizeLeft];

        for (int index=0; index< sizeLeft ; index++){

            tempArrayLeft[index] = array[left+index];

            printArray(tempArrayLeft);

            printArray(tempArrayRight);

            System.out.println("for 1");

            printArray(array);

        }
        for (int index=0; index< sizeRight ; index++){

            tempArrayRight[index] = array[mid+1+index];

            System.out.println("for 2");

            printArray(tempArrayLeft);

            printArray(tempArrayRight);

            printArray(array);

        }
        int index1 =0 ,index2 =0;

        int mergedIndex = left;

        while (index1< sizeLeft && index2 < sizeRight){

            if (tempArrayLeft[index1] <= tempArrayRight[index2] ){

                array[mergedIndex] = tempArrayLeft[index1];

                index1++;

                System.out.println("while 1 if");

                printArray(array);

            }
            else {

                array[mergedIndex] = tempArrayRight[index2];

                index2++;

                System.out.println("while 1 else");

                printArray(array);
            }
            mergedIndex++;
        }
        while (index1 < sizeLeft){

            array[mergedIndex] = tempArrayLeft[index1];

            index1++;

            mergedIndex++;

            System.out.println("while left");

            printArray(array);
        }
        while (index2 < sizeRight){

            array[mergedIndex] = tempArrayRight[index2];

            index2++;

            mergedIndex++;

            System.out.println("while right");

            printArray(array);
        }
    }
    static void sort(int array[], int left, int right){

        if (left<right){
            int mid = left+(right-left)/2;

            sort(array,left,mid);

            sort(array,mid+1,right);

            merge(array,left,mid,right);
        }
    }

    static void printArray(int array[]){

        int iterator;

        int length = array.length;

        for (iterator= 0; iterator< length ; iterator++){

            System.out.print(array[iterator]+ " ");
        }
        System.out.println();
    }
}
