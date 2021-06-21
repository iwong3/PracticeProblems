import java.util.*;

class WordBreak {

    public static void main(String[] args) {

        // Find out if s can be created using strings in wordDict
        
        // EX1
        // Input: s = "leetcode", wordDict = ["leet", "code"]
        // Output: true
        // Explanation: Return true because "leetcode" can be segmented as "leet code".

        // EX2
        // Input: s = "applepenapple", wordDict = ["apple", "pen"]
        // Output: true
        // Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        //             Note that you are allowed to reuse a dictionary word.

        // EX3
        // Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        // Output: false

        // EX1
        // String s = "leetcode";
        // ArrayList<String> wordDict = new ArrayList<>();
        // wordDict.add("leet");
        // wordDict.add("code");

        // EX2
        // String s = "applepenapple";
        // ArrayList<String> wordDict = new ArrayList<>();
        // wordDict.add("apple");
        // wordDict.add("pen");

        // EX3
        String s = "catsandog";
        ArrayList<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");

        System.out.println(wordBreak(s, wordDict));

    }

    // Method 1: Brute Force
    // Compare all entries in the wordDict to the string. If the string begins with the entry, remove it from the string and repeat
    // If the string is empty, then it's possible
    public static boolean wordBreak(String s, List<String> wordDict) {
        while (s.length() > 0) {
            boolean change = false;
            for (String word : wordDict) {
                if (word.length() <= s.length()) {
                    if (s.substring(0, word.length()).equals(word)) {
                        s = s.substring(word.length());
                        change = true;
                    }
                }
            }
            if (!change) {
                return false;
            }
        }
        return true;
    }

}

// SOLUTION

// public class Solution {
//     public boolean wordBreak(String s, Set<String> dict) {
        
//         boolean[] f = new boolean[s.length() + 1];
        
//         f[0] = true;
        
        
//         /* First DP
//         for(int i = 1; i <= s.length(); i++){
//             for(String str: dict){
//                 if(str.length() <= i){
//                     if(f[i - str.length()]){
//                         if(s.substring(i-str.length(), i).equals(str)){
//                             f[i] = true;
//                             break;
//                         }
//                     }
//                 }
//             }
//         }*/
        
//         //Second DP
//         for(int i=1; i <= s.length(); i++){
//             for(int j=0; j < i; j++){
//                 if(f[j] && dict.contains(s.substring(j, i))){
//                     f[i] = true;
//                     break;
//                 }
//             }
//         }
        
//         return f[s.length()];
//     }
// }