package com.gunuputi.funcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.
 */
public class NQueens {

    public static void main(String[] args) {
        printQueeenPostions(7);
    }

    private static void printQueeenPostions(int gridSize) {

        HashSet<Position> restrictedPositions = new HashSet<>();
        int startRow = 0;
        List<Position> trackedPositions = new ArrayList<>();
        boolean foundMoves = findQueenPositions(gridSize, startRow, restrictedPositions, trackedPositions);
        if (foundMoves) {
            System.out.println(
                "Success Position for grid size :[" + gridSize + "] are [");
            trackedPositions.stream().forEach(System.out::println);
            System.out.println("]");
        } else {
            System.out.println("Not able to find potential moves for grid size : [" + gridSize + "]");
        }
    }

    private static Boolean findQueenPositions(int gridSize, int startRow, Set<Position> restrictedPositions,
        List<Position> trackedPositions) {

        // base case
        if (startRow >= gridSize) {
            return true;
        }

        for (int col = 0; col < gridSize; col++) {
            Position currPos = Position.of(startRow, col);
            if (restrictedPositions.contains(currPos)) {
                continue;
            }

            HashSet<Position> currentPosImpactedMoves = findQueenMovesFromPosition(currPos, gridSize);
            currentPosImpactedMoves.addAll(restrictedPositions);

            boolean isSuccess = findQueenPositions(gridSize, startRow + 1, currentPosImpactedMoves, trackedPositions);
            if (isSuccess) {
                trackedPositions.add(currPos);
                return true;
            }
        }

        return false;
    }

    private static HashSet<Position> findQueenMovesFromPosition(Position currPos, int gridSize) {

        HashSet<Position> possibleQueenMoves = new HashSet<>();

        // add all the cols for the current position row
        for (int col = 0; col < gridSize; col++) {
            possibleQueenMoves.add(Position.of(currPos.row, col));
        }

        // add all the rows for the current position col
        for (int row = 0; row < gridSize; row++) {
            possibleQueenMoves.add(Position.of(row, currPos.col));
        }

        // down and left side of diagonal
        for (int row = currPos.row, col = currPos.col; row < gridSize && col >= 0; row++, col--) {
            possibleQueenMoves.add(Position.of(row, col));
        }

        // up and  right side of diagonal
        for (int row = currPos.row, col = currPos.col; row >= 0 && col < gridSize; row--, col++) {
            possibleQueenMoves.add(Position.of(row, col));
        }

        // up and left side of diagonal
        for (int row = currPos.row, col = currPos.col; row >= 0 && col >= 0; row--, col--) {
            possibleQueenMoves.add(Position.of(row, col));
        }

        // down and right side of diagonal
        for (int row = currPos.row, col = currPos.col; row < gridSize && col < gridSize; row++, col++) {
            possibleQueenMoves.add(Position.of(row, col));
        }

        String debugMsg = String.format("Position [%s] Restricted Moves found: [%s]", currPos, possibleQueenMoves);
        System.out.println(debugMsg);
        return possibleQueenMoves;
    }

    public static class Position {
        private int row;
        private int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public static Position of(int row, int col) {
            return new Position(row, col);
        }

        public String toString() {
            return String.format("Position (%s,%s)", row, col);
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            Position tmp = (Position) other;
            if (tmp.row == row && tmp.col == col) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + row;
            return result;
        }
    }
}
