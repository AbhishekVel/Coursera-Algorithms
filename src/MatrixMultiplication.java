
public class MatrixMultiplication {
	
	public static void main(String[] args) {
		//test case
		int[][] m1 = {{2,1},{3,4},{5,6}};
		int[][] m2 = {{1,3,6}, {2,4,5}};
		
		
	}
	
	public static int[][] matrixMultiplication(int[][] m1, int[][] m2) {
		
		if (m1 == null || m2 == null) return null;
		if (m1[0].length != m2.length) return null;
		
		int[][] matrix = new int[m1.length][m2[0].length];
		int col = 0, row = 0;
		
		for (int col1 = 0; col1 < m1[0].length; col1++) {
			for (int row1 = 0; row1 < m1.length; row1++) {
				
				int row2 = 0;
				for (int col2 = 0; col2 < m2[0].length; col2++) {
					matrix[row][col] += m1[row1][col1] * m2[row2][col2];
				}
				row2++;
				
			}
		}
		
		return matrix;
		
		
	}

}
