import java.util.*;

class WordDictionary {

    // Initialize data structure here
    public WordDictionary() {

    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad") + " should be false");
        System.out.println(dict.search("bad") + " should be true");
        System.out.println(dict.search(".ad") + " should be true");
        System.out.println(dict.search("b..") + " should be true");
    }

    // Add a word into the data structure
    public void addWord(String word) {

    }

    // Returns true if the word is in the data structure
    // A word can contain a dot '.' to represent any one letter
    public boolean search(String word) {
        return false;
    }

}