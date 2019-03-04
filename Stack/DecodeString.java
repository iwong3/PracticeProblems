import java.util.*;

class DecodeString {

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(s);
        System.out.println(decodeString(s));
    }

    // repeats - stack that holds the number of times to repeat substring
    // substrings - stack that holds the string we'll APPEND TO, NOT THE STRING WE REPEAT
    // substring - represents the string WE WILL REPEAT
    // Ex.
    // "3[a2[c]]"
    // repeats    : 3 , 2
    // substrings : '', a
    // substring  : c
    //
    // repeat [SUBSTRING] [REPEATS.POP] number of times on [SUBSTRINGS.POP]
    // 1. ans: acc (repeat 'c' 2 times on 'a')
    // 2. ans: accaccacc (repeat 'acc' 3 times on '')
    public static String decodeString(String s) {
        String substring = "";
        Stack<Integer> repeats = new Stack<Integer>();
        Stack<String> substrings = new Stack<String>();
        
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int repeat = 0;
                while (Character.isDigit(s.charAt(i))) {
                    // Gets the numerical value via ASCII value by subtracting char 0
                    repeat = 10 * repeat + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                repeats.push(repeat);
            } else if (s.charAt(i) == '[') {
                substrings.push(substring);
                substring = "";
            } else if (s.charAt(i) == ']') {
                String temp = substrings.pop();
                int numRepeat = repeats.pop();
                for (int j = 0; j < numRepeat; j++) {
                    temp += substring;
                }
                substring = temp;
            } else {
                substring += s.charAt(i);
            }
        }

        return substring;
    }

}