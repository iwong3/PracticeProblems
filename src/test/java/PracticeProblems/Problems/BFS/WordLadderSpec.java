package PracticeProblems.Problems.BFS;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class WordLadderSpec {

    WordLadder wl = new WordLadder();

    /* TEST CASES FOR MAIN IMPLEMENTATION */

    @Test
    public void ladderExists() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        int expectedAnswer = 5;
        Assert.assertEquals(expectedAnswer, this.wl.ladderLengthBFS(beginWord, endWord, wordList));
    }

    @Test
    public void ladderDoesNotExist() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        int expectedAnswer = 0;
        Assert.assertEquals(expectedAnswer, this.wl.ladderLengthBFS(beginWord, endWord, wordList));
    }

    @Test
    public void ladderExistsBidirectional() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        int expectedAnswer = 5;
        Assert.assertEquals(expectedAnswer, this.wl.ladderLengthBiDirectionalBFS(beginWord, endWord, wordList));
    }

    @Test
    public void ladderDoesNotExistBidirectional() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        int expectedAnswer = 0;
        Assert.assertEquals(expectedAnswer, this.wl.ladderLengthBiDirectionalBFS(beginWord, endWord, wordList));
    }


}
