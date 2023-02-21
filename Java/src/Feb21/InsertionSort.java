package Feb21;

public class InsertionSort {

    public static void main(String[] args){

        int array[] = {23, 78, 45, 8, 32, 56, 1} ;

        insertionSort(array);

        printArray(array);

    }

    static void insertionSort(int array[]){

        int length = array.length;

        for (int index = 1; index< length; index++){

            printArray(array);

            int key = array[index];

            int inner = index-1;

            while (inner >= 0 && array[inner] > key){

                array[ inner+1] = array[inner];
                inner -= 1;
            }
            array[inner+1] = key;
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
