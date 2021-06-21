import java.util.*;

// DYNAMIC PROGRAMMING

class UniquePaths {
    
    public static void main(String[] args) {
        int m = 7;
        int n = 3;

        System.out.println("For a " + m + "x" + n + " board, there are this many unique paths from the top left to the bottom right");
        System.out.println(uniquePaths(m, n));
    }
    
    public static int uniquePaths(int m, int n) {

        //algo
        //the # of ways to get to a cell is equal to the # of paths to cell to the left + # of paths to cell above
        //all border cells have 1 way to get there, go straight down or go straight to the right
        //populate the board
        //return value at m, n
        
        Integer[][] grid = new Integer[m][n];
        
        //populate sides with 1
        for (int i = 0; i < m; i++) {
            grid[i][0] = 1;
        }
        
        for (int j = 0; j < n; j++) {
            grid[0][j] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }
        
        return grid[m-1][n-1];
    
    }

}