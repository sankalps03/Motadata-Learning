package Feb21;

public class SelectionSort {

    public static void main(String[] args){

        int array [] = {64, 34, 25, 12, 22, 11, 90};
        int length = array.length;

        selectionSort(array,length);

        System.out.println(" Sorted Array : ");

        printArray(array,length);

    }

    static void selectionSort(int array[], int length){

        int outerCount, innerCount, tempVariable;

        for (outerCount= 0; outerCount< length- 1; outerCount++ ){

            printArray(array,length);

            int minIndex = outerCount;

            for (innerCount= outerCount+1; innerCount < length; innerCount++){

                if (array[innerCount] < array[minIndex]){

                    minIndex = innerCount;

                }
            }
            tempVariable = array[outerCount];

            array[outerCount] = array[minIndex];

            array[minIndex] = tempVariable;
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
