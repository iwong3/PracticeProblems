package PracticeProblems.Problems.DFS;

import org.junit.Assert;
import org.junit.Test;

public class CourseScheduleSpec {

    CourseSchedule cs = new CourseSchedule();

    /* TEST CASES FOR MAIN IMPLEMENTATION */

    @Test
    public void noCycle() {
        int numCourses = 4;
        int[][] prereqs = new int[][]{
            {1, 0},
            {2, 1},
            {3, 0}
        };
        boolean expectedAnswer = true;
        Assert.assertEquals(expectedAnswer, this.cs.canFinish(numCourses, prereqs));
    }

    @Test
    public void hasCycle() {
        int numCourses = 4;
        int[][] prereqs = new int[][]{
            {1, 0},
            {0, 1}
        };
        boolean expectedAnswer = false;
        Assert.assertEquals(expectedAnswer, this.cs.canFinish(numCourses, prereqs));
    }

    @Test
    public void coursePointsToChecked() {
        int numCourses = 4;
        int[][] prereqs = new int[][]{
            {1, 0},
            {2, 1},
            {3, 0},
            {2, 3}
        };
        boolean expectedAnswer = true;
        Assert.assertEquals(expectedAnswer, this.cs.canFinish(numCourses, prereqs));
    }

}
