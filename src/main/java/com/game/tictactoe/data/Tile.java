package com.game.tictactoe.data;

public enum Tile {
    NOTHING(" "){
        public Tile inverse() {
            throw new IllegalStateException("Cannot get inverse of nothing");
        }
    },
    X("X") {
        public Tile inverse() {
            return O;
        }
    },
    O("O"){
        public Tile inverse() {
            return X;
        }
    };

    private final String tile;

    private Tile(String tile) {
        this.tile = tile;
    }

    public abstract Tile inverse();

    public String toString() {
        return tile;
    }

}
