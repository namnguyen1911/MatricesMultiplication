import java.util.Random;
import java.util.Scanner;

public class MatricesMultiplication {
    public static void main(String[] args) {
        //Declaration and Initialization
        Scanner scan = new Scanner(System.in);
        int size = 0;
        ClassicalMultiplication classicalMultiply = new ClassicalMultiplication();

        //Prompt user enter the size of matrices
        System.out.print("Enter the size of the matrix: ");
        size = scan.nextInt();

        //Matrix generation
        int[][] matrix1 = matricesGenerator(size);
        int[][] matrix2 = matricesGenerator(size);

        //Display the matrix
        System.out.println("Matrix 1:");
        matricesDisplay(matrix1);
        System.out.println("Matrix 2:");
        matricesDisplay(matrix2);
        System.out.println("The result by using classical multiplication: ");
        matricesDisplay(classicalMultiply.classicalMultiplication(matrix1, matrix2));
    }

    /** 
     * function generate a two-dimensional array 
     * @param size is the size of row and column of a two-dimensional array
     * @return return a two-dimensional array
    */
    static int[][] matricesGenerator(int size) {
        //Declaration
        int[][] matrix = new int[size][size];
        Random rand = new Random();

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                matrix[row][col] = rand.nextInt(10);
            }
        }

        return matrix;
    }

    /**
     * function displays a two-dimensional array
     * @param matrix is an two-dimensional array
     */
    static void matricesDisplay(int[][] matrix) {
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}