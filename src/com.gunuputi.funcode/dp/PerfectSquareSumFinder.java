package com.gunuputi.funcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LC# 279. Perfect Squares
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class PerfectSquareSumFinder {

    public int minPerfectSquares(int input) {
        if (input <= 0) {
            return 0;
        }
        int[] minPerfectSquaresCache = new int[input + 1];
        minPerfectSquaresCache[0] = 0;
        minPerfectSquaresCache[1] = 1;
        List<Integer> perfectSquaresVisited = new ArrayList<>();
        perfectSquaresVisited.add(1);

        for (int i = 2; i <= input; i++) {
            if (isPerfectSquare(i)) {
                minPerfectSquaresCache[i] = 1;
                perfectSquaresVisited.add(i);
            } else {
                int minPerfectSequares = input;
                for (Integer perfectSquare : perfectSquaresVisited) {
                    int noOfPerfectSquares = minPerfectSquaresCache[perfectSquare] + minPerfectSquaresCache[i - perfectSquare];
                    if (noOfPerfectSquares < minPerfectSequares) {
                        minPerfectSequares = noOfPerfectSquares;
                    }
                }
                minPerfectSquaresCache[i] = minPerfectSequares;
            }
        }

        return minPerfectSquaresCache[input];
    }

    private boolean isPerfectSquare(int i) {
        double squareRoot = Math.sqrt((double) i);
        if (Math.floor(squareRoot) == squareRoot) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        PerfectSquareSumFinder perfectSquareSumFinder = new PerfectSquareSumFinder();
        for (int i = 0; i < 100; i++) {
            System.out.println("Min perfect suqares need to compute Sum [" + i + "] is: " + perfectSquareSumFinder.minPerfectSquares(i));
        }
    }
}
