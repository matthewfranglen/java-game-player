package TicTacToe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WinCheckerTest {

    @Test
    public void givenEmptyBoard_whenPlayingGame_thenNoWinners() {
        Board board = new Board();
        board.createNewBoard();
        Game game = new Game();
        game.setAiPlayerPiece("[X]");
        game.setOpponentPiece("[O]");

        assertFalse(WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
        assertFalse(WinChecker.isAWinForOpponent(board, game.getOpponentPiece()));
    }

    @Test
    public void givenDiagonalX_whenAIisX_thenAIisWinner() {
        Board board = new Board();
        board.createNewBoard();
        Cell[][] cells = board.getBoard();
        cells[0][0].setCell("[X]");
        cells[1][1].setCell("[X]");
        cells[2][2].setCell("[X]");
        board.setBoard(cells);
        Game game = new Game();
        game.setAiPlayerPiece("[X]");
        game.setOpponentPiece("[O]");

        assertTrue(WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
        assertFalse(WinChecker.isAWinForOpponent(board, game.getOpponentPiece()));
    }

}
