import java.util.Arrays;

public class Main {

    /*Method that adds two matrices together*/
    public static int[][] add2dArrays(int[][] first, int[][] second){
        //creating another matrix to store the sum of two matrices
        int [][] added = new int[first.length][second[0].length];
        //makes sure the matrices are the same length
        if(first.length==second.length && first[0].length==second[0].length) {
            for (int i = 0; i < first.length; i++) {
                for (int j = 0; j < first[0].length; j++) {
                    added[i][j] = first[i][j] + second[i][j];
                }
            }
            return added;
        }
        System.out.println("Matrix are not the same size");
        return null;
    }

    /*Method that multiplies two matrices together*/
    public static int[][] multiply2dArrays(int[][] first, int[][] second){
        //creating another matrix to store the product of two matrices
        int [][] multiply = new int[first.length][second[0].length];
        //makes sure the matrices fit the dimensions to multiply each other
        if(first[0].length==second.length) {
            for (int i = 0; i < first.length; i++) {
                for (int j = 0; j < second[0].length; j++) {
                    for (int k = 0; k < second.length; k++) {
                        multiply[i][j] += first[i][k] * second[k][j];
                    }
                }
            }
            return multiply;
        }
        System.out.println("The first Matrix does not have the same number of columns " +
                "as the second matrix has rows.");
        return null;
    }

    /*Driver method to test code*/
    public static void main(String[] args) {
        int a[][]={{12,3,4},
                   {2,4,3},
                   {3,4,5}};
        int b[][]={{1,3, 3},
                   {2,4, 3},
                   {1,2, 3}};
        System.out.println(Arrays.deepToString(add2dArrays(a, b)));
        System.out.println(Arrays.deepToString(multiply2dArrays(a, b)));
    }
}
