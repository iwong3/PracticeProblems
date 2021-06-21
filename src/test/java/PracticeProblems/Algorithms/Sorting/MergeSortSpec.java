package PracticeProblems.Algorithms.Sorting;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortSpec {

    MergeSort ms = new MergeSort();

    /* TEST CASES FOR MAIN IMPLEMENTATION */

    @Test
    public void outOfOrder() {
        int[] input_arr = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5};
        int[] expected_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ms.mergeSort(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void reverseOrder() {
        int[] input_arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ms.mergeSort(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void inOrder() {
        int[] input_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ms.mergeSort(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void allSame() {
        int[] input_arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] expected_arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        ms.mergeSort(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void someSame() {
        int[] input_arr = new int[]{1, 9, 3, 7, 5, 3, 7, 5, 1};
        int[] expected_arr = new int[]{1, 1, 3, 3, 5, 5, 7, 7, 9};
        ms.mergeSort(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void emptyArray() {
        int[] input_arr = new int[]{};
        int[] expected_arr = new int[]{};
        ms.mergeSort(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void nullCheck() {
        int[] input_arr = null;
        int[] expected_arr = null;
        ms.mergeSort(input_arr);
        Assert.assertEquals(expected_arr, input_arr);
    }

    /* TEST CASES FOR PRACTICE IMPLEMENTATIONS */

    @Test
    public void outOfOrderPractice() {
        int[] input_arr = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5};
        int[] expected_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ms.mergeSortPractice(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void reverseOrderPractice() {
        int[] input_arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ms.mergeSortPractice(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void inOrderPractice() {
        int[] input_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ms.mergeSortPractice(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void allSamePractice() {
        int[] input_arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] expected_arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        ms.mergeSortPractice(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void someSamePractice() {
        int[] input_arr = new int[]{1, 9, 3, 7, 5, 3, 7, 5, 1};
        int[] expected_arr = new int[]{1, 1, 3, 3, 5, 5, 7, 7, 9};
        ms.mergeSortPractice(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void emptyArrayPractice() {
        int[] input_arr = new int[]{};
        int[] expected_arr = new int[]{};
        ms.mergeSortPractice(input_arr);
        Assert.assertArrayEquals(expected_arr, input_arr);
    }

    @Test
    public void nullCheckPractice() {
        int[] input_arr = null;
        int[] expected_arr = null;
        ms.mergeSortPractice(input_arr);
        Assert.assertEquals(expected_arr, input_arr);
    }

}
