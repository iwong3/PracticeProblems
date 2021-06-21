package PracticeProblems.Problems.DFS;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfIslandsSpec {

    NumberOfIslands islands = new NumberOfIslands();

    /* TEST CASES FOR MAIN IMPLEMENTATION */
    @Test
    public void oneIsland() {
        int[][] map = new int[][]{
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 0}
        };
        int expected_ans = 1;
        Assert.assertEquals(expected_ans, islands.numIslands(map));
    }

    @Test
    public void manyIslands() {
        int[][] map = new int[][]{
            {1, 1, 1, 0, 0},
            {1, 1, 0, 1, 0},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
        };
        int expected_ans = 5;
        Assert.assertEquals(expected_ans, islands.numIslands(map));
    }

    @Test
    public void noIslands() {
        int[][] map = new int[][]{
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        int expected_ans = 0;
        Assert.assertEquals(expected_ans, islands.numIslands(map));
    }

    @Test
    public void allLand() {
        int[][] map = new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int expected_ans = 1;
        Assert.assertEquals(expected_ans, islands.numIslands(map));
    }

    @Test
    public void diagonalIslands() {
        int[][] map = new int[][]{
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        int expected_ans = 5;
        Assert.assertEquals(expected_ans, islands.numIslands(map));
    }

    @Test
    public void emptyMap() {
        int[][] map = new int[][]{{}};
        int expected_ans = 0;
        Assert.assertEquals(expected_ans, islands.numIslands(map));
    }


    /* TEST CASES FOR PRACTICE IMPLEMENTATION */

}
