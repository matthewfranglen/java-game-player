package com.game.tictactoe.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.stream.IntStream;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class BoardTest {

    @Test
    public void testInitialTilesBlank() {
        Board board = new Board();

        assertTrue(
                IntStream.range(0, Board.COLUMNS)
                    .boxed()
                    .flatMap(column ->
                        IntStream.range(0, Board.ROWS)
                            .mapToObj(row -> board.at(column, row))
                    )
                    .allMatch(Tile.NOTHING::equals)
        );
    }

    @Test
    public void testMovedTilesNonBlank() {
        Board board = new Board().move(0, 0, Tile.X);

        assertEquals(Tile.X, board.at(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotMoveOntoOccupiedSpace() {
        new Board().move(0, 0, Tile.X).move(0, 0, Tile.X);
    }

    @Test
    public void testInitialToString() throws IOException {
        String expected = Resources.toString(
                Resources.getResource("board/blank.txt"), Charsets.UTF_8
            );
        String actual = new Board().toString() + "\n";

        assertEquals(expected, actual);
    }

    @Test
    public void testMovedToString() throws IOException {
        String expected = Resources.toString(
                Resources.getResource("board/moved.txt"), Charsets.UTF_8
            );
        String actual = new Board()
            .move(0, 0, Tile.X)
            .move(1, 1, Tile.O)
            .toString()
            + "\n";

        assertEquals(expected, actual);
    }

}
