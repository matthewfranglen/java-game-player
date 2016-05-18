package com.game.tictactoe.data;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.ImmutableList;

public class Board {

    public static final int ROWS = 3;
    public static final int COLUMNS = 3;
    public static final int TILES = ROWS * COLUMNS;

    private final List<Tile> tiles;

    public Board() {
        this(
            IntStream.range(0, TILES)
                .mapToObj(v -> Tile.NOTHING)
                .collect(Collectors.toList())
        );
    }

    private Board(List<Tile> tiles) {
        this.tiles = ImmutableList.copyOf(tiles);
    }

    public Tile at(int row, int column) {
        return tiles.get(toIndex(row, column));
    }

    public List<List<Tile>> sequences() {
        ImmutableList.Builder<List<Tile>> builder = new ImmutableList.Builder<>();

        IntStream.range(0, COLUMNS).mapToObj(this::column).forEach(builder::add);
        IntStream.range(0, ROWS).mapToObj(this::row).forEach(builder::add);
        builder.add(diagonalDown());
        builder.add(diagonalUp());

        return builder.build();
    }

    public List<Tile> column(int column) {
        return IntStream.range(0, ROWS)
            .mapToObj(row -> at(row, column))
            .collect(Collectors.toList());
    }

    public List<Tile> row(int row) {
        return IntStream.range(0, COLUMNS)
            .mapToObj(column -> at(row, column))
            .collect(Collectors.toList());
    }

    public List<Tile> diagonalDown() {
        return IntStream.range(0, Math.min(ROWS, COLUMNS))
            .mapToObj(i -> at(i, i))
            .collect(Collectors.toList());
    }

    public List<Tile> diagonalUp() {
        int range = Math.min(ROWS, COLUMNS);
        return IntStream.range(0, range)
            .mapToObj(i -> at(i, range - i))
            .collect(Collectors.toList());
    }

    public boolean canMove(int row, int column) {
        return at(row, column) == Tile.NOTHING;
    }

    public Board move(int row, int column, Tile move) {
        checkArgument(canMove(row, column), "Cannot move on occupied square");

        int index = toIndex(row, column);

        return new Board(
            IntStream.range(0, TILES)
                .mapToObj(i -> i == index ? move : tiles.get(i))
                .collect(Collectors.toList())
        );
    }

    public boolean isFull() {
        return tiles.stream().noneMatch(Tile.NOTHING::equals);
    }

    private int toIndex(int row, int column) {
        return (column * ROWS) + row;
    }

    public String toString() {
        return IntStream.range(0, COLUMNS)
            .mapToObj(column ->
                    IntStream.range(0, ROWS)
                        .mapToObj(row -> at(row, column))
                        .map(Object::toString)
                        .collect(Collectors.joining("|"))
            )
            .collect(Collectors.joining("\n-+-+-\n"));
    }

}
