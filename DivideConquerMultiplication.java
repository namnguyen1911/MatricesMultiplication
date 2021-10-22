public class DivideConquerMultiplication {
    
    public int[][] divideConquerMultiplication(int[][] matrix1, int[][] matrix2) {
       
        //return simple multiplication if arrays are of size 1
        if (matrix1.length == 1 && matrix2.length == 1) {
            int [][] temp = new int[1][1];
            temp[0][0] = matrix1[0][0] * matrix2[0][0];
            return temp;
        }
        else {

            int newLength = matrix1.length/2;

            int [][] a11 = partitionMatrix(matrix1, 0, 0);
            int [][] a12 = partitionMatrix(matrix1, 0, newLength);
            int [][] a21 = partitionMatrix(matrix1, newLength, 0); 
            int [][] a22 = partitionMatrix(matrix1, newLength, newLength); 

            int [][] b11 = partitionMatrix(matrix2, 0, 0);
            int [][] b12 = partitionMatrix(matrix2, 0, newLength);
            int [][] b21 = partitionMatrix(matrix2, newLength, 0);
            int [][] b22 = partitionMatrix(matrix2, newLength, newLength);;
                    
            
            int [][] c11 = matrixAddition(divideConquerMultiplication(a11, b11), 
                           divideConquerMultiplication(a12, b21));
            
            int [][] c12 = matrixAddition(divideConquerMultiplication(a11, b12), 
                           divideConquerMultiplication(a12, b22));
            
            int [][] c21 = matrixAddition(divideConquerMultiplication(a21, b11), 
                           divideConquerMultiplication(a22, b21));
            
            int [][] c22 = matrixAddition(divideConquerMultiplication(a21, b12), 
                           divideConquerMultiplication(a22, b22));
       
            return joinMatrix(c11,c12,c21,c22);
        }
        
    }

  
    private int [][] partitionMatrix(int [][] matrix, int rowIndex, int colIndex) {
        int length = matrix.length/2;
        int [][] newMatrix = new int[length][length];

        for(int rowCounter = 0; rowCounter < length; rowCounter++) {
            for(int colCounter = 0; colCounter < length; colCounter++) {
                newMatrix[rowCounter][colCounter] = matrix[rowIndex + rowCounter][colIndex + colCounter];
            }
        }
        return newMatrix;
    }

    private int[][] joinMatrix(int [][] matrix11, int [][] matrix12, int [][] matrix21, int [][] matrix22) {

        int [][] matrix = new int[matrix11.length*2][matrix12.length*2];
        for(int i = 0; i < matrix11.length; i++) {
            for(int j = 0; j < matrix11.length; j++) {
                matrix[i][j] = matrix11[i][j];
            }
        }

        for(int i = 0; i < matrix12.length; i++) {
            for (int j = matrix12.length, colBegin = 0; j < matrix12.length * 2; j++, colBegin++ ) {
                matrix[i][j] = matrix12[i][colBegin];
            }
        }

        for(int i = matrix21.length, rowBegin = 0; i < matrix21.length * 2; i++, rowBegin++) {
            for(int j = 0; j < matrix21.length; j++) {
                matrix[i][j] = matrix21[rowBegin][j];
            }
        }

        for(int i = matrix22.length, rowBegin = 0; i < matrix12.length * 2; i++, rowBegin++) {
            for (int j = matrix12.length, colBegin = 0; j < matrix12.length * 2; j++, colBegin++ ) {
                matrix[i][j] = matrix22[rowBegin][colBegin];
            }
        }

        return matrix;
    }

    private int[][] matrixAddition(int[][] matrix1, int[][] matrix2) {
        int[][] temp = new int[matrix1.length][matrix2.length];

        for(int i = 0; i < matrix1.length; i++) {
            for(int j = 0; j < matrix2.length;j++) {
                temp[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return temp;
    }

    //Display the matrix within the program, for error trapping 
    void matricesDisplay(int[][] matrix) {
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

}
