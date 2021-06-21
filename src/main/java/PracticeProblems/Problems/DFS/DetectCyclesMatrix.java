package PracticeProblems.Problems.DFS;

/**
 * Detect Cycles in 2D Grid
 * https://leetcode.com/problems/detect-cycles-in-2d-grid/
 * ========================
 * Given a grid with letters, determine if a cycle exists
 * You can only traverse on paths with the same letter
 * and you cannot go back to the cell you previously came from.
 *
 * Examples:
 * a a a
 * a b a - true
 * a a a
 *
 * a b a
 * a b b - false
 * a a a
 */
public class DetectCyclesMatrix {

    int[][] lookup;
    final static int UNCHECKED = 0;
    final static int CHECKING = 1;
    final static int CHECKED = 2;

    public boolean containsCycle(char[][] grid) {

        // null checks
        if (null == grid || grid.length == 0 || grid[0].length == 0) {
            return false;
        }

        // init vars
        lookup = new int[grid.length][grid[0].length];

        // iterate through grid and find cycles
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (lookup[row][col] == UNCHECKED) {
                    if (checkCycle(grid, grid[row][col], row, col, row, col)) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public boolean checkCycle(char[][] grid, char currChar, int row, int col, int prevRow, int prevCol) {

        // out of bounds
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        }

        // not part of current char path
        if (grid[row][col] != currChar) {
            return false;
        }

        // currently checking - part of path - cycle
        if (lookup[row][col] == CHECKING) {
            return true;
        }

        // cell already checked from previous path
        if (lookup[row][col] == CHECKED) {
            return false;
        }

        // mark current cell as checking
        lookup[row][col] = CHECKING;

        // check all neighbors except where you came from
        if (!(row + 1 == prevRow && col == prevCol)) {
            if (checkCycle(grid, currChar, row + 1, col, row, col)) {
                return true;
            };
        }
        if (!(row - 1 == prevRow && col == prevCol)) {
            if (checkCycle(grid, currChar, row - 1, col, row, col)) {
                return true;
            };
        }
        if (!(row == prevRow && col + 1 == prevCol)) {
            if (checkCycle(grid, currChar, row, col + 1, row, col)) {
                return true;
            };
        }
        if (!(row == prevRow && col - 1 == prevCol)) {
            if (checkCycle(grid, currChar, row, col - 1, row, col)) {
                return true;
            };
        }

        // mark curr cell as checked
        lookup[row][col] = CHECKED;

        return false;

    }

}
