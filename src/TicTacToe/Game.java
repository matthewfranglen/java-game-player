package TicTacToe;

public class Game {

    private static boolean isWon = false;
    private static boolean isATie = false;
    private static String opponent;
    private static String player;
    private static int difficultyLevel;

    public static boolean isWon() {
        return isWon;
    }

    public static void setIsWon(boolean isWon) {
        Game.isWon = isWon;
    }

    public static boolean isATie() {
        return isATie;
    }

    public static void setIsATie(boolean isATie) {
        Game.isATie = isATie;
    }

    public static String getOpponent() {
        return opponent;
    }

    public static void setOpponent(String opponent) {
        Game.opponent = opponent;
    }

    public static String getPlayer() {
        return player;
    }

    public static void setPlayer(String player) {
        Game.player = player;
    }

    public static int getDifficultyLevel() {
        return difficultyLevel;
    }

    public static void setDifficultyLevel(int difficultyLevel) {
        Game.difficultyLevel = difficultyLevel;
    }
}
