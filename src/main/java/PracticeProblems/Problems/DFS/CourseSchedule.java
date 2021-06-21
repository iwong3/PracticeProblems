package PracticeProblems.Problems.DFS;

import java.util.*;

/**
 * CourseSchedule
 * ==============
 * Given a number of courses 'n' where the courses are labeled from '0' to 'n-1'
 *       and a list of course prerequisites [[1, 0], ...] where '0' is a prereq for '1'
 * Return true or false if it's possible to take all courses and print a valid order to take the courses
 * ==============
 * 1. Set up a directed graph via adjacency list: prereqs -> courses
 *    Ex. A -> B -> C
 *               -> D
 * 2. Check all courses for a cycle using DFS
 *    a. If there is a cycle, we can stop everything as it's impossible (0->1, 1->0, can't ever take 0 or 1)
 *       A cycle exists if we run into a course with the status CHECKING
 *    b. Otherwise, add course to stack once we've checked all neighbors
 * 3. Pop stack to get course order.
 *
 * O(n+e) where n = num courses, e = num edges
 */
public class CourseSchedule {

    public static final int NOT_CHECKED = 0;
    public static final int CHECKING = 1;
    public static final int CHECKED = 2;

    boolean hasCycle;
    private Map<Integer, Integer> courseStatus;
    private Map<Integer, List<Integer>> adjList;
    private Stack<Integer> courseOrder;

    public void init(int numCourses, int[][] prereqs) {

        // init helper vars
        hasCycle = false;
        courseStatus = new HashMap<Integer, Integer>();
        adjList = new HashMap<Integer, List<Integer>>();
        courseOrder = new Stack<Integer>();

        // set all courses not NOT_CHECKED - O(n)
        for (int i = 0; i < numCourses; i++) {
            courseStatus.put(i, NOT_CHECKED);
        }

        // create adj list: prereq -> course - O(e)
        for (int i = 0; i < prereqs.length; i++) {
            int course = prereqs[i][0];
            int prereq = prereqs[i][1];

            List<Integer> isPrereqFor = new ArrayList<Integer>();
            if (adjList.containsKey(prereq)) {
                isPrereqFor = adjList.get(prereq);
            }
            isPrereqFor.add(course);
            adjList.put(prereq, isPrereqFor);
        }

    }

    public boolean canFinish(int numCourses, int[][] prereqs) {

        init(numCourses, prereqs);

        // check all courses for a cycle
        for (int i = 0; i < numCourses; i++) {
            if (courseStatus.get(i) == NOT_CHECKED) {
                if (hasCycle(i)) {
                    return false;
                }
            }
        }

        // print course order
        String courseOrderString = "";
        for (int i = 0; i < numCourses; i++) {
            courseOrderString += courseOrder.pop() + "->";
        }
        System.out.println(courseOrderString.substring(0, courseOrderString.length() - 2));

        return true;

    }

    public boolean hasCycle(int course) {

        // check course status
        int status = courseStatus.get(course);
        // course has already been checked
        if (status == CHECKED) {
            return false;
        }
        // course is currently being checked - cycle
        if (status == CHECKING) {
            return true;
        }

        // mark course as being checked
        courseStatus.put(course, CHECKING);

        // check all courses this course is a prereq for
        if (adjList.containsKey(course)) {
            for (int futureCourse : adjList.get(course)) {
                if (hasCycle(futureCourse)) {
                    return true;
                }
            }
        }

        // checked all future courses - no cycle
        // update status and add to course order
        courseStatus.put(course, CHECKED);
        courseOrder.push(course);

        return false;
    }

}
