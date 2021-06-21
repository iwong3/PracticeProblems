package PracticeProblems.Problems.Arrays;

import java.util.*;

/**
 * MeetingRooms
 * ============
 * Given an array of meeting time intervals, return if we can attend all meetings.
 * Ex.
 * - [[0,30],[5,10],[15,20]] => false
 * - [[0,10],[10,20],[20,30]] => true
 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {

            if (intervals.length == 0) {
                return true;
            }

            Arrays.sort(intervals, new intervalComparator());
            int end = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < end) {
                    return false;
                }
                end = intervals[i][1];
            }

            return true;

    }

    // given an array of existing meetings, a number of rooms, and an array of new meetings to add
    // return an array of booleans that show whether the new meeting can be added or not
    // ex.
    // - meetings=[[1,5],[1,5]], rooms=2, newMeetings=[[1,5]] => false bc no more rooms for meetings at same time interval
    // - meetings=[[1,5]], rooms=2, new Meetings[[1,5]] => true
    public boolean[] meetingRooms3(int[][] meetings, int rooms, int[][] newMeetings) {

        boolean[] ans = new boolean[newMeetings.length];



        return ans;

    }

    class intervalComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if (a[0] < b[0]) {
                return -1;
            }
            if (a[0] > b[0]) {
                return 1;
            }
            return 0;

        }
    }

}
