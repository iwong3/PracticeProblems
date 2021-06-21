package PracticeProblems.Algorithms.Searching;

import java.util.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DFSSpec {

    DFS dfs = new DFS();
    static HashMap<Character, Node> nodeList = new HashMap<>();

    @BeforeClass
    public static void setup() {

        // set up nodes
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
        a.addNeighbor(c);
        b.addNeighbor(d);
        b.addNeighbor(e);
        b.addNeighbor(f);
        c.addNeighbor(g);
        h.addNeighbor(i);

        // add nodes to graph
        nodeList.put('a', a);
        nodeList.put('b', b);
        nodeList.put('c', c);
        nodeList.put('d', d);
        nodeList.put('e', e);
        nodeList.put('f', f);
        nodeList.put('g', g);
        nodeList.put('h', h);
        nodeList.put('i', i);

    }

    /* TEST CASES FOR MAIN IMPLEMENTATION */

    @Test
    public void pathExists() {
        Node start = nodeList.get('a');
        Node target = nodeList.get('f');
        boolean expectedAnswer = true;
        Assert.assertEquals(expectedAnswer, this.dfs.hasPath(start, target));
    }

    @Test
    public void pathDoesNotExist() {
        Node start = nodeList.get('a');
        Node target = nodeList.get('i');
        boolean expectedAnswer = false;
        Assert.assertEquals(expectedAnswer, this.dfs.hasPath(start, target));
    }

}
