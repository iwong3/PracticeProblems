package PracticeProblems.Problems.BST;

import java.util.*;

/**
 * Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * ===========================
 * Given the root of a binary tree, return only the nodes from the rightmost side of the tree
 *
 * Ex:
 *       4 <-
 *      / \
 *     2   5 <-
 *    / \
 *   1   3 <-
 *      / \
 *     7   6 <-
 * Ans: [4, 5, 3, 6]
 *
 * The key to this problem is realizing we just need the right-most node at *each depth*.
 * We keep a tracker to see if a node has been selected as the rightmost for each depth.
 * Then, we recursively iterate, keeping track of the current depth.
 * 1. Check ourselves - if we haven't found the rightmost at this depth, record ourselves
 * 2. Recurse right
 * 3. Recurse left
 * Because of our recurse ordering, we're guaranteed to explore the rightmost nodes first.
 *
 * Runtime: O(n)
 * Space  : O(n)
 *
 * Optimizations:
 * - can we stop once we've found a node for each depth?
 */
public class RightSideView {

    HashMap<Integer, Integer> lookup;

    public List<Integer> rightSideView(TreeNode root) {

        lookup = new HashMap<>();
        rightSideViewHelper(root, 0);

        ArrayList<Integer> ans = new ArrayList<>();
        int depth = 0;
        while (lookup.containsKey(depth)) {
            ans.add(lookup.get(depth));
            depth++;
        }

        return ans;

    }

    public void rightSideViewHelper(TreeNode root, int depth) {

        // null check
        if (null == root) {
            return;
        }

        // check self for rightmost - guaranteed to traverse rightmost nodes right
        if (!lookup.containsKey(depth)) {
            lookup.put(depth, root.val);
        }

        rightSideViewHelper(root.right, depth+1);
        rightSideViewHelper(root.left, depth+1);

    }

}
