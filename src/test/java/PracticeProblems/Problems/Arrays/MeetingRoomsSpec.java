package PracticeProblems.Problems.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class MeetingRoomsSpec {

    MeetingRooms mr = new MeetingRooms();

    /* TEST CASES FOR MAIN IMPLEMENTATION */

    @Test
    public void canAttend() {
        int[][] intervals = new int[][]{
            {0, 5},
            {5, 10},
            {10, 15}
        };
        boolean ans = mr.canAttendMeetings(intervals);
        boolean expectedAns = true;
        Assert.assertEquals(expectedAns, ans);
    }

    @Test
    public void partialOverlap() {
        int[][] intervals = new int[][]{
            {0, 8},
            {5, 10},
            {10, 15}
        };
        boolean ans = mr.canAttendMeetings(intervals);
        boolean expectedAns = false;
        Assert.assertEquals(expectedAns, ans);
    }

    @Test
    public void multiplePartialOverlap() {
        int[][] intervals = new int[][]{
            {0, 5},
            {2, 8},
            {6, 10},
            {9, 15}
        };
        boolean ans = mr.canAttendMeetings(intervals);
        boolean expectedAns = false;
        Assert.assertEquals(expectedAns, ans);
    }

    @Test
    public void fullOverlap() {
        int[][] intervals = new int[][]{
            {0, 10},
            {3, 8},
            {10, 15}
        };
        boolean ans = mr.canAttendMeetings(intervals);
        boolean expectedAns = false;
        Assert.assertEquals(expectedAns, ans);
    }

    @Test
    public void multipleFullOverlap() {
        int[][] intervals = new int[][]{
            {0, 10},
            {2, 5},
            {6, 8},
            {10, 15}
        };
        boolean ans = mr.canAttendMeetings(intervals);
        boolean expectedAns = false;
        Assert.assertEquals(expectedAns, ans);
    }

    @Test
    public void oneMeeting() {
        int[][] intervals = new int[][]{
            {0, 10}
        };
        boolean ans = mr.canAttendMeetings(intervals);
        boolean expectedAns = true;
        Assert.assertEquals(expectedAns, ans);
    }

    @Test
    public void noMeetings() {
        int[][] intervals = new int[][]{};
        boolean ans = mr.canAttendMeetings(intervals);
        boolean expectedAns = true;
        Assert.assertEquals(expectedAns, ans);
    }

}
