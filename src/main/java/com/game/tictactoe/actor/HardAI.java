package com.game.tictactoe.actor;

import org.springframework.stereotype.Component;

import com.game.tictactoe.data.Board;
import com.game.tictactoe.data.Tile;

@Component
public class HardAI implements AI {

    public Board move(Board board, Tile tile) {
        return board;
    }

}
