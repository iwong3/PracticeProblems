package PracticeProblems.Problems.DFS;

import org.junit.Assert;
import org.junit.Test;

public class DetectCyclesMatrixSpec {

    DetectCyclesMatrix dc = new DetectCyclesMatrix();

    /* TEST CASES FOR MAIN IMPLEMENTATION */
    @Test
    public void oneCycle() {
        char[][] grid = new char[][]{
            {'a', 'a', 'a'},
            {'a', 'b', 'a'},
            {'a', 'a', 'a'}
        };
        boolean expected_ans = true;
        Assert.assertEquals(expected_ans, dc.containsCycle(grid));
    }

    @Test
    public void manyCycles() {
        char[][] grid = new char[][]{
            {'a', 'a', 'b', 'b'},
            {'a', 'a', 'b', 'b'},
            {'a', 'a', 'a', 'a'}
        };
        boolean expected_ans = true;
        Assert.assertEquals(expected_ans, dc.containsCycle(grid));
    }

    @Test
    public void noCycle() {
        char[][] grid = new char[][]{
            {'a', 'b', 'a'},
            {'a', 'b', 'a'},
            {'a', 'b', 'a'}
        };
        boolean expected_ans = false;
        Assert.assertEquals(expected_ans, dc.containsCycle(grid));
    }

    @Test
    public void nullGrid() {
        char[][] grid = new char[][]{{}};
        boolean expected_ans = false;
        Assert.assertEquals(expected_ans, dc.containsCycle(grid));
    }

}
