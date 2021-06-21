package PracticeProblems.Algorithms.Searching;

import java.util.*;

public class DFS {

    private ArrayList<Node> checked;
    private String path;
    private String checkOrder;

    public boolean hasPath(Node start, Node target) {

        // reset helper vars
        this.checked = new ArrayList<>();
        this.path = "";
        this.checkOrder = "";

        boolean hasPath = hasPathHelper(start, target);
        System.out.println("order: " + this.checkOrder.substring(0, this.checkOrder.length() - 2));
        if (hasPath) {
            System.out.println("path:  " + this.path);
        }
        return hasPath;

    }

    private boolean hasPathHelper(Node a, Node b) {

        this.checkOrder += a.getValue() + "->";

        // reached target node
        if (a.getValue() == b.getValue()) {
            this.path = b.getValue() + "";
            return true;
        }

        // add current node to checked list
        this.checked.add(a);

        // get all neighbors of current node
        List<Node> neighbors = a.getNeighbors();

        // recursively check all neighbors for path to target
        boolean pathExists = false;
        for (Node n : neighbors) {

            // skip nodes that we've already checked
            if (this.checked.contains(n)) {
                continue;
            }

            if (hasPathHelper(n, b)) {
                pathExists = true;
                this.path = a.getValue() + "->" + this.path;
                break;
            }
        }

        return pathExists;

    }

}
