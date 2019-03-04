import java.util.*;

class SearchMatrix {

    // Matrix will be sorted by row and column
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        int t1 = 5; // true
        int t2 = 20; //false

        System.out.println(searchMatrix(matrix, t1));
        System.out.println(searchMatrix(matrix, t2));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[row].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            }
        }
        return false;
    }

}

// HINT
// Start search from top right corner