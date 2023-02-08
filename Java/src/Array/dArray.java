package Array;

public class dArray {
    public static void main(String[] args)
    {

        int[][] arr = new int[10][20];

        for (int i = 0; i < 10; i++){

            for (int j = 0; j < 20; j++){
                arr[i][j] =i+j;
                System.out.println("arr[" + i + "][" + j + "] = "
                        + arr[i][j]);
            }
        }
    }
}
