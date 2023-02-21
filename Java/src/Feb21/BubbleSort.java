package Feb21;

public class BubbleSort {

    public static void main(String[] args){

        int array [] = {64, 34, 25, 12, 22, 11, 90};
        int length = array.length;

        bubbleSort(array,length);

        System.out.println(" Sorted Array : ");

        printArray(array,length);

    }

    static void bubbleSort(int array[], int length){

        int outerCount, innerCount, tempVariable;
        boolean swapped;

        for (outerCount= 0; outerCount< length; outerCount++ ){

            swapped= false;

            printArray(array,length);

            for (innerCount= 0; innerCount < length-outerCount-1; innerCount++){

                if (array[innerCount] > array[innerCount+1]){

                    tempVariable = array[innerCount];

                    array[innerCount] = array[innerCount+1];

                    array[innerCount+1] = tempVariable;

                    swapped= true;
                }
            }
            if (swapped == false){

                // if there is no swapping done that means the array is sorted

                break;
            }
        }

    }
    static void printArray(int array[], int length){

        int iterator;

        for (iterator= 0; iterator< length ; iterator++){

            System.out.print(array[iterator]+ " ");
        }
        System.out.println();
    }
}
