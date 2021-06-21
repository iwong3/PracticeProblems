package PracticeProblems.Problems.Arrays;

/**
 * Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 * ============================
 * Given an array of integers (nums), return an array with the same length (ans) where
 * every index in ans is the product of all the other indexes in nums.
 *
 * Complete without division and in O(n).
 *
 * Example:
 * nums: [ 1,  2, 3, 4]
 *  ans: [24, 12, 8, 6]
 *
 * Solution:
 * For any given index, the product of all other indexes is the same as the product of all left indexes * right indexes
 *
 * [1, 2, 3, 4]
 *     ^
 * 1 * 3 * 4 = left * right = (1) * (3 * 4)
 *
 * Therefore, we can iterate through nums twice (keeping it O(n)) and
 * calculate the cumulative product of the left/right as we go
 *
 *  left: [ 1,  2,  6, 24]
 * right: [24, 24, 12,  4]
 *
 * Then, ans[i] = left[i-1] * right[i+1]. if the index is out of bounds, use 1.
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        // create left/right arrays
        int[] left = new int[nums.length];
        left[0] = nums[0];
        int[] right = new int[nums.length];
        right[nums.length - 1] = nums[nums.length - 1];

        // populate left/right arrays
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i];
        }

        // calculate ans
        int[] ans = new int[nums.length];
        ans[0] = right[1];
        ans[nums.length - 1] = left[nums.length - 2];
        for (int i = 1; i < ans.length - 1; i++) {
            ans[i] = left[i-1] * right[i+1];
        }

        return ans;

    }

}
