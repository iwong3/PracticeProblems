import java.util.*;

// SLIDING WINDOW

public class LongestSubstringWithoutRepeatingCharacter {

    public static void main(String[] args) {
        String s = "abcdefga";
        System.out.println("Length of longest substring without repeating characters");
        System.out.println(s);
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> sequence = new LinkedList<Character>();
        HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
        int longest = 0;
        boolean removed = false;
        char tempChar = 'a';
        
        // Sliding window technique
        // Iterate through the string, keeping track of the substring in a char Queue and all visited characters in a hashmap.
        // - If we run into a character not in our hashmap, add it to the queue and the hashmap
        // - If we run into a character in the hashmap, check our substring queue to see if it's the longest so far.
        //   - If so, update the answer.
        //   - Otherwise, remove characters from the beginning of the substring (Queue poll) until we run into the character.
        //     Also, remove the character from the hashmap
        for (int i = 0; i < s.length(); i++) {
            if (chars.containsKey(s.charAt(i))) {
                if (sequence.size() > longest) longest = sequence.size();
                while (!removed) {
                    tempChar = sequence.poll();
                    chars.remove(tempChar);
                    if (tempChar == s.charAt(i)) {
                        removed = true;
                    }
                }
                removed = false;
            }
            
            sequence.add(s.charAt(i));
            chars.put(s.charAt(i), 1);
        }
        
        // Final check once we finish iterating
        if (sequence.size() > longest) longest = sequence.size();
        
        return longest;
    }

}
