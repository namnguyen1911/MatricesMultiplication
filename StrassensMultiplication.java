public class StrassensMultiplication {

    public int[][] strassensMultiplication(int[][] matrix1, int[][] matrix2) {
        if(!isPowerOfTwo(matrix1.length) || !isPowerOfTwo(matrix2.length)) {
            System.out.println("Cannot use Strassen's method!");
            return null;
        }
        else if (matrix1.length == 2 && matrix2.length == 2) {
            return classicalMultiplication(matrix1, matrix2);
        }
        else {
            //Create a matrix to hold the product of two matrices
            //int [][] matrix = new int[matrix1.length][matrix2.length];

            //Size of smaller matrices
            int n = matrix1.length / 2;

            //A11
            int [][] a11 = copyMatrix(matrix1, 0, 0, n);
            //A12
            int [][] a12 = copyMatrix(matrix1, 0, n + 1, n);
            //A13
            int [][] a21 = copyMatrix(matrix1, n + 1, 0, n);
            //A14
            int [][] a22 = copyMatrix(matrix1, n + 1, n + 1, n);
            //A21
            int [][] b11 = copyMatrix(matrix2, 0, 0, n);
            //A22
            int [][] b12 = copyMatrix(matrix2, 0, n + 1, n);
            //A23
            int [][] b21 = copyMatrix(matrix2, n + 1, 0, n);
            //A24
            int [][] b22 = copyMatrix(matrix2, n + 1, n + 1, n);

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
            int [][] c11 = matricesAddition('-',matricesAddition('+',m1,m4),matricesAddition('+', m5, m7));

            //M3 + M5
            int [][] c12 = matricesAddition('+',m3,m5);

            //M2 + m4
            int [][] c21 = matricesAddition('+',m2,m4);

            //M1 + M3 - M2 + M6
            int [][] c22 = matricesAddition('-', matricesAddition('+',m1,m3), matricesAddition('+', m2, m6));

            return joinMatrix(c11,c12,c21,c22);
        }
        //return matrix;
    }

    private int [][] copyMatrix(int [][] matrix, int rowIndex, int colIndex, int size) {
        int [][] copyMatrix = new int[size][size];
        for(int i = rowIndex, beginRow = 0; i < size + rowIndex; i++,beginRow++) {
            for(int j = colIndex, beginCol = 0; j < size + colIndex; j++, beginCol++) {
                copyMatrix[beginRow][beginCol] = matrix[rowIndex][colIndex];
            }
        }
        return copyMatrix;
    }

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
            for (int j = matrix12.length + 1, colBegin = 0; j < matrix12.length * 2 + 1; j++, colBegin++ ) {
                matrix[i][j] = matrix[i][colBegin];
            }
        }

        //A21
        for(int i = matrix21.length + 1, rowBegin = 0; i < matrix21.length * 2 + 1; i++, rowBegin++) {
            for(int j = 0; j < matrix21.length; j++) {
                matrix[i][j] = matrix21[rowBegin][j];
            }
        }

        //A22
        for(int i = matrix22.length + 1, rowBegin = 0; i < matrix12.length * 2 + 1; i++, rowBegin++) {
            for (int j = matrix12.length + 1, colBegin = 0; j < matrix12.length * 2 + 1; j++, colBegin++ ) {
                matrix[i][j] = matrix[rowBegin][colBegin];
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
