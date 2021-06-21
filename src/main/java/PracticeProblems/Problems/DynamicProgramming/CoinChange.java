package PracticeProblems.Problems.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem:
 * Given a set of (coin values: [1, 2, 5]) and (target value: 11),
 * Find the minimum number of coins needed to reach that target value,
 * assuming you can use an unlimited amount of each coin.
 * Return -1 if reaching the target value is impossible.
 */
public class CoinChange {

    // solution explanation:
    // at our current target value, we'll recursively try every coin starting from greatest -> smallest
    // once we hit target (rem = 0), build the number of coins needed by backtracking and adding 1 coin at a time
    // for every current target, we'll try all coins and keep track of the min coins needed
    // that value will be cached so we don't perform repeat calculations
    // this way, we're guaranteed to find the correct min coins for target values smallest -> greatest

    // example:
    // coins: [1, 2, 5]
    // 1. target: 11, coin: 5
    // 2. target: 6, coin: 5
    // 3. target: 1, coin: 5
    // 4. target: -4, invalid
    // 5. target: 1, coin: 2
    // 6. target: -1, invalid
    // 7. target: 1, coin: 1
    // 8. target: 0, valid
    // 9. return to target 1, min coin = 1
    // 10. we've tried all coins for target 1, so 1 coin is guaranteed to be the min coins for target 1. cache
    // 11. target: 6, coin: 2
    // 12. target: 4, coin: 5
    // 13. target: -1, invalid
    // 14. target: 4, coin: 2
    // 15. target: 2, coin: 5
    // 16. target: -3, invalid
    // 17. target: 2, coin: 2
    // 18. target: 0, valid
    // 19. return to target 2, min coin = 1
    // 20. target: 2, coin: 1
    // 21. target: 1 - we've confirmed the min coins for target 1, so return it
    // 22. return to target 2 - using coin 2, the min coins is 1. using coin 1, the min coins is 2.
    //     1 < 2, so the min coins to reach target 2 is 1. cache
    // repeat until done

    // key = amount left, value = min coins
    HashMap<Integer, Integer> lookup = new HashMap<>();

    public int coinChange(int[] coins, int target) {

        Arrays.sort(coins);
        return coinChangeHelper(coins, target);

    }

    public int coinChangeHelper(int[] coins, int rem) {

        // passed target
        if (rem < 0) {
            return -1;
        }

        // hit target
        if (rem == 0) {
            return 0;
        }

        // if we've calculated the min coins needed for this remainder, return it
        if (lookup.containsKey(rem)) {
            return lookup.get(rem);
        }

        // minimum number of coins needed to hit CURRENT remainder
        int currMin = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int minCoinsNeeded = coinChangeHelper(coins, rem - coins[i]);
            if (minCoinsNeeded >= 0 && minCoinsNeeded < currMin) {
                currMin = minCoinsNeeded + 1;
            }
        }

        lookup.put(rem, currMin == Integer.MAX_VALUE ? -1 : currMin);
        return lookup.get(rem);

    }

    /* PRACTICE IMPLEMENTATIONS */

    HashMap<Integer, Integer> lookupPractice;

    public int coinChangePractice(int[] coins, int target) {

        lookupPractice = new HashMap<>();
        return coinChangePracticeHelper(coins, target);

    }

    public int coinChangePracticeHelper(int[] coins, int target) {

        // went past target
        if (target < 0) {
            return -1;
        }

        // hit target
        if (target == 0) {
            return 0;
        }

        // check lookup to see if we've done this target already
        if (lookup.containsKey(target)) {
            return lookup.get(target);
        }

        // try all coins from highest value -> lowest value
        // keep track of which leads to fewest number of coins
        int minCoins = Integer.MAX_VALUE;
        int currCoins = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            currCoins = 1 + coinChangePracticeHelper(coins, target - coins[i]);
            if (currCoins > 0 && currCoins < minCoins) {
                minCoins = currCoins;
            }
        }

        // put result into lookup
        if (minCoins == Integer.MAX_VALUE) {
            lookup.put(target, -1);
        } else {
            lookup.put(target, minCoins);
        }

        return lookup.get(target);

    }

}
