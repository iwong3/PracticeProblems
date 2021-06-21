import java.util.*;

class WordBreakTwo {

    // HINT
    // DFS or DP

    // Thoughts
    // DP might be easy to implement but has problems when wordDict is a huge data set
    // DP stores memory in cache so we would have a n^2 storage for cache
    // This makes a iterative or searching algorithm potentially more realistic

    // DFS thoughts
    // starting with all words
    // if the word 'w1' equals the beginning of string, then compare it to all words 'w2' in wordDict.
    // if w1 + w2 still equals the beginning of the string, repeat

    // Find all possible combinations of strings in wordDict to create s
    
    public static void main(String[] args) {
        String s = "catsanddog";
        ArrayList<String> wordDict = new ArrayList<String>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        // Expected output 1
        // [
        //      "cats and dog",
        //      "cat sand dog"
        // ]

        // ===
        
        // String s = "pineapplepenapple";
        // List<String> wordDict = new String[]{"apple", "pen", "applepen", "pine", "pineapple"};

        // Expected output 2
        // [
        //      "pine apple pen apple",
        //      "pineapple pen apple",
        //      "pine applepen apple"
        // ]

        List<String> ans = wordBreak(s, wordDict);
        for (String i : ans) {
            System.out.println(i);
        }
    }

    // Thoughts on how to retain each word added
    // Use a Hashtable that stores the combinedword as the key and a list of words as the value
    // Problematic when a string can be reached through a different combination of substrings (thats the problem lol)
    // 2nd try
    // Only store words with spaces, but remove the spaces when comparing
    public static List<String> wordBreak(String s,  List<String> wordDict) {
        ArrayList<String> ans = new ArrayList<String>();
        ArrayList<String> wordsForNextIteration = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();

        // Initialization for valid choices
        for (String word : wordDict) {
            if (word.length() < s.length()) {
                if (s.substring(0, word.length()).equals(word)) {
                    wordsForNextIteration.add(word);
                }
            }
        }
        // While we have valid choices
        while (wordsForNextIteration.size() > 0) {
            // Check if that choice + a word in wordDict would also be a valid choice
            for (String currWord : wordsForNextIteration) {
                for (String word : wordDict) {
                    String combinedWord = currWord + " " + word;
                    String combinedWordNoSpaces = removeSpaces(combinedWord);
                    if (combinedWordNoSpaces.length() <= s.length()) {
                        // If we found a match, add to answers
                        if (combinedWordNoSpaces.equals(s)) {
                            ans.add(combinedWord);
                        } else {
                            // Else, if valid substring, add it to temp
                            if (s.substring(0, combinedWordNoSpaces.length()).equals(combinedWordNoSpaces)) {
                                temp.add(combinedWord);
                            }
                        }
                    }
                }
            }
            // Set next valid choices to temp, reset temp
            wordsForNextIteration = temp;
            temp = new ArrayList<String>();
        }

        return ans;
    }

    private static String removeSpaces(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                ans += s.charAt(i);
            }
        }
        return ans;
    }

}

// Solution

// public List<String> wordBreak(String s, Set<String> wordDict) {
//     return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
// }

// List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>> map) {
//     if (map.containsKey(s)) {
//         return map.get(s);
//     }

//     LinkedList<String> res = new LinkedList<String>();

//     if (s.length() == 0) {
//         res.add("");
//         return res;
//     }

//     for (String word : wordDict) {
//         if (s.startsWith(word)) {
//             List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
//             for (String sub : sublist) {
//                 res.add(word + (sub.isEmpty() ? "" : " ") + sub);
//             }
//         }
//     }

//     map.put(s, res);
//     return res;
// }