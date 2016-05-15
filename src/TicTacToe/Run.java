package TicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Run {

    private static WinChecker winChecker = new WinChecker();
    private static TieChecker tieChecker = new TieChecker();

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Board board = new Board();
        board.createNewBoard();

        System.out.println("Welcome to Tic-Tac-Toe. Would you like to be X or O?");
        game.setOpponent(getOpponentPieceChoice());
        game.setPlayer(game.getOpponent().equals("[O]") ? "[X]" : "[O]");
        System.out.println("Would you like to go first? Y/N");
        boolean opponentStarts = isOpponentStarter();
        System.out.println("Lastly, what difficulty level? Easy (E) or Impossible to Win (I)?");
        game.setDifficultyLevel(getDepth());
        board.printBoard();
        playGame(opponentStarts, board, game);
    }

    private static String getOpponentPieceChoice() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.toUpperCase().equals("X") && !input.toUpperCase().equals("O")) {
            System.out.println("Please enter either X or O");
            input = br.readLine();
        }
        return "[" + input.toUpperCase() + "]";
    }

    private static boolean isOpponentStarter() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.toUpperCase().equals("Y") && !input.toUpperCase().equals("N")) {
            System.out.println("Please enter either Y or N");
            input = br.readLine();
        }
        if (input.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    private static int getDepth() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.toUpperCase().equals("E")  && !input.toUpperCase().equals("I")) {
            System.out.println("Please enter either E (easy) or I (impossible to win)");
            input = br.readLine();
        }
        if (input.toUpperCase().equals("E")) {
            return 1;
        }
        return 10;
    }

    private static void playGame(boolean opponentStarts, Board board, Game game) throws IOException {
        if (opponentStarts) {
            do {
                getMove(board, game);
                game.setIsWon(winChecker.isAWinForOpponent(board, game.getOpponent()));
                game.setIsATie(tieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
                makeBestMove(board, game);
                game.setIsWon(winChecker.isAWinForPlayer(board, game.getPlayer()));
                game.setIsATie(tieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
            } while (!game.isATie() && !game.isWon());
        } else {
            do {
                makeBestMove(board, game);
                game.setIsWon(winChecker.isAWinForPlayer(board, game.getPlayer()));
                game.setIsATie(tieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
                getMove(board, game);
                game.setIsWon(winChecker.isAWinForOpponent(board, game.getOpponent()));
                game.setIsATie(tieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
            } while (!game.isATie() && !game.isWon());
        }
        if (winChecker.isAWinForOpponent(board, game.getOpponent())) {
            System.out.println("YOU WIN!");
        } else if (winChecker.isAWinForPlayer(board, game.getPlayer())) {
            System.out.println("YOU LOSE!");
        } else {
            System.out.println("IT'S A TIE");
        }
    }

    private static void getMove(Board board, Game game) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Position as row|number " +
                "(eg the first cell is 00, the one to it's right is 01, the one below it is 10: ");
        try {
            String input = br.readLine();
            int i = Integer.parseInt(input.substring(0, 1));
            int j = Integer.parseInt(input.substring(1, 2));
            Cell[][] currentBoard = board.getBoard();
            if (!currentBoard[i][j].getCell().equals("[ ]")) {
                System.out.println("Already a piece there. Try again\n");
                getMove(board, game);
            } else {
                currentBoard[i][j].setCell(game.getOpponent());
                board.setBoard(currentBoard);
                board.printBoard();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("That is not a valid location. Try again\n");
            getMove(board, game);
        }
    }

    private static void makeBestMove(Board board, Game game) {
        System.out.println("AI's move:");
        AIPlayer aiPlayer = new AIPlayer();
        int[] bla = aiPlayer.miniMax(board, game.getDifficultyLevel(), game.getPlayer(), game);
        int i = bla[1];
        int j = bla[2];

        Cell[][] currentBoard = board.getBoard();
        if (!currentBoard[i][j].getCell().equals("[ ]")) {
            makeBestMove(board, game);
        } else {
            currentBoard[i][j].setCell(game.getPlayer());
            board.setBoard(currentBoard);
            board.printBoard();
        }
    }

}