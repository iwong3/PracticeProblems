package PracticeProblems.Problems.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class ProductExceptSelfSpec {

    ProductExceptSelf pes = new ProductExceptSelf();

    /* TEST CASES FOR MAIN IMPLEMENTATION */
    @Test
    public void manyNumbers() {
        int[] nums = new int[]{1, 2, 3, 4};
        int[] expectedAns = new int[]{24, 12, 8, 6};
        Assert.assertArrayEquals(expectedAns, pes.productExceptSelf(nums));
    }

    @Test
    public void twoNumbers() {
        int[] nums = new int[]{1, 2};
        int[] expectedAns = new int[]{2, 1};
        Assert.assertArrayEquals(expectedAns, pes.productExceptSelf(nums));
    }

    @Test
    public void negativeNumbers() {
        int[] nums = new int[]{5, -3, 2, -4};
        int[] expectedAns = new int[]{24, -40, 60, -30};
        Assert.assertArrayEquals(expectedAns, pes.productExceptSelf(nums));
    }

    @Test
    public void hasZero() {
        int[] nums = new int[]{10, 0, 20, 30};
        int[] expectedAns = new int[]{0, 6000, 0, 0};
        Assert.assertArrayEquals(expectedAns, pes.productExceptSelf(nums));
    }
}
