package PracticeProblems.Problems.BST;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class RightSideViewSpec {

    RightSideView rsv = new RightSideView();

    /* TEST CASES FOR MAIN IMPLEMENTATION */
    @Test
    public void validCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(6);
        List<Integer> expectedAns = Arrays.asList(4, 5, 3, 6);
        Assert.assertEquals(expectedAns, rsv.rightSideView(root));
    }

}
