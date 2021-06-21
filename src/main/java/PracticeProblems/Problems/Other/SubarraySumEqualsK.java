import java.util.*;

class SubarraySumEqualsK {

    // Given an array, return the number of consecutive subarrays that would lead to a sum of k

    public static void main(String[] args) {

        int[] nums = new int[]{-1, 3, 2, -4, 4, -2, 6, -4};
        int k = 2;
        // expected output - 5

        System.out.println(subarraySum(nums, k));

    }

    public static int subarraySum(int[] nums, int k) {
        ArrayList<Integer> sumsAtIndex = new ArrayList<>();
        sumsAtIndex.add(0);
        int currentSum = 0;
        int ans = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            sumsAtIndex.add(currentSum);
            if (sumsAtIndex.contains(currentSum - k)) {
                ans++;
            }
        }

        return ans;
    }

}

// Solution

// We keep track of the sums from the beginning to each index
// If the current index's sum MINUS k is equal to any existing sum,
// that means the interval from the previous index to the current index
// is a subarray with a sum of k

// Reuse
// If you can find a solution by calculating the difference between the current index
// and a previous index, then you can use this technique
// Keep track of previous values using a hashmap

// public int subarraySum(int[] nums, int k) {
//     int sum = 0, result = 0;
//     Map<Integer, Integer> preSum = new HashMap<>();
//     preSum.put(0, 1);

//     for (int i = 0; i < nums.length; i++) {
//         sum += nums[i];
//         if (preSum.containsKey(sum - k)) {
//             result += preSum.get(sum - k);
//         }
//         preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
//     }

//     return result;
// }