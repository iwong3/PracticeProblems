import java.util.*;

class GraphBipartite {

    static int RED = 1;
    static int BLUE = 2;

    public static void main(String[] args) {
        int[][] graph = new int[][]{
            {1, 3},
            {0, 2},
            {1, 3},
            {0, 2}
        };

        System.out.println(isBipartite(graph));
    }

    // In a bipartite graph, every node's neighbors belongs to the opposite set
    // To represent this, we'll mark every node with 2 colors: RED and BLUE
    // 1. We'll start at the first node and set it equal to RED.
    // 2. Then, we'll find all unvisited neighbors and calculate its color (opposite of current node)
    // 3. We'll compare it to the neighbor's current color. If it's not color or the same, then update the color
    // 3a. Also, if the neighbor isn't in our list of nodes to visit, add it
    // 4. Otherwise, if the neighbor had the opposite color, then we return false.
    // 5. Repeat by taking a node out of the list of nodes to visit.
    // 6. Stop when we have visited N number of nodes.
    // 7. If there are no nodes to visit but we haven't reached N, that means
    //    the graph isn't fully connected. Select the first unvisited node and continue.
    // 8. After iterating through everything, if we never returned false, then return true.
    public static boolean isBipartite(int[][] graph) {

        int numVisited = 0;
        int[] visited = new int[graph.length];
        int[] colors = new int[graph.length];
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        
        // Initialize first node
        colors[0] = RED;
        toVisit.add(0);

        // While we haven't visited all nodes
        while (numVisited < graph.length) {

            int currentNode = 0;
            // If we have nodes to visit, pull the first
            if (toVisit.size() > 0) {
                currentNode = toVisit.remove(0);
            } else {
                // Otherwise, find the first unvisited node
                // Improvements: Keep track of last unvisited node every time you run this if statement
                // This way, we don't need to iterate through the whole list every time
                // More important if we have a lot of values
                for (int i = 0; i < visited.length; i++) {
                    if (visited[i] == 0) {
                        currentNode = i;
                    }
                }
            }

            numVisited++;
            visited[currentNode] = 1;
            // Set color to RED if unvisited
            int currentColor = colors[currentNode] == 0 ? RED : colors[currentNode];

            // Find all neighbor nodes
            for (int nextNode = 0; nextNode < graph[currentNode].length; nextNode++) {
                // If the neighbor node is not visited
                if (visited[graph[currentNode][nextNode]] == 0) {
                    // If next node's color is the same as current color, return false
                    if (currentColor == colors[graph[currentNode][nextNode]]) {
                        return false;
                    } else {
                        // Calculate the next color based on current color
                        int nextColor = currentColor == RED ? BLUE : RED;
                        colors[graph[currentNode][nextNode]] = nextColor;

                        // If next node isn't on the list to visit, add it
                        if (!toVisit.contains(graph[currentNode][nextNode])) {
                            toVisit.add(graph[currentNode][nextNode]);
                        }
                    }
                }
            }
        }

        return true;

    }

}