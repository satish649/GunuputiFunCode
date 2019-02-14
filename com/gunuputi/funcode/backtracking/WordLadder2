package com.gunuputi.funcode.backtracking;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 126. Word Ladder II, Leet code
 * <p>
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 */
public class WordLadder2 {

    private String beginWord;
    private String endWord;
    private HashSet<String> wordList;
    private static final String TC_OUTPUT_FORMAT = "Begin Word: [%s], EndWord: [%s], WordList: [%s]";

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<String> travelledPath = new ArrayList<>();
        List<List<String>> optimalChains = new ArrayList<>();
        findLaddersHelper(beginWord, endWord, wordList, optimalChains, travelledPath);
        if (!optimalChains.isEmpty()) {
            System.out.println("Optimal Transformations: ");
            for (List<String> tranformationChain : optimalChains) {
                System.out.println(Arrays.toString(tranformationChain.toArray()));
            }
        } else {
            System.out.println("No such path exists!");
        }
        return optimalChains;
    }

    private void findLaddersHelper(String beginWord, String endWord, Set<String> wordList,
        List<List<String>> optimalChains, List<String> travelledPath) {

        // base case
        if (beginWord.equals(endWord)) {
            travelledPath.add(beginWord);
            if (optimalChains.isEmpty()) {
                List<String> newChain = travelledPath.stream().collect(Collectors.toList());
                optimalChains.add(newChain);
            } else if (travelledPath.size() < optimalChains.get(0).size()) {
                optimalChains.clear();
                optimalChains.add(travelledPath.stream().collect(Collectors.toList()));

            } else if (travelledPath.size() == optimalChains.get(0).size()) {
                optimalChains.add(travelledPath.stream().collect(Collectors.toList()));
            } else {
                // ignore since there is a better possibility
            }
            travelledPath.remove(beginWord);
            return;
        }

        travelledPath.add(beginWord);
        Set<String> oneLevelTransofmrations = getOneLevelTransformations(beginWord, wordList, travelledPath);
        for (String oneLevelTransofmation : oneLevelTransofmrations) {
            // System.out.println("Being word: " + beginWord + " One Level Transoformation: " + oneLevelTransofmation);
            findLaddersHelper(oneLevelTransofmation, endWord, wordList, optimalChains, travelledPath);
        }
        travelledPath.remove(beginWord);
    }

    Set<String> getOneLevelTransformations(String inputWord, Set<String> wordList, List<String> travelledPath) {

        HashSet<String> childNodes = new HashSet<>();
        HashSet<String> travelledPathWords = Sets.newHashSet(travelledPath);
        for (int i = 0; i < inputWord.length(); i++) {
            char currentChar = inputWord.charAt(i);
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == currentChar) {
                    continue;
                }
                String childNode = replaceStringAt(inputWord, i, j);
                if (wordList.contains(childNode) && !travelledPathWords.contains(childNode)) {
                    childNodes.add(childNode);
                }
            }
        }

        return childNodes;
    }

    String replaceStringAt(String inputWord, int replacementPos, char replacementChar) {
        String newString = "";
        for (int i = 0; i < inputWord.length(); i++) {
            char c = inputWord.charAt(i);
            if (replacementPos == i) {
                c = replacementChar;
            }
            newString = newString + c;
        }
        return newString;
    }

    public static void main(String[] args) {
        WordLadder2 shortestDistanceBetweenWords = new WordLadder2();
        shortestDistanceBetweenWords.tc1();
        shortestDistanceBetweenWords.tc2();
        shortestDistanceBetweenWords.tc3();
    }

    private void tc1() {
        System.out.println("--------------------------------------------------");
        beginWord = "go";
        endWord = "om";
        wordList = Sets.newHashSet("mo", "om", "oo");
        findLadders(beginWord, endWord, wordList);
    }

    private void tc2() {
        System.out.println("--------------------------------------------------");
        beginWord = "hit";
        endWord = "cog";
        wordList = Sets.newHashSet("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(String.format(TC_OUTPUT_FORMAT, beginWord, endWord, wordList));
        findLadders(beginWord, endWord, wordList);
    }

    private void tc3() {
        System.out.println("--------------------------------------------------");
        beginWord = "hit";
        endWord = "cog";
        wordList = Sets.newHashSet("hot", "dot", "dog", "lot", "log");
        System.out.println(String.format(TC_OUTPUT_FORMAT, beginWord, endWord, wordList));
        findLadders(beginWord, endWord, wordList);
    }
}
