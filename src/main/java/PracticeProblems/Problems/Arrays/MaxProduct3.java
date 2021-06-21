package PracticeProblems.Problems.Arrays;

/**
 * MaximumProductOfThreeNumbers
 * https://leetcode.com/problems/maximum-product-of-three-numbers/
 * ============================
 * Given an unsorted array of positive and negative integers, find the maximum product of 3 numbers.
 *
 * Solution:
 * There are only 4 possible combinations of the 3 chosen numbers.
 * Assuming each combination leads to the maximum value, each combination has a constraint on the 3 numbers.
 * 1. + + + : max3
 * 2. + + - : max3
 *    if there were another positive number, + + - could not be the max as + + + would be
 *    similarly, if there were another negative number, + - - would be the max
 *    this means there must only be 3 numbers in the array, so we need max3
 * 3. + - - : max1, min2
 * 4. - - - : max3
 *    if all the numbers are negative, we need the max3 numbers to get the smallest negative value (== max product)
 *
 * After looking at all combinations, we realize there are only 2 possible constraints on the numbers:
 * max3 vs. max1 * min2
 *
 * So we iterate through the array and track max3 and min1, returning the maximum product.
 */
public class MaxProduct3 {

    public int maximumProduct(int[] nums) {

        // keep track of max3 & min2
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int n : nums) {
            // check for max
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
            // check for min
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);

    }

}
