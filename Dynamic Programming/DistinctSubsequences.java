import java.util.*;

class DistinctSubsequences {

    public static void main(String[] args) {

        // Find number of times T shows up as a distinct subsequence in S
        
        // EX1
        // Input: S = "rabbbit", T = "rabbit"
        // Output: 3
        // Explanation:

        // As shown below, there are 3 ways you can generate "rabbit" from S.
        // (The caret symbol ^ means the chosen letters)

        // rabbbit
        // ^^^^ ^^
        // rabbbit
        // ^^ ^^^^
        // rabbbit
        // ^^^ ^^^

        // EX2
        // Input: S = "babgbag", T = "bag"
        // Output: 5
        // Explanation:

        // As shown below, there are 5 ways you can generate "bag" from S.
        // (The caret symbol ^ means the chosen letters)

        // babgbag
        // ^^ ^
        // babgbag
        // ^^    ^
        // babgbag
        // ^    ^^
        // babgbag
        // ^  ^^
        // babgbag
        //     ^^^

        String t = "babgbag";
        String s = "bag";
        System.out.println(numDistinct(s, t));
    }

    // thoughts: dp
    // if the characters are equal, take the sum of the left and diagonal
    // otherwise, set it equal to left
    // 
    // if chars are equal, then find the number of ways the previous substring fit in, then add 1 for the new char matching
    // otherwise, if it doesnt match, then the number of ways the char can fit is the same as before
    // 
    //   x b a b g b a g
    // x 1 1 1 1 1 1 1 1
    // b 0 1 1 2 2 3 3 3
    // a 0 0 1 1 1 1 4 4
    // g 0 0 0 0 1 1 1 5
    //
    public static int numDistinct(String s, String t) {
        int[][] matrixDP = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < matrixDP.length; i++) {
            matrixDP[i][0] = 0;
        }
        for (int i = 0; i < matrixDP[0].length; i++) {
            matrixDP[0][i] = 1;
        }
        for (int i = 1; i < matrixDP.length; i++) {
            for (int j = 1; j < matrixDP[i].length; j++) {
                int newVal = matrixDP[i][j - 1];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    newVal += matrixDP[i - 1][j - 1];
                }
                matrixDP[i][j] = newVal;
            }
        }

        for (int i = 0; i < matrixDP.length; i++) {
            for (int j = 0; j < matrixDP[i].length; j++) {
                System.out.print("["+matrixDP[i][j]+"]");
            }
            System.out.println("");
        }

        return matrixDP[s.length()][t.length()];
    }

}

// Solution

// public int numDistinct(String S, String T) {
//     // array creation
//     int[][] mem = new int[T.length()+1][S.length()+1];

//     // filling the first row: with 1s
//     for(int j=0; j<=S.length(); j++) {
//         mem[0][j] = 1;
//     }
    
//     // the first column is 0 by default in every other rows but the first, which we need.
    
//     for(int i=0; i<T.length(); i++) {
//         for(int j=0; j<S.length(); j++) {
//             if(T.charAt(i) == S.charAt(j)) {
//                 mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
//             } else {
//                 mem[i+1][j+1] = mem[i+1][j];
//             }
//         }
//     }
    
//     return mem[T.length()][S.length()];
// }