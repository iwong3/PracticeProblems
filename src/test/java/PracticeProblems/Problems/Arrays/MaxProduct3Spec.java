package PracticeProblems.Problems.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class MaxProduct3Spec {

    MaxProduct3 mp3 = new MaxProduct3();

    /* TEST CASES FOR MAIN IMPLEMENTATION */
    @Test
    public void allPositive() {
        int[] nums = new int[]{1, 3, 5, 7, 9};
        int expected_ans = 9 * 7 * 5;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

    @Test
    public void onePositive() {
        int[] nums = new int[]{-5, -4, -3, -2, -1, 1};
        int expected_ans = -5 * -4 * 1;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

    @Test
    public void twoPositive() {
        int[] nums = new int[]{-5, -4, -3, 1, 2};
        int expected_ans = -5 * -4 * 2;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

    @Test
    public void threePositive() {
        int[] nums = new int[]{-5, -4, 1, 2, 3};
        int expected_ans = -5 * -4 * 3;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

    @Test
    public void oneNegative() {
        int[] nums = new int[]{-1, 3, 5, 7, 9};
        int expected_ans = 9 * 7 * 5;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

    @Test
    public void twoNegative() {
        int[] nums = new int[]{-3, -1, 3, 5, 7, 9};
        int expected_ans = 9 * 7 * 5;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

    @Test
    public void threeNegative() {
        int[] nums = new int[]{-9, -5, -3, 1, 3, 5, 7, 9};
        int expected_ans = -9 * -5 * 9;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

    @Test
    public void allNegative() {
        int[] nums = new int[]{-5, -4, -3, -2, -1};
        int expected_ans = -3 * -2 * -1;
        Assert.assertEquals(expected_ans, mp3.maximumProduct(nums));
    }

}
