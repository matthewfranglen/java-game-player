package com.game.tictactoe.actor;

import com.game.tictactoe.data.Board;
import com.game.tictactoe.data.Tile;

public interface AI {

    public Board move(Board board, Tile tile);

}
