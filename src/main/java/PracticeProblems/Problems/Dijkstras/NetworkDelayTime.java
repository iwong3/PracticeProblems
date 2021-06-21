import java.util.*;

class Node implements Comparable<Node> {
    int node;
    int distance;

    public Node(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node n) {
        return Integer.compare(this.distance, n.distance);
    }
}

// Given a directed weighted graph, calculate the time it would take for a node K to
// reach all other nodes
// 
// ALGO
// Use Dijkstras to calculate times to all other nodes
// Return max value
// Dijkstras:
// unvisited, visited, distances, pq
// start node set to 0, all others set to infinity
// pop from pq, check all unvisited neighbors, update distances, add to pq
// repeat until pq is empty or all are visited

class NetworkDelayTime {

    public static void main(String[] args) {

        // int[][] times = new int[][]{
        //     {2, 1, 1},
        //     {2, 3, 1},
        //     {3, 4, 1}
        // };
        // int N = 4;
        // int K = 2;
        // int ans = 2;

        int[][] times = new int[][]{
            {1, 2, 1},
            {2, 3, 2},
            {1, 3, 1}
        };
        int N = 3;
        int K = 2;
        int ans = -1;

        System.out.println(networkDelayTime(times, N, K));
    }

    public static int networkDelayTime(int[][] times, int N, int K) {

        int visited = 0;
        int[] unvisited = new int[N];
        int[] distances = new int[N];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[K-1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(K, 0));

        // Stop when there's nothing left to visit or if we've visited all nodes
        while (pq.size() > 0 && visited != N) {
            // Get shortest distance unvisited node
            Node currentNode = pq.poll();
            if (unvisited[currentNode.node - 1] == 1) {
                continue;
            }

            // Mark node as visited
            unvisited[currentNode.node - 1] = 1;
            visited++;

            // Iterate through adjacency list
            for (int i = 0; i < times.length; i++) {
                // If current node is found on adjList
                if (times[i][0] == currentNode.node) {
                    // If destination node is unvisited
                    if (unvisited[times[i][1] - 1] == 0) {
                        // Calculate new distance and update it if it's shorter
                        // Current node's distance + distance to destination node
                        int newDistance = distances[currentNode.node - 1] + times[i][2];
                        if (newDistance < distances[times[i][1] - 1]) {
                            distances[times[i][1] - 1] = newDistance;
                        }
                        // Add unvisited destination node to pq
                        pq.add(new Node(times[i][1], distances[times[i][1] - 1]));
                    }
                }
            }
        }

        // Find largest distance
        int max = -1;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1;
            }
            if (i == K) {
                continue;
            }
            if (distances[i] > max) {
                max = distances[i];
            }
        }

        return max;

    }

}