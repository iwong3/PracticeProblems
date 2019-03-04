import java.util.*;

// DP Solution

class JumpGame {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4, 1, 1, 1, 1};
        for (int i : nums) {
            System.out.print("["+i+"]");
        }
        System.out.println("");
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        int[] jumps = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i+j < nums.length) {
                    if (jumps[i+j] == 0) {
                        jumps[i+j] = jumps[i]+1;
                    } else {
                        jumps[i+j] = Math.min(jumps[i+j], jumps[i]+1);
                    }
                }
            }
        }
        for (int i : jumps) {
            System.out.print("["+i+"]");
        }
        System.out.println("");
        return jumps[nums.length-1];
    }

}