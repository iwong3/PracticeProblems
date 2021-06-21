package PracticeProblems.Problems.DynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

public class CoinChangeSpec {

    CoinChange cc = new CoinChange();

    /* TEST CASES FOR MAIN IMPLEMENTATION */

    @Test
    public void possible() {
        int[] coins = new int[]{1, 2, 5};
        int target = 11;
        int expected_ans = 3;
        Assert.assertEquals(expected_ans, cc.coinChange(coins, target));
    }

    @Test
    public void impossible() {
        int[] coins = new int[]{2, 5};
        int target = 3;
        int expected_ans = -1;
        Assert.assertEquals(expected_ans, cc.coinChange(coins, target));
    }

    @Test
    public void targetIsCoin() {
        int[] coins = new int[]{1, 2, 5, 11, 14};
        int target = 11;
        int expected_ans = 1;
        Assert.assertEquals(expected_ans, cc.coinChange(coins, target));
    }

    @Test
    public void targetDoesntUseLargestCoin() {
        int[] coins = new int[]{1, 2, 5, 9};
        int target = 7;
        int expected_ans = 2;
        Assert.assertEquals(expected_ans, cc.coinChange(coins, target));
    }

    /* TEST CASES FOR PRACTICE IMPLEMENTATION */

    @Test
    public void possiblePractice() {
        int[] coins = new int[]{1, 2, 5};
        int target = 11;
        int expected_ans = 3;
        Assert.assertEquals(expected_ans, cc.coinChangePractice(coins, target));
    }

    @Test
    public void impossiblePractice() {
        int[] coins = new int[]{2, 5};
        int target = 3;
        int expected_ans = -1;
        Assert.assertEquals(expected_ans, cc.coinChangePractice(coins, target));
    }

    @Test
    public void targetIsCoinPractice() {
        int[] coins = new int[]{1, 2, 5, 11, 14};
        int target = 11;
        int expected_ans = 1;
        Assert.assertEquals(expected_ans, cc.coinChangePractice(coins, target));
    }

    @Test
    public void targetDoesntUseLargestCoinPractice() {
        int[] coins = new int[]{1, 2, 5, 9};
        int target = 7;
        int expected_ans = 2;
        Assert.assertEquals(expected_ans, cc.coinChangePractice(coins, target));
    }

}
