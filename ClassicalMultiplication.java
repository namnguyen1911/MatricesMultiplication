public class ClassicalMultiplication {

    /**
     * function calculate multiplication of two matrices
     * @param matrix1 is the first two-dimentional array (matrix 1)
     * @param matrix2 is the second two-dimentional array (matrix 2)
     * @return a matrix which is the production of matrix 1 and matrix 2
     */
    public int[][] classicalMultiplication(int[][] matrix1, int [][]matrix2) {
        int sum = 0;
        //Check if the two matrices are valid
        //If not, return null
        if(matrix1[0].length != matrix2.length) {
            return null;
        }
        else {
            int[][] matrix = new int[matrix1.length][matrix2[0].length];

            for(int row = 0; row < matrix1.length; row++) {
                for(int col = 0; col < matrix2[0].length; col++) {
                    sum = 0;
                    for(int pos = 0; pos < matrix1[0].length; pos++) {
                        sum += (matrix1[row][pos] * matrix2[pos][col]);
                    }
                    matrix[row][col] = sum;
                }
            }
            return matrix;
        }
    }

}
