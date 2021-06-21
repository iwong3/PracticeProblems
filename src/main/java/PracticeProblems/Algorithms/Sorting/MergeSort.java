package PracticeProblems.Algorithms.Sorting;

/**
 * MergeSort
 * =========
 * Recursively split array in half until you reach individual elements.
 * Merge halves together in a sorted order.
 *
 * Runtime Complexity: O(nlogn)
 * Space Complexity:   O(n)
 * Stable sort
 */
public class MergeSort {

    public MergeSort() {}

    public void mergeSort(int[] arr) {

        // null or empty
        if (null == arr || arr.length == 0) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);

    }

    private void mergeSort(int[] arr, int l, int r) {

        // one element - no more splitting
        if (l >= r) {
            return;
        }

        // split into halves
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        // copy left/right splits to prepare for merging
        int[] leftArr = new int[m - l + 1];
        int[] rightArr = new int[r - m];
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[l + i];
        }
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[m + 1 + i];
        }

        // merge splits into original arr in order
        int i = l;
        int li = 0;
        int ri = 0;
        while (li < leftArr.length && ri < rightArr.length) {
            if (leftArr[li] <= rightArr[ri]) {
                arr[i] = leftArr[li];
                li++;
            } else {
                arr[i] = rightArr[ri];
                ri++;
            }
            i++;
        }
        // merge remaining unfinished split
        while (li < leftArr.length) {
            arr[i] = leftArr[li];
            li++;
            i++;
        }
        while (ri < rightArr.length) {
            arr[i] = rightArr[ri];
            ri++;
            i++;
        }

    }

    /* PRACTICE IMPLEMENTATIONS */

    public void mergeSortPractice(int[] arr) {

        if (null == arr || arr.length < 2) {
            return;
        }

        mergeSortPractice(arr, 0, arr.length - 1);

    }

    // RUNTIME
    // logn - recursively calling on splits
    // n - we essentially iterating through the whole array for merging
    //   - n + n/2 + n/4 + ... = 2n = n
    // SPACE
    // n - leftArr and rightArr use same logic as n for runtime
    public void mergeSortPractice(int[] arr, int l, int r) {

        // stop at one element
        if (l >= r) {
            return;
        }

        // find middle and recurse on splits
        int m = (l + r) / 2;
        mergeSortPractice(arr, l, m);
        mergeSortPractice(arr, m + 1, r);

        // copy original arr values into left and right arrays to prepare for merging
        int[] leftArr = new int[m - l + 1];
        int[] rightArr = new int[r - m];
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[l + i];
        }
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[m + 1 + i];
        }

        // merge until one half is completed
        int arrIdx = l;
        int leftIdx = 0;
        int rightIdx = 0;
        while (leftIdx < leftArr.length && rightIdx < rightArr.length) {
            if (leftArr[leftIdx] < rightArr[rightIdx]) {
                arr[arrIdx] = leftArr[leftIdx];
                leftIdx++;
            } else {
                arr[arrIdx] = rightArr[rightIdx];
                rightIdx++;
            }
            arrIdx++;
        }

        // finish remaining half
        while (leftIdx < leftArr.length) {
            arr[arrIdx] = leftArr[leftIdx];
            leftIdx++;
            arrIdx++;
        }
        while (rightIdx < rightArr.length) {
            arr[arrIdx] = rightArr[rightIdx];
            rightIdx++;
            arrIdx++;
        }

    }

}
