package PracticeProblems.Problems.BFS;

import java.util.*;

class WordLadder {

    public int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {

        // init helper vars
        Queue<String> todo = new LinkedList<>();
        HashMap<String, Boolean> checked = new HashMap<>();
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        HashMap<String, String> addedBy = new HashMap<>();

        // create adj list for all words that have a one letter difference w/ beginWord
        ArrayList<String> neighbors = new ArrayList<>();
        String wordOne = "";
        for (int i = 0; i < wordList.size(); i++) {
            wordOne = wordList.get(i);
            if (oneLetterDifference(beginWord, wordOne)) {
                neighbors.add(wordOne);
            }
        }
        adjList.put(beginWord, neighbors);

        // create adj list for all words in wordlist w/ one letter difference
        String wordTwo = "";
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size(); j++) {
                if (i != j) {
                    wordOne = wordList.get(i);
                    wordTwo = wordList.get(j);
                    if (oneLetterDifference(wordOne, wordTwo)) {
                        neighbors = new ArrayList<>();
                        if (adjList.containsKey(wordOne)) {
                            neighbors = adjList.get(wordOne);
                        }
                        neighbors.add(wordTwo);
                        adjList.put(wordOne, neighbors);
                    }
                }
            }
        }

        // use bfs to see if a path exists from beginWord to endWord
        todo.add(beginWord);
        checked.put(beginWord, true);
        String curr = "";
        while (todo.size() > 0) {
            // check if endWord
            curr = todo.poll();
            if (endWord.equals(curr)) {
                break;
            }

            // add all neighbors that haven't been checked & keep track of path
            if (adjList.containsKey(curr)) {
                for (String neighbor : adjList.get(curr)) {
                    if (!checked.containsKey(neighbor)) {
                        todo.add(neighbor);
                        checked.put(neighbor, true);
                        addedBy.put(neighbor, curr);
                    }
                }
            }
        }

        // if we never reached endword, no path exists
        if (!addedBy.containsKey(endWord)) {
            return 0;
        }

        // count steps from endword to beginword
        int steps = 1;
        String prevWord = endWord;
        while (!prevWord.equals(beginWord)) {
            prevWord = addedBy.get(prevWord);
            steps++;
        }

        return steps;

    }

    public int ladderLengthBiDirectionalBFS(String beginWord, String endWord, List<String> wordList) {

        // init helper vars
        Queue<String> todo1 = new LinkedList<>();
        Queue<String> todo2 = new LinkedList<>();
        HashMap<String, Integer> checked1 = new HashMap<>();
        HashMap<String, Integer> checked2 = new HashMap<>();
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        HashMap<String, String> addedBy1 = new HashMap<>();
        HashMap<String, String> addedBy2 = new HashMap<>();

        // create adj list all words that have a one letter difference with beginword
        ArrayList<String> neighbors = new ArrayList<>();
        String wordOne = "";
        if (!wordList.contains(beginWord)) {
            for (int i = 0; i < wordList.size(); i++) {
                wordOne = wordList.get(i);
                if (oneLetterDifference(beginWord, wordOne)) {
                    neighbors.add(wordOne);
                }
            }
            adjList.put(beginWord, neighbors);
        }

        // create adj list for all words in wordlist w/ one letter difference
        String wordTwo = "";
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size(); j++) {
                if (i != j) {
                    wordOne = wordList.get(i);
                    wordTwo = wordList.get(j);
                    if (oneLetterDifference(wordOne, wordTwo)) {
                        neighbors = new ArrayList<>();
                        if (adjList.containsKey(wordOne)) {
                            neighbors = adjList.get(wordOne);
                        }
                        neighbors.add(wordTwo);
                        adjList.put(wordOne, neighbors);
                    }
                }
            }
        }

        // use bd-bfs to see if a path exists from beginWord to endWord
        todo1.add(beginWord);
        todo2.add(endWord);
        ArrayList<String> midWords = new ArrayList<>();
        String curr1 = "";
        String curr2 = "";
        checked1.put(beginWord, 1);
        checked2.put(endWord, 1);
        boolean midWordFound = false;
        while (todo1.size() > 0 || todo2.size() > 0) {
            // check if left and right have met
            midWords = sameWordChecked(checked1, checked2, wordList);
            if (midWords.size() > 0) {
                midWordFound = true;
            }

            // left - add all neighbors that haven't been checked & keep track of path
            curr1 = todo1.poll();
            if (!midWordFound && adjList.containsKey(curr1)) {
                for (String neighbor : adjList.get(curr1)) {
                    // only add if left hasn't come across, or if right isn't finished checking
                    if (!checked1.containsKey(neighbor) && (!checked2.containsKey(neighbor) || checked2.get(neighbor) == 0 || neighbor.equals(endWord))) {
                        todo1.add(neighbor);
                        addedBy1.put(neighbor, curr1);
                        checked1.put(neighbor, 0);
                    }
                }
            }
            checked1.put(curr1, 1);

            // right - add all neighbors that haven't been checked & keep track of path
            curr2 = todo2.poll();
            if (!midWordFound && adjList.containsKey(curr2)) {
                for (String neighbor : adjList.get(curr2)) {
                    // only add if right hasn't come across, or if left isn't finished checking
                    if (!checked2.containsKey(neighbor) && (!checked1.containsKey(neighbor) || checked1.get(neighbor) == 0)) {
                        todo2.add(neighbor);
                        addedBy2.put(neighbor, curr2);
                        checked2.put(neighbor, 0);
                    }
                }
            }
            checked2.put(curr2, 1);
        }

        // if we never reached midword, no path exists
        midWords = sameWordChecked(checked1, checked2, wordList);
        if (midWords.size() == 0) {
            return 0;
        }

        // get smallest midWord path
        int minPath = Integer.MAX_VALUE;
        int steps1 = 1;
        int steps2 = 1;
        for (String midWord : midWords) {
            // count steps from beginWord to midWord
            steps1 = 1;
            String prevWord = midWord;
            while (!prevWord.equals(beginWord)) {
                prevWord = addedBy1.get(prevWord);
                steps1++;
            }

            // count steps from endWord to midWord
            steps2 = 1;
            prevWord = midWord;
            while (!prevWord.equals(endWord)) {
                prevWord = addedBy2.get(prevWord);
                steps2++;
            }

            // check if smallest
            if (steps1 + steps2 - 1 < minPath) {
                minPath = steps1 + steps2 - 1;
            }
        }

        return minPath;

    }

    // helper method to check if two strings are only different by 1 letter
    public boolean oneLetterDifference(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

    // helper method to get all same words checked for bidirectional bfs
    public ArrayList<String> sameWordChecked(HashMap<String, Integer> checked1, HashMap<String, Integer> checked2, List<String> wordList) {
        ArrayList<String> sameWords = new ArrayList<>();
        for (String s : wordList) {
            if (checked1.containsKey(s) && checked2.containsKey(s)) {
                if (checked1.get(s) == 1 && checked2.get(s) == 1) {
                    sameWords.add(s);
                }
            }
        }
        return sameWords;
    }

    // LEVEL 2 - Implement BiDirectional BFS ;)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

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

}
