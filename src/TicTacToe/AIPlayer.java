package TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer {

    private static WinChecker winChecker = new WinChecker();
    private static TieChecker tieChecker = new TieChecker();

    public int[] miniMax(Board board, int depth, String player, Game game) {
        Cell[][] cells = board.getBoard().clone();
        List<int[]> possibleMoves = getEmptySlots(board);
        int bestScore = player.equals(game.getPlayer()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (winChecker.isAWinForPlayer(board, game.getPlayer())
                || winChecker.isAWinForOpponent(board, game.getOpponent())
                || tieChecker.isATie(board)
                || depth == 0) {
            bestScore = getScore(board, game);
        } else {
            for (int[] move : possibleMoves) {
                cells[move[0]][move[1]].setCell(player);
                if (player.equals(game.getPlayer())) {
                    currentScore = miniMax(board, depth - 1, game.getOpponent(), game)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else {
                    currentScore = miniMax(board, depth - 1, game.getPlayer(), game)[0];
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
        if (winChecker.isAWinForPlayer(board, game.getPlayer())) {
            score = 100;
        } else if (winChecker.isAWinForOpponent(board, game.getOpponent())) {
            score = -100;
        } else if (tieChecker.isATie(board)) {
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
