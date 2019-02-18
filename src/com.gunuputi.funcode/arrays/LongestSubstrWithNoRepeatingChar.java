package com.gunuputi.funcode.arrays;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithNoRepeatingChar {

    public String findLongestSubstringWithNonRepeatingCharInSubStr(String input) {

        String currentSubStr = "";
        int currentSubStrStartPos = 0;
        int longestSubStrStartPos = 0;
        int longestSubStrLen = 0;
        int currentSubStrLen = 0;
        HashMap<String, Integer> charLatestPositionsMap = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            String currChar = String.valueOf(input.charAt(i));
            Integer latestCharPos = charLatestPositionsMap.get(currChar);
            // case where we know there is no problem
            if (latestCharPos == null || latestCharPos < currentSubStrStartPos) {
                currentSubStr = currentSubStr + currChar;
                currentSubStrLen++;
                charLatestPositionsMap.put(currChar, i);
            } else {
                if (currentSubStrLen > longestSubStrLen) {
                    longestSubStrLen = currentSubStrLen;
                    longestSubStrStartPos = currentSubStrStartPos;
                }
                currentSubStrLen = i - latestCharPos;
                currentSubStrStartPos = latestCharPos + 1;
                charLatestPositionsMap.put(currChar, i);
            }
        }

        if (currentSubStrLen > longestSubStrLen) {
            longestSubStrLen = currentSubStrLen;
            longestSubStrStartPos = currentSubStrStartPos;
        }

        return input.substring(longestSubStrStartPos, longestSubStrStartPos + longestSubStrLen);
    }

    public String findLongestSubStringWithNonRepeatingCharsInWholeString(String input) {
        Map<String, Boolean> duplicateChars = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            String currChar = String.valueOf(input.charAt(i));
            if (duplicateChars.containsKey(currChar)) {
                duplicateChars.put(currChar, true);
                continue;
            }
            duplicateChars.put(currChar, false);
        }

        String longestSubStr = "";
        String currentSubStr = "";
        for (int i = 0; i < input.length(); i++) {
            String currChar = String.valueOf(input.charAt(i));
            if (duplicateChars.get(currChar)) {
                // take the first LongSubString with non-repeating char
                if (currentSubStr.length() > longestSubStr.length()) {
                    longestSubStr = currentSubStr;
                    currentSubStr = "";
                }
                // skip processing duplicate chars
                continue;
            }
            currentSubStr = currentSubStr + currChar;
        }

        if (currentSubStr.length() > longestSubStr.length()) {
            longestSubStr = currentSubStr;
        }
        return longestSubStr;
    }

    public static void main(String[] args) {
        LongestSubstrWithNoRepeatingChar longestSubstrWithNoRepeatingChar = new LongestSubstrWithNoRepeatingChar();
        longestSubstrWithNoRepeatingChar.testCases();
    }

    private void testCases() {
        // non-repeating char in the sub string
        System.out.println("Non-repeating char in the sub string: ");
        assertStringEquals("dabce", "dabcebaddabd", 1, "SUB_STRING");
        assertStringEquals("vdf", "dvdf", 2, "SUB_STRING");
        assertStringEquals("", "", 3, "SUB_STRING");
        assertStringEquals("A", "AAAA", 4, "SUB_STRING");
        assertStringEquals("abcdef", "abcdaabcdef", 5, "SUB_STRING");
        assertStringEquals("abcd", "abcabcd", 6, "SUB_STRING");
        // non-repeating char in the whole string
        System.out.println("Non-repeating char in the whole string: ");
        assertStringEquals("ATI", "SATISH", 1, "WHOLE_STRING");
        assertStringEquals("", "", 2, "WHOLE_STRING");
        assertStringEquals("S", "S", 3, "WHOLE_STRING");
        assertStringEquals("ATI", "SATISH", 4, "WHOLE_STRING");
        assertStringEquals("ABCDEF", "KGXABCDEFXPQRS", 5, "WHOLE_STRING");
        assertStringEquals("B", "AAAAB", 5, "WHOLE_STRING");
    }

    private void assertStringEquals(String expected, String input, int testCaseID, String testCaseToExecute) {
        String actual = null;
        if (testCaseToExecute.equals("SUB_STRING")) {
            actual = findLongestSubstringWithNonRepeatingCharInSubStr(input);
        }
        if (testCaseToExecute.equals("WHOLE_STRING")) {
            actual = findLongestSubStringWithNonRepeatingCharsInWholeString(input);
        }

        if (expected.equals(actual)) {
            System.out.println(
                String.format("Test Case [%s] Passed, Input string [%s], Expected [%s], Actual [%s]", testCaseID, input,
                    expected, actual));
        } else {
            System.out.println(
                String.format("Test Case [%s] Failed, Input string [%s], Expected [%s], Actual [%s]", testCaseID, input,
                    expected, actual));
        }
    }
}
