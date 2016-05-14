package TicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Run {

    private static WinChecker winChecker = new WinChecker();
    private static TieChecker tieChecker = new TieChecker();
    private static boolean gameWon = false;
    private static boolean isATie = false;
    private static String opponent;
    private static String player;
    private static int difficultyLevel;

    public static String getPlayer() {
        return player;
    }

    public static String getOpponent() {
        return opponent;
    }

    public static void main(String[] args) throws IOException {
        Board board = new Board();
        board.createNewBoard();

        System.out.println("Welcome to Tic-Tac-Toe. Would you like to be X or O?");
        opponent = getOpponentPieceChoice();
        player = (opponent.equals("[O]") ? "[X]" : "[O]");
        System.out.println("Would you like to go first? Y/N");
        boolean opponentStarts = isOpponentStarter();
        System.out.println("Lastly, what difficulty level? Easy (E) or Impossible to Win (I)?");
        difficultyLevel = getDepth();
        board.printBoard();
        playGame(opponentStarts, board);
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

    private static void playGame(boolean opponentStarts, Board board) throws IOException {
        if (opponentStarts) {
            do {
                getMove(board);
                gameWon = winChecker.isAWinForOpponent(board);
                isATie = tieChecker.isATie(board);
                if (gameWon || isATie) {
                    break;
                }
                makeBestMove(board);
                gameWon = winChecker.isAWinForPlayer(board);
                isATie = tieChecker.isATie(board);
                if (gameWon || isATie) {
                    break;
                }
            } while (!gameWon && !isATie);
        } else {
            do {
                makeBestMove(board);
                gameWon = winChecker.isAWinForPlayer(board);
                isATie = tieChecker.isATie(board);
                if (gameWon || isATie) {
                    break;
                }
                getMove(board);
                gameWon = winChecker.isAWinForOpponent(board);
                isATie = tieChecker.isATie(board);
                if (gameWon || isATie) {
                    break;
                }
            } while (!gameWon && !isATie);
        }
        if (winChecker.isAWinForOpponent(board)) {
            System.out.println("YOU WIN!");
        } else if (winChecker.isAWinForPlayer(board)) {
            System.out.println("YOU LOSE!");
        } else {
            System.out.println("IT'S A TIE");
        }
    }

    private static void getMove(Board board) throws IOException {
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
                getMove(board);
            } else {
                currentBoard[i][j].setCell(opponent);
                board.setBoard(currentBoard);
                board.printBoard();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("That is not a valid location. Try again\n");
            getMove(board);
        }
    }

    private static void makeBestMove(Board board) {
        System.out.println("AI's move:");
        AIPlayer aiPlayer = new AIPlayer();
        int[] bla = aiPlayer.miniMax(board, difficultyLevel, player);
        int i = bla[1];
        int j = bla[2];

        Cell[][] currentBoard = board.getBoard();
        if (!currentBoard[i][j].getCell().equals("[ ]")) {
            makeBestMove(board);
        } else {
            currentBoard[i][j].setCell(player);
            board.setBoard(currentBoard);
            board.printBoard();
        }
    }

}