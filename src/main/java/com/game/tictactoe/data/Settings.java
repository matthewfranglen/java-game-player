package com.game.tictactoe.data;

import lombok.Data;

@Data
public class Settings {

    private final Tile playerTile;
    private final boolean playFirst;
    private final boolean winnable;

}
