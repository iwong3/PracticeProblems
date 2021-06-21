package PracticeProblems.Algorithms.Searching;

import java.util.*;

public class Node {

    private char value;
    private List<Node> neighbors;

    public Node(char c) {
        this.value = c;
        this.neighbors = new ArrayList<Node>();
    }

    public char getValue() {
        return this.value;
    }

    public void setValue(char c) {
        this.value = c;
    }

    public void addNeighbor(Node n) {
        if (!neighbors.contains(n)) {
            neighbors.add(n);
        }
    }

    public List<Node> getNeighbors() {
        return this.neighbors;
    }

}
