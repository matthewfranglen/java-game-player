package com.game.tictactoe.data;

import static com.google.common.base.Preconditions.checkState;

import lombok.Data;

import com.game.tictactoe.actor.AI;

@Data
public class Game {

    private Board board;
    private final Tile playerTile;
    private final AI opponent;

    public Game(Tile playerTile, AI opponent) {
        board = new Board();
        this.playerTile = playerTile;
        this.opponent = opponent;
    }

    public Tile getOpponentTile() {
        return playerTile.inverse();
    }

    public boolean canMove(int x, int y) {
        return board.canMove(x, y);
    }

    public void move(int x, int y) {
        checkState(isNotOver(), "Game over man, game over");

        board = board.move(x, y, playerTile);
        if (isNotOver()) {
            board = opponent.move(board, getOpponentTile());
        }
    }

    public void opponentMove() {
        checkState(isNotOver(), "Game over man, game over");

        board = opponent.move(board, getOpponentTile());
    }

    public boolean isWon() {
        return board.sequences().stream()
            .anyMatch(sequence -> sequence.stream().allMatch(getPlayerTile()::equals));
    }

    public boolean isLost() {
        return board.sequences().stream()
            .anyMatch(sequence -> sequence.stream().allMatch(getOpponentTile()::equals));
    }

    public boolean isOver() {
        return board.isFull() || isWon() || isLost();
    }

    public boolean isNotOver() {
        return ! isOver();
    }

    public String toString() {
        return board.toString();
    }

}
