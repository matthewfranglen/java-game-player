package com.game.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AIPlayer {

    public Map<Move, Integer> maximize(Board board, int depth, Game game) {
        Cell[][] cells = board.getBoard().clone();
        List<Move> possibleMoves = getEmptySlots(board);
        int bestScore = Integer.MIN_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (gameIsOver(board, game) || depth == 0) {
            bestScore = getScore(board, game);
        } else {
            for (Move move : possibleMoves) {
                cells[move.getRow()][move.getColumn()].setCell(game.getAiPlayerPiece());
                Map.Entry<Move, Integer> entry = minimize(board, depth - 1, game).entrySet().iterator().next();
                currentScore = entry.getValue();
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestRow = move.getRow();
                    bestCol = move.getColumn();
                }
                cells[move.getRow()][move.getColumn()].setCell("[ ]");
            }
        }
        Move move = new Move(bestRow, bestCol);
        Map<Move, Integer> moveAndScore = new HashMap<>();
        moveAndScore.put(move, bestScore);

        return moveAndScore;
    }

    private Map<Move, Integer> minimize(Board board, int depth, Game game) {
        Cell[][] cells = board.getBoard().clone();
        List<Move> possibleMoves = getEmptySlots(board);
        int bestScore = Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (gameIsOver(board, game) || depth == 0) {
            bestScore = getScore(board, game);
        } else {
            for (Move move : possibleMoves) {
                cells[move.getRow()][move.getColumn()].setCell(game.getOpponentPiece());
                Map.Entry<Move, Integer> entry = maximize(board, depth - 1, game).entrySet().iterator().next();
                currentScore = entry.getValue();
                if (currentScore < bestScore) {
                    bestScore = currentScore;
                    bestRow = move.getRow();
                    bestCol = move.getColumn();
                }
                cells[move.getRow()][move.getColumn()].setCell("[ ]");
            }
        }
        Move move = new Move(bestRow, bestCol);
        Map<Move, Integer> moveAndScore = new HashMap<>();
        moveAndScore.put(move, bestScore);

        return moveAndScore;
    }

    private boolean gameIsOver(Board board, Game game) {
        return WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())
                || WinChecker.isAWinForOpponent(board, game.getOpponentPiece())
                || TieChecker.isATie(board);
    }

    private static int getScore(Board board, Game game) {
        int score = 0;
        if (WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())) {
            score = 100;
        } else if (WinChecker.isAWinForOpponent(board, game.getOpponentPiece())) {
            score = -100;
        } else if (TieChecker.isATie(board)) {
            score = 0;
        }
        return score;
    }

    private static List<Move> getEmptySlots(Board board){
        List<Move> possibleMoves = new ArrayList<>();
        Cell[][] cells = board.getBoard();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells.length; column++) {
                if(cells[row][column].getCell().equals("[ ]")) {
                    Move move = new Move(row, column);
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves;
    }

}
