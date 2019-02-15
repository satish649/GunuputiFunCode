package com.gunuputi.funcode.backtracking;

import java.util.HashSet;

public class StringPermutations {

    public static void main(String[] args) {
        printStringPermutations(null);
        printStringPermutations("");
        printStringPermutations("MARCH");
        printStringPermutations("MMMMM");
        printStringPermutations("SATISH");
    }


    private static void printStringPermutations(String input) {

        if(input == null || input.isEmpty()) {
            System.out.printf("Input string need to have atleast 1 char to print possible permutations\n");
            return;
        }
        printStringPermutationInternal(input,  "");

    }

    private static void printStringPermutationInternal(String input, String trackedPath) {
        if(input.length() == 1) {
            System.out.println(trackedPath + input);
            return;
        }
        HashSet<String> visitedChars = new HashSet<>();
        for(int i = 0; i < input.length(); i++) {
            String currChar = String.valueOf(input.charAt(i));
            if(visitedChars.contains(currChar)) {
                continue;
            }
            visitedChars.add(currChar);
            String excludedString = input.substring(0, i) + input.substring(i+1);
            printStringPermutationInternal(excludedString,trackedPath.concat(currChar));
        }
    }
}
