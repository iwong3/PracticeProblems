import java.util.*;

class WordLadder {

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        System.out.println(ladderLength(beginWord, endWord, wordList) + " should be 5");

    }

    // Given a starting word and ending word, and a list of words,
    // return the number of changes it takes to get from start to end.
    // You can only change 1 letter at a time, and only if the resulting word is in wordList.
    //
    // Algo
    // Use BFS
    // Create an adj list from wordlist
    // Starting from start, check all neighbors
    // If they're unvisited, add to a Queue
    // Add current word to visited
    // Continue until we reach goal, return length

    // LEVEL 2 - Implement BiDirectional BFS ;)
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 1. Create an adjList
        Hashtable<String, ArrayList<String>> adjList = new Hashtable<String, ArrayList<String>>();
        // 1a. Add beginWord to adjList
        for (String s : wordList) {
            if (oneLetterDifference(beginWord, s)) {
                ArrayList<String> adjWords = adjList.getOrDefault(beginWord, new ArrayList<>());
                adjWords.add(s);
                if (adjList.containsKey(beginWord)) {
                    adjList.replace(beginWord, adjWords);
                } else {
                    adjList.put(beginWord, adjWords);
                }
            }
        }
        // 1b. Add all words in wordList to adjList
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size(); j++) {
                if (j == i) { continue; }
                if (oneLetterDifference(wordList.get(i), wordList.get(j))) {
                    ArrayList<String> adjWords = adjList.getOrDefault(wordList.get(i), new ArrayList<>());
                    adjWords.add(wordList.get(j));
                    if (adjList.containsKey(wordList.get(i))) {
                        adjList.replace(wordList.get(i), adjWords);
                    } else {
                        adjList.put(wordList.get(i), adjWords);
                    }
                }
            }
        }

        // 2. Initialize variables for BiDirectional BFS
        String currentWord1 = beginWord;
        Hashtable<String, Boolean> visitedStatus1 = new Hashtable<>();
        visitedStatus1.put(currentWord1, true);
        Hashtable<String, Integer> stepsToWord1 = new Hashtable<>();
        stepsToWord1.put(currentWord1, 1);
        Queue<String> toVisit1 = new LinkedList<>();
        toVisit1.add(currentWord1);

        String currentWord2 = endWord;
        Hashtable<String, Boolean> visitedStatus2 = new Hashtable<>();
        visitedStatus2.put(currentWord2, true);
        Hashtable<String, Integer> stepsToWord2 = new Hashtable<>();
        stepsToWord2.put(currentWord2, 1);
        Queue<String> toVisit2 = new LinkedList<>();
        toVisit2.add(currentWord2);

        for (String s : wordList) {
            visitedStatus1.put(s, false);
            stepsToWord1.put(s, Integer.MAX_VALUE);
            if (!s.equals(currentWord2)) {
                visitedStatus2.put(s, false);
                stepsToWord2.put(s, Integer.MAX_VALUE);
            }
        }
        visitedStatus2.put(beginWord, false);
        stepsToWord2.put(beginWord, Integer.MAX_VALUE);

        // 3. Perform BFS
        // Alternate between 1 and 2
        int index = 1;
        while (getSameVisitedWord(visitedStatus1, visitedStatus2, wordList) == null && toVisit1.size() > 0 && toVisit2.size() > 0) {
            Hashtable<String, Boolean> visitedStatus = visitedStatus1;
            Hashtable<String, Integer> stepsToWord = stepsToWord1;
            Queue<String> toVisit = toVisit1;
            if (index % 2 == 0) {
                visitedStatus = visitedStatus2;
                stepsToWord = stepsToWord2;
                toVisit = toVisit2;
            }
            index++;

            String currentWord = toVisit.poll();
            visitedStatus.replace(currentWord, true);
            if (adjList.containsKey(currentWord)) {
                ArrayList<String> neighbors = adjList.get(currentWord);
                for (String s : neighbors) {
                    if (!visitedStatus.get(s)) {
                        toVisit.add(s);
                        stepsToWord.replace(s, Math.min(stepsToWord.get(currentWord) + 1, stepsToWord.get(s)));
                    }
                }
            }
            
            // // 1
            // String currentWord = toVisit1.poll();
            // visitedStatus1.replace(currentWord, true);
            // if (adjList.containsKey(currentWord)) {
            //     ArrayList<String> neighbors = adjList.get(currentWord);
            //     for (String s : neighbors) {
            //         if (!visitedStatus1.get(s)) {
            //             toVisit1.add(s);
            //             stepsToWord1.replace(s, Math.min(stepsToWord1.get(currentWord) + 1, stepsToWord1.get(s)));
            //         }
            //     }
            // }
            
            // // 2
            // currentWord = toVisit2.poll();
            // visitedStatus2.replace(currentWord, true);
            // if (adjList.containsKey(currentWord)) {
            //     ArrayList<String> neighbors = adjList.get(currentWord);
            //     for (String s : neighbors) {
            //         if (!visitedStatus2.get(s)) {
            //             toVisit2.add(s);
            //             stepsToWord2.replace(s, Math.min(stepsToWord2.get(currentWord) + 1, stepsToWord2.get(s)));
            //         }
            //     }
            // }
        }

        if (getSameVisitedWord(visitedStatus1, visitedStatus2, wordList) != null) {
            String sameVisitedWord = getSameVisitedWord(visitedStatus1, visitedStatus2, wordList);
            System.out.println(stepsToWord1.get(sameVisitedWord) + " " + stepsToWord2.get(sameVisitedWord));
            return stepsToWord1.get(sameVisitedWord) + stepsToWord2.get(sameVisitedWord) - 1;
        }

        return -1;
    }

    private static String getSameVisitedWord(Hashtable<String, Boolean> v1, Hashtable<String, Boolean> v2, List<String> wordList) {
        for (String s : wordList) {
            if (v1.get(s) && v2.get(s)) {
                return s;
            }
        }
        return null;
    }

    // public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

    //     // 1. Create an adjList
    //     Hashtable<String, ArrayList<String>> adjList = new Hashtable<String, ArrayList<String>>();
    //     // 1a. Add beginWord to adjList
    //     for (String s : wordList) {
    //         if (oneLetterDifference(beginWord, s)) {
    //             ArrayList<String> adjWords = adjList.getOrDefault(beginWord, new ArrayList<>());
    //             adjWords.add(s);
    //             if (adjList.containsKey(beginWord)) {
    //                 adjList.replace(beginWord, adjWords);
    //             } else {
    //                 adjList.put(beginWord, adjWords);
    //             }
    //         }
    //     }
    //     // 1b. Add all words in wordList to adjList
    //     for (int i = 0; i < wordList.size(); i++) {
    //         for (int j = 0; j < wordList.size(); j++) {
    //             if (j == i) { continue; }
    //             if (oneLetterDifference(wordList.get(i), wordList.get(j))) {
    //                 ArrayList<String> adjWords = adjList.getOrDefault(wordList.get(i), new ArrayList<>());
    //                 adjWords.add(wordList.get(j));
    //                 if (adjList.containsKey(wordList.get(i))) {
    //                     adjList.replace(wordList.get(i), adjWords);
    //                 } else {
    //                     adjList.put(wordList.get(i), adjWords);
    //                 }
    //             }
    //         }
    //     }

    //     // 2. Initialize variables for BFS
    //     String currentWord = beginWord;
    //     Hashtable<String, Boolean> visitedStatus = new Hashtable<>();
    //     visitedStatus.put(currentWord, true);
    //     Hashtable<String, Integer> stepsToWord = new Hashtable<>();
    //     stepsToWord.put(currentWord, 1);
    //     for (String s : wordList) {
    //         visitedStatus.put(s, false);
    //         stepsToWord.put(s, Integer.MAX_VALUE);
    //     }
    //     Queue<String> toVisit = new LinkedList<>();
    //     toVisit.add(currentWord);

    //     // 3. Perform BFS
    //     while (toVisit.size() > 0) {
    //         currentWord = toVisit.poll();
    //         if (currentWord.equals(endWord)) {
    //             return stepsToWord.get(currentWord);
    //         }
    //         visitedStatus.replace(currentWord, true);
    //         if (adjList.containsKey(currentWord)) {
    //             ArrayList<String> neighbors = adjList.get(currentWord);
    //             for (String s : neighbors) {
    //                 if (!visitedStatus.get(s)) {
    //                     toVisit.add(s);
    //                     stepsToWord.replace(s, Math.min(stepsToWord.get(currentWord) + 1, stepsToWord.get(s)));
    //                 }
    //             }
    //         }
    //     }

    //     return -1;
    // }

    // Helper method to check if two strings are only different by 1 letter
    private static boolean oneLetterDifference(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

}