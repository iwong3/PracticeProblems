package PracticeProblems.Problems.DFS;

/**
 * NumberOfIslands
 * https://leetcode.com/problems/number-of-islands/
 * ===============
 * Given a matrix filled with 0s and 1s where 0 = water and 1 = land,
 * determine the number of islands where an island is horizontally or vertically connected land surrounded by water.
 * - assume water surrounds the matrix
 * - diagonally connected land does NOT count as one island
 */
public class NumberOfIslands {

    private int[][] lookup;

    public int numIslands(int[][] map) {

        // null check
        if (null == map || map.length <= 0 || map[0].length <= 0) {
            return 0;
        }

        // init vars
        lookup = new int[map.length][map[0].length];
        int numIslands = 0;

        // check map for islands
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                // if current cell is unchecked
                if (lookup[row][col] == 0) {
                    // if land, explore and increment numIslands
                    if (map[row][col] == 1) {
                        exploreLandDFS(map, row, col);
                        numIslands++;
                    // if water, mark as checked
                    } else {
                        lookup[row][col] = 1;
                    }
                }
            }
        }

        return numIslands;

    }

    public void exploreLandDFS(int[][] map, int row, int col) {

        // out of bounds
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return;
        }

        // cell has been checked
        if (lookup[row][col] == 1) {
            return;
        }

        // mark cell as checked
        lookup[row][col] = 1;

        // if on land, check all neighbors
        if (map[row][col] == 1) {
            exploreLandDFS(map, row, col + 1);
            exploreLandDFS(map, row + 1, col);
            exploreLandDFS(map, row, col - 1);
            exploreLandDFS(map, row - 1, col);
        }

    }

    /* PRACTICE IMPLEMENTATION */


}
