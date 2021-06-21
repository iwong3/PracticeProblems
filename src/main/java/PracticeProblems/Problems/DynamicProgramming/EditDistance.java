public class EditDistance {

    public static void main(String[] args) {
        String word1 = "helloworld";
        String word2 = "yo";
        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);
        System.out.println(minDistance(word1, word2));
    }

    public static int minDistance(String word1, String word2) {
        
        //ALGO
        //DP
        //   X H O R S E
        // X 0 1 2 3 4 5
        // R 1 1 2 2 3 4
        // O 2 2 1 2 3 3
        // S 3 3 2 2 2 3
        // 
        // box = steps it takes to get from left word to top word
        // box to top left = replace a character
        // box to left = add a character
        // box to top = remove a character

        int n = word1.length();
        int m = word2.length();
        
        // if one of the strings is empty
        if (n * m == 0)
          return n + m;

        // array to store the convertion history
        int [][] d = new int[n + 1][m + 1];

        // init boundaries
        for (int i = 0; i < n + 1; i++) {
          d[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
          d[0][j] = j;
        }

        // DP compute 
        for (int i = 1; i < n + 1; i++) {
          for (int j = 1; j < m + 1; j++) {
            int left = d[i - 1][j] + 1;
            int down = d[i][j - 1] + 1;
            int left_down = d[i - 1][j - 1];
            if (word1.charAt(i - 1) != word2.charAt(j - 1))
              left_down += 1;
            d[i][j] = Math.min(left, Math.min(down, left_down));

          }
        }
        return d[n][m];
    }
}