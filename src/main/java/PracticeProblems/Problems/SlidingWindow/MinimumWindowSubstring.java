import java.util.*;

class MinimumWindowSubstring {

    public static void main(String[] args) {

        // Find the minimum window in S which contains all letters in T

        // find shortest ABC
        // in BCCBACCBA
        // b c
        // b c c | c
        // b c c b | c, b
        // b c c b a | c, b
        // c b a -> record
        // b a
        // b a c
        // a c
        // a c c | c
        // a c c b | c -> record
        // c b
        // c b a -> record
        // rules
        // keep track of which chars we need
        // add ALL relevant chars until we've added all unique chars
        // every time we add a duplicate char, keep track of it
        // when we've added all unique chars, check the first char. if its part of the duplicate list, remove it. repeat this until duplicate is empty.
        // if the first char is not on the list, then this is a minimum string. record it.
        // then, remove all first chars until duplicate list is empty.
        // repeat until we've parsed the whole string.
        // Input: S = "ADOBECODEBANC", T = "ABC"
        // Output: "BANC"

        // Lv2
        // T can have duplicates, T = "AABC"
        // Output: "ABODECODEBA"

        String s = "ABODECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        LinkedList<Character> chars = new LinkedList<Character>();
        LinkedList<Integer> indexes = new LinkedList<Integer>();
        ArrayList<Character> tChars = new ArrayList<Character>();
        Hashtable<Character, Integer> numChars = new Hashtable<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            tChars.add(t.charAt(i));
            numChars.put(t.charAt(i), 0);
        }
        int shortestLength = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // If we run into a t char, add it to our queues, add counter in our hashtable
            for (Character c : tChars) {
                if (s.charAt(i) == c) {
                    chars.add(s.charAt(i));
                    indexes.add(i);
                    numChars.replace(s.charAt(i), numChars.get(s.charAt(i)) + 1);
                }
            }
            // If we've added all characters
            if (addedAllChars(numChars, tChars)) {
                // If there are duplicates in the front of the string, remove them
                ArrayList<Character> duplicates = getDuplicateCharacters(numChars, tChars);
                if (duplicates.size() > 0) {
                    while (duplicates.contains(chars.peek())) {
                        Character removed = chars.poll();
                        duplicates.remove(removed);
                        numChars.replace(removed, numChars.get(removed) - 1);
                        indexes.poll();
                    }
                }
                // Find starting and ending index of answer
                int startIndex = indexes.peek();
                int endIndex = indexes.peekLast();
                int length = endIndex - startIndex + 1;
                if (length < shortestLength) {
                    shortestLength = length;
                    start = startIndex;
                    end = endIndex;
                }

                // Then, remove until there are no duplicates
                while (duplicates.size() > 0) {
                    Character removed = chars.poll();
                    numChars.replace(removed, numChars.get(removed) - 1);
                    indexes.poll();
                    if (duplicates.contains(removed)) {
                        duplicates.remove(removed);
                    }
                }

                // We are left with a string with all chars once, so remove the first one
                Character removed = chars.poll();
                numChars.replace(removed, numChars.get(removed) - 1);
                indexes.poll();
            }
        }

        return s.substring(start, end+1);
    }

    private static boolean addedAllChars(Hashtable<Character, Integer> numChars, ArrayList<Character> tChars) {
        for (Character c : tChars) {
            if (numChars.get(c) < 1) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Character> getDuplicateCharacters(Hashtable<Character, Integer> numChars, ArrayList<Character> tChars) {
        ArrayList<Character> duplicates = new ArrayList<Character>();
        for (Character c : tChars) {
            if (numChars.get(c) > 1) {
                duplicates.add(c);
            }
        }
        return duplicates;
    }

}