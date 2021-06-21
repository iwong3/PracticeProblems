### Blind Curated 75 - Summarized Approaches

# 1. Two Sum
**Problem**
Given an array of integers and a target integer, return the indicies of two integers that add up to target. There is only one solution.

**Solution**
Use a HashMap to keep track of the number needed to sum to the target at each index. Iterate through the array. If the current number exists in the HashMap, then we've found the pair. Return the current index and the index in the HashMap.

**Example**
arr=[2, 3, 5, 7], target=8

HashMap
6 -> 0 (at index 0, the number 2 needs the number 6 to sum to target 8)
5 -> 1

Once we reach 5, we see that it is in the HashMap, so we return [1, 2].

# 2. Longest Substring Without Repeating Characters
**Problem**
Given a string s, return the length of the longest substring without repeating characters.

**Solution**
Use a sliding window + HashMap approach. Keep track of current substring length with left and right pointers. Iterate through the string, using a HashMap to keep track of all characters. If we reach a character that's already in the HashMap, check if it's the longest. Then, while left is less than the first index of the repeated character, remove the character at left from the HashMap and increase left. Finally, after iterating through the string, check again if the current substring is the longest.

**Example**
s="abcba"

HashMap
a -> 0
b -> 1
c -> 2

When we hit 'b' again, set max=3. Then, remove chars from HashMap until left hits 'b'. In this case, we remove two chars, 'a' and 'b'.

HashMap
c -> 2

Then, procede as usual.

HashMap
c -> 2
b -> 3
a -> 4

Do one last check after iterating. "cba" has the same max length, so we don't update.

# 19. Unique Paths
**Problem**
In a m*n grid, starting from the top left, you need to go to the bottom right. You can either move right or down. How many unique paths can you take?

**Solution**
Initialize a matrix m*n with row 0 = 1 and col 0 = 1. Starting from row=1 and col=1, every cell is equal to the sum of the cells to the left and up.

# 20. Climbing Stairs
**Problem**
You can climb stairs by 1 or 2 steps at a time. How many ways can you climb to step n?

**Solution**
There is 1 way to climb to n=1. There are 2 ways to climb to n=2. Ways to climb to i = i-2 + i-1.

# 42. House Robber
**Problem**
Given an array where the index represents a house and the value represents the amount of money in that house, return the maximum amount of money a thief could steal given that you cannot rob two adjacent houses.

**Solution**
Create an array that keeps track of the max you can steal at index i. For any index i, max[i] equals the greater of (max[i-2] + nums[i]) and (max[i-1]). Basically, you can steal from 2 houses ago and the current house, or you can skip the current house.

Note that you need to initialize max[0] = nums[0] and max[1] = Math.max(nums[0], nums[1]). (At house i=1, the max you can have there is the max between house 0 and house 1).
