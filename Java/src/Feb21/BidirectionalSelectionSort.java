package Feb21;

public class BidirectionalSelectionSort {

    public static void main(String[] args)
    {
        int array[] = { 23, 78, 45, 8, 32, 56, 1 };

        int length = array.length;

        minMaxSelectionSort(array, length);

        System.out.printf("Sorted array:\n");

        printArray(array,length);
    }

    static void minMaxSelectionSort(int[] array, int n)
    {
        for (int start = 0, end = n - 1; start < end; start++, end--) {

            int min = array[start], max = array[start];

            int min_index = start, max_index = start;

            for (int k = start; k <= end; k++) {

                if (array[k] > max) {

                    max = array[k];

                    max_index = k;
                }

                else if (array[k] < min) {

                    min = array[k];

                    min_index = k;
                }
            }

            // shifting the min.
            swap(array, start, min_index);

            // Shifting the max. The equal condition
            // happens if we shifted the max to arr[min_i]
            // in the previous swap.
            if (array[min_index] == max) {

                swap(array, end, min_index);
            }
            else {

                swap(array, end, max_index);
            }
        }
    }

    static int[] swap(int []array, int i, int j)
    {
        int temp = array[i];

        array[i] = array[j];

        array[j] = temp;

        return array;
    }

    static void printArray(int array[], int length){

        int iterator;

        for (iterator= 0; iterator< length ; iterator++){

            System.out.print(array[iterator]+ " ");
        }
        System.out.println();
    }



}
