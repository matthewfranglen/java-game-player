package TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer {

    public int[] miniMax(Board board, int depth, String currentPlayer, Game game) {
        Cell[][] cells = board.getBoard().clone();
        List<int[]> possibleMoves = getEmptySlots(board);
        int bestScore = currentPlayer.equals(game.getAiPlayerPiece()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())
                || WinChecker.isAWinForOpponent(board, game.getOpponentPiece())
                || TieChecker.isATie(board)
                || depth == 0) {
            bestScore = getScore(board, game);
        } else {
            for (int[] move : possibleMoves) {
                cells[move[0]][move[1]].setCell(currentPlayer);
                if (currentPlayer.equals(game.getAiPlayerPiece())) {
                    currentScore = miniMax(board, depth - 1, game.getOpponentPiece(), game)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else {
                    currentScore = miniMax(board, depth - 1, game.getAiPlayerPiece(), game)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                cells[move[0]][move[1]].setCell("[ ]");
            }
        }
        return new int [] {bestScore, bestRow, bestCol};
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

    private static List<int[]> getEmptySlots(Board board){
        List<int[]> possibleMoves = new ArrayList<>();
        Cell[][] cells = board.getBoard();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells.length; column++) {
                if(cells[row][column].getCell().equals("[ ]")) {
                    int[] move = new int[2];
                    move[0] = row;
                    move[1] = column;
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves;
    }

}
