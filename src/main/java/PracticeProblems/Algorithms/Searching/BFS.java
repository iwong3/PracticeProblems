package PracticeProblems.Algorithms.Searching;

import java.util.*;

/**
 * - can be susceptible to cycles depending on the ordering of neighbors
 *   - avoid by using a "checked" list
 */
public class BFS {

    public static void main(String[] args) {

        // set up graph
        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');
        Node f = new Node('f');
        Node g = new Node('g');
        Node h = new Node('h');
        Node i = new Node('i');
        a.addNeighbor(b);
        a.addNeighbor(f);
        b.addNeighbor(a);
        b.addNeighbor(c);
        c.addNeighbor(d);
        d.addNeighbor(e);
        e.addNeighbor(c);
        e.addNeighbor(i);
        f.addNeighbor(g);
        g.addNeighbor(h);
        h.addNeighbor(f);

        long startTime = System.nanoTime();
        System.out.println(bfs(a, i));
        long endTime = System.nanoTime();
        System.out.println("Runtime: " + ((endTime - startTime) / 1000000.));

    }

    public boolean hasPath(Node start, Node target) {

        // init helper vars
        HashMap<Node, Boolean> checked = new HashMap<Node, Boolean>();
        HashMap<Node, Node> addedBy = new HashMap<Node, Node>();
        Queue<Node> toCheck = new LinkedList<>();

        // confirm bfs path & order
        String path = "";
        String checkOrder = "";

        // loop through nodes
        Node curr = null;
        toCheck.add(start);
        while (toCheck.size() > 0) {

            curr = toCheck.poll();
            checkOrder += curr.getValue() + "->";

            // reached target node
            if (curr.getValue() == target.getValue()) {

                // print check order
                System.out.println("order: " + checkOrder.substring(0, checkOrder.length() - 2));

                // print path
                path = target.getValue() + "";
                Node pathCurr = target;
                while (pathCurr.getValue() != start.getValue()) {
                    pathCurr = addedBy.get(pathCurr);
                    path = pathCurr.getValue() + "->" + path;
                }
                System.out.println("path:  " + path);

                return true;

            }

            // add all unchecked neighbors to queue
            List<Node> neighbors = curr.getNeighbors();
            for (Node n : neighbors) {
                if (!checked.containsKey(n)) {
                    toCheck.add(n);
                    addedBy.put(n, curr);
                }
            }

        }

        System.out.println("order: " + checkOrder.substring(0, checkOrder.length() - 2));
        return false;

    }

    public static String bfs(Node start, Node target) {

        // init tracking vars
        Queue<Node> todo = new LinkedList<>();
        ArrayList<Node> checked = new ArrayList<>();
        HashMap<Node, String> paths = new HashMap<>();

        // populate tracking vars
        todo.add(start);
        paths.put(start, start.getValue() + "");

        while (!todo.isEmpty()) {

            Node curr = todo.poll();

            // found target
            if (curr.getValue() == target.getValue()) {
                break;
            }

            // add current node to checked
            checked.add(curr);

            // add all neighbors that haven't been checked
            String currPath = paths.get(curr);
            for (Node neighbor : curr.getNeighbors()) {
                if (!checked.contains(neighbor)) {
                    todo.add(neighbor);
                    paths.put(neighbor, currPath + "-" + neighbor.getValue());
                }
            }

        }

        // return path
        if (paths.containsKey(target)) {
            return paths.get(target);
        }
        return null;

    }

}
