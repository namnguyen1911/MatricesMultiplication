import java.util.Random;
import java.util.Scanner;

public class MatricesMultiplication {
    public static void main(String[] args) {
        //Declaration and Initialization
        Scanner scan = new Scanner(System.in);
        int size = 0;
        ClassicalMultiplication classicalMultiply = new ClassicalMultiplication();
        long startTime = 0, endTime = 0, duration = 0;
        int range = 0;


        //Prompt user enter the size of matrices
        System.out.print("Enter the size of the matrix: ");
        size = scan.nextInt();
        
        
        //Remove the new line character in the input buffer
        scan.nextLine();

        //Prompt user enter the sample size of the test
        System.out.print("Enter the sample size of the test: ");
        range = scan.nextInt();

        // Sqaure Matrices generation
        int[][] matrix1 = matricesGenerator(size);
        int[][] matrix2 = matricesGenerator(size);

        //Display the matrix
        System.out.println("Matrix 1:");
        matricesDisplay(matrix1);
        System.out.println("Matrix 2:");
        matricesDisplay(matrix2);
        System.out.println("The result by using classical multiplication: ");

        //Check if two matrices can be multiplied
        if(classicalMultiply.classicalMultiplication(matrix1, matrix2) == null) {
            System.out.println("Two input matrices can not be multiplied");
        }
        else {
            //Record the starting time of the algorithm
            startTime = System.nanoTime();

            //Run the algorithm
            for(int count = 0; count < range; count++) {
                classicalMultiply.classicalMultiplication(matrix1, matrix2);
            }

            //Display the result of multiplication on the screen
            matricesDisplay(classicalMultiply.classicalMultiplication(matrix1, matrix2));
    
            //Record the ending time of the algorithm
            endTime = System.nanoTime();

            //Calculate the duration of the execution
            duration = endTime - startTime;

            //Display the total and average time of execution on the screen
            System.out.println("The total execution time of " + range + " sample size (nano time) is: " + duration + " nano time");
            System.out.println("The total execution time of " + range + " sample size (milliseconds) is: " + (duration / 1000000.0) + " milliseconds");
            System.out.println("The average execution time of " + range + " sample size (milliseconds) is: " + (duration / range) / 1000000.0 + " milliseconds");
        }
        
    }

    /** 
     * function generate a two-dimensional array with the same numbers of columns and rows (square matrix)
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