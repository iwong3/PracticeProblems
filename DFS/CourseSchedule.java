import java.util.*;

// DFS

// ALGO
// ====
// 1. Set up a directed graph: prereqs -> courses
//    Ex. A -> B -> C
//               -> D
//    where A is the prereq to B, and B is the prereq to C & D
// 2. Perform DFS on this graph. DFS = seek all children BEFORE neighbors.
//    The deepest children in our directed graph is the "highest level" course
//    Ex. A -> B -> C
//               -> D
//    we will perform an action at C first, then D, then B, then A
//    add the node to our ans stack. We will have courses in order of highest level to lowest level. C -> D -> B -> A
// 3. Pop our stack to get the final answer. A -> B -> D -> C
// Edge case: When we are checking neighbors, if their status is checking, that means theres a cycle: A -> B and B -> A
// now, it's impossible to take A or B, so no valid answer

public class CourseSchedule {
    
    static int NOT_CHECKED = 1;
    static int CHECKING = 2;
    static int CHECKED = 3;
    
    static boolean hasCycle;
    static Map<Integer, Integer> checkStatus;
    static Map<Integer, List<Integer>> adjList;
    static Stack<Integer> ansOrder;
    
    public static void init(int numCourses) {
        hasCycle = false;
        checkStatus = new HashMap<Integer, Integer>();
        adjList = new HashMap<Integer, List<Integer>>();
        ansOrder = new Stack<Integer>();
        
        // Set all courses to not checked
        // O(n)
        for (int i = 0; i < numCourses; i++) {
            checkStatus.put(i, NOT_CHECKED);
        }
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = new int[][]{
            {1, 0},
            {2, 0},
            {3, 1},
            {3, 2}
        };
        int[] order = findOrder(numCourses, prerequisites);
        for (int i : order) {
            System.out.print("["+i+"]");
        }

    }
    
    public static void findOrderDfs(int course) {
        // If there is a cycle, a course order is impossible, stop recursing
        if (hasCycle) {
            return;
        }
        
        // Set course to CHECKING
        checkStatus.put(course, CHECKING);
        
        // Check all of this course's neighbors
        // Perform dfs if the neighbor is unvisited
        if (adjList.containsKey(course)) {
            for (Integer futureCourse : adjList.get(course)) {
                if (checkStatus.get(futureCourse) == NOT_CHECKED) {
                    findOrderDfs(futureCourse);
                // Cycle exists
                } else if (checkStatus.get(futureCourse) == CHECKING) {
                    hasCycle = true;
                }
            }
        }
        
        // Set course to CHECKED
        checkStatus.put(course, CHECKED);
        ansOrder.push(course);
    }
    
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        init(numCourses); // O(n) for initializing checkStatus
        
        // Create the adjacency list
        // prereq course -> course
        // O(m) to initialize adjList
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prereq = prerequisites[i][1];
            
            List<Integer> isPrereqFor = new ArrayList<Integer>();
            if (adjList.containsKey(prereq)) {
                isPrereqFor = adjList.get(prereq);
            }
            isPrereqFor.add(course);
            adjList.put(prereq, isPrereqFor);
        }
        
        // With the checkStatus and adjList, we can now perform dfs
        // As long as there is a unvisited course, perform dfs
        for (int i = 0; i < numCourses; i++) {
            if (checkStatus.get(i) == NOT_CHECKED) {
                findOrderDfs(i);
            }
        }
        
        int[] ans = new int[0];
        if (!hasCycle) {
            ans = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ans[i] = ansOrder.pop();
            }
        }
        return ans;
    }
        
}