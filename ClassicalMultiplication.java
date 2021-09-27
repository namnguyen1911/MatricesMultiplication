public class ClassicalMultiplication {
    public int[][] classicalMultiplication(int[][] matrix1, int [][]matrix2) {
        
        if(matrix1[0].length != matrix2.length) {
            return null;
        }
        else {
            int[][] matrix = new int[matrix1.length][matrix2[0].length];

            for(int row = 0; row < matrix1[0].length; row++) {
                for(int col = 0; col < matrix2.length; col++) {

                }
            }



            return matrix;
        }

    }
}
