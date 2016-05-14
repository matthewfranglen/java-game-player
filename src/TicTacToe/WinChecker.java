package TicTacToe;

public class WinChecker {

    public boolean isAWinForPlayer(Board board) {
        return isAWin(board, Run.getPlayer());
    }

    public boolean isAWinForOpponent(Board board) {
        return isAWin(board, Run.getOpponent());
    }

    private boolean isAWin(Board board, String player) {
        Cell[][] cells = board.getBoard();

        if (cells[0][0].getCell().equals(player)
                && cells[1][0].getCell().equals(player)
                && cells[2][0].getCell().equals(player)) {
            return true;
        }
        if (cells[0][0].getCell().equals(player)
                && cells[0][1].getCell().equals(player)
                && cells[0][2].getCell().equals(player)) {
            return true;
        }
        if (cells[1][0].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[1][2].getCell().equals(player)) {
            return true;
        }
        if (cells[2][0].getCell().equals(player)
                && cells[2][1].getCell().equals(player)
                && cells[2][2].getCell().equals(player)) {
            return true;
        }
        if (cells[0][0].getCell().equals(player)
                && cells[1][0].getCell().equals(player)
                && cells[2][0].getCell().equals(player)) {
            return true;
        }
        if (cells[0][1].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[2][1].getCell().equals(player)) {
            return true;
        }
        if (cells[0][2].getCell().equals(player)
                && cells[1][2].getCell().equals(player)
                && cells[2][2].getCell().equals(player)) {
            return true;
        }
        if (cells[0][0].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[2][2].getCell().equals(player)) {
            return true;
        }
        if (cells[0][2].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[2][0].getCell().equals(player)) {
            return true;
        }
        return false;
    }

}