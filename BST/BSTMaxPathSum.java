class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
 
public class BSTMaxPathSum {
    
    // finds the maximum sum path from any node to another 
    //    -10
    //  9     20
    //     15    7

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxPathSum(root));
    }

    // algo
    // for a path to exist in a tree, it will have to go up, hit a ROOT node, then go down (where up and down could be 0)
    // recursively find the left/right max values.
    // then, find the max value for the current node, assuming it would be the root node (aka max value including left AND right sides)
    // update if it is the new max value
    // otherwise, return as if it is NOT the max value, so return a PATH with only left or right
    // this way, we can continue checking higher up nodes for max value
    
    static int maxValue;
    
    public static int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    private static int maxPathDown(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // get max values for left and right nodes
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        
        // compare left + right + current vs. existing max value, update if needed
        // basically, we pretend the current node is the new root for max value.
        maxValue = Math.max(maxValue, left + right + node.val);
        
        // return the greater path (only left or right)
        // if we have to go return to a higher iteration, then the current node isn't the root for max value, so we return the PATH to the current node (left OR right)
        // now, for the higher iteration nodes, the left/right will only be the PATH to the left/right nodes.
        return Math.max(left, right) + node.val;
    }
}