public class StrassensMultiplication {

    /**
     * function calculate multiplication of two matrices
     * @param matrix1 is the first two-dimentional array (matrix 1)
     * @param matrix2 is the second two-dimentional array (matrix 2)
     * @return a matrix which is the production of matrix 1 and matrix 2
     */
    public int[][] strassensMultiplication(int[][] matrix1, int[][] matrix2) {
        //Check if the sizes of matrices are power of 2
        //If not, return null
        //if(!isPowerOfTwo(matrix1.length) || !isPowerOfTwo(matrix2.length)) {
        //    System.out.println("Cannot use Strassen's method!");
        //    return null;
        //}
        //If the sizes of matrices are equal to 2
        //Use classical multiplication
        if (matrix1.length == 32 && matrix2.length == 32) {
            return classicalMultiplication(matrix1, matrix2);
        }
        //Divide the matrices into 8 smaller matrices
        else {

            //Size of smaller matrices
            int n = matrix1.length / 2;

            //A11
            int [][] a11 = copyMatrix(matrix1, 0, 0, n);
            //A12
            int [][] a12 = copyMatrix(matrix1, 0, n , n);
            //A13
            int [][] a21 = copyMatrix(matrix1, n , 0, n);
            //A14
            int [][] a22 = copyMatrix(matrix1, n , n , n);
            //A21
            int [][] b11 = copyMatrix(matrix2, 0, 0, n);
            //A22
            int [][] b12 = copyMatrix(matrix2, 0, n , n);
            //A23
            int [][] b21 = copyMatrix(matrix2, n , 0, n);
            //A24
            int [][] b22 = copyMatrix(matrix2, n , n , n);

            //M1
            int [][] m1 = strassensMultiplication(matricesAddition('+', a11, a22), matricesAddition('+', b11, b22));
            //M2
            int [][] m2 = strassensMultiplication(matricesAddition('+',a21,a22), b11);
            //M3
            int [][] m3 = strassensMultiplication(a11, matricesAddition('-', b12, b22));
            //M4
            int [][] m4 = strassensMultiplication(a22, matricesAddition('-', b21, b11));
            //M5
            int [][] m5 = strassensMultiplication(matricesAddition('+', a11, a12), b22);
            //M6
            int [][] m6 = strassensMultiplication(matricesAddition('-', a21, a11), matricesAddition('+', b11, b12));
            //M7
            int [][] m7 = strassensMultiplication(matricesAddition('-', a12, a22), matricesAddition('+', b21, b22));

            //M1 + M4 - M5 + M7
            int [][] c11 = matricesAddition('+',matricesAddition('-',matricesAddition('+',m1,m4),m5),m7);

            //M3 + M5
            int [][] c12 = matricesAddition('+',m3,m5);

            //M2 + m4
            int [][] c21 = matricesAddition('+',m2,m4);

            //M1 + M3 - M2 + M6
            int [][] c22 = matricesAddition('+',matricesAddition('-',matricesAddition('+',m1,m3),m2),m6);

            //return matrix;
            return joinMatrix(c11,c12,c21,c22);
        }
        
    }

    /**
     * function copies element from inputed matrix to other matrix
     * @param matrix is a two-dimensional array
     * @param rowIndex is the beginning row of index
     * @param colIndex is the beginning column of index
     * @return a copy matrix from indicated indexes of original matrix
     */
    private int [][] copyMatrix(int [][] matrix, int rowIndex, int colIndex, int size) {
        //Create a matrix
        int [][] copyMatrix = new int[size][size];

        //Copy elements from matrix to copyMatrix
        for(int i = rowIndex, beginRow = 0; i < size + rowIndex; i++,beginRow++) {
            for(int j = colIndex, beginCol = 0; j < size + colIndex; j++, beginCol++) {
                copyMatrix[beginRow][beginCol] = matrix[i][j];
            }
        }

        //Return the copyMatrix
        return copyMatrix;
    }

    /** 
     * functions join 4 smaller matrices into a larger one
     * @param matrix11 is two-dimensional array (matrix 11)
     * @param matrix12 is two-dimensional array (matrix 12)
     * @param matrix21 is two-dimensional array (matrix 21)
     * @param matrix22 is two-dimensional array (matrix 22)
     * @return a matrix which is copied from 4 smaller matrices
    */
    private int[][] joinMatrix(int [][] matrix11, int [][] matrix12, int [][] matrix21, int [][] matrix22) {

        int [][] matrix = new int[matrix11.length*2][matrix12.length*2];
        //A11
        for(int i = 0; i < matrix11.length; i++) {
            for(int j = 0; j < matrix11.length; j++) {
                matrix[i][j] = matrix11[i][j];
            }
        }

        //A12
        for(int i = 0; i < matrix12.length; i++) {
            for (int j = matrix12.length, colBegin = 0; j < matrix12.length * 2; j++, colBegin++ ) {
                matrix[i][j] = matrix12[i][colBegin];
            }
        }

        //A21
        for(int i = matrix21.length, rowBegin = 0; i < matrix21.length * 2; i++, rowBegin++) {
            for(int j = 0; j < matrix21.length; j++) {
                matrix[i][j] = matrix21[rowBegin][j];
            }
        }

        //A22
        for(int i = matrix22.length, rowBegin = 0; i < matrix12.length * 2; i++, rowBegin++) {
            for (int j = matrix12.length, colBegin = 0; j < matrix12.length * 2; j++, colBegin++ ) {
                matrix[i][j] = matrix22[rowBegin][colBegin];
            }
        }

        return matrix;
    }

    /**
     * function calculate multiplication of two matrices
     * @param matrix1 is the first two-dimentional array (matrix 1)
     * @param matrix2 is the second two-dimentional array (matrix 2)
     * @return a matrix which is the production of matrix 1 and matrix 2
     */
    private int[][] classicalMultiplication(int[][] matrix1, int [][]matrix2) {
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

    /**
     * function add two matrices
     * @symbol is indicator which let the function know addition or subtraction
     * @matrix1 is a two-dimensional array (matrix 1)
     * @matrix2 is a two-dimentsional array (matrix 2)
     * @return a matrix
     */
    private int[][] matricesAddition(char symbol,int[][] matrix1, int[][] matrix2) {
        int[][] matrix = new int[matrix1.length][matrix2.length];

        if(symbol == '+') {
            for(int i = 0; i < matrix1.length; i++) {
                for(int j = 0; j < matrix2.length;j++) {
                    matrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
        }
        else {
            for(int i = 0; i < matrix1.length; i++) {
                for(int j = 0; j < matrix2.length;j++) {
                    matrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }
        }
        
        return matrix;
    }

    /**
     * function checks is n is the power of 2 or not
     * @n is an integer which will be checked
     * @return whether n is a power of 2 or not
     */
    private boolean isPowerOfTwo(int n) {
        if(n == 0)
            return false;

        while(n != 1) {
            if(n % 2 != 0)
                return false;
            n /= 2;
        }  
        
        return true;
    }

}
