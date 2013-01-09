package dojo.ttt;

import java.util.ArrayList;
import java.util.Collection;

import static dojo.ttt.BoardState.DRAW;
import static dojo.ttt.BoardState.PLAY;
import static dojo.ttt.BoardState.WIN_O;
import static dojo.ttt.BoardState.WIN_X;
import static dojo.ttt.Player.O;
import static dojo.ttt.Player.X;
import dojo.ttt.TicTacToeUtils.Callback;
import static dojo.ttt.TicTacToeUtils.WIN_COMBINATIONS;
import static dojo.ttt.TicTacToeUtils.forEach;

public class ArrayBoard implements Board {

    private final Player[][] grid;

    public ArrayBoard() {
        grid = new Player[3][3];
    }

    public ArrayBoard(Player[][] grid) {
        this.grid = grid;
    }

    @Override
    public Collection<Position> getAvailablePositions() {
        final ArrayList<Position> positions = new ArrayList<Position>(9);

        forEach(new Callback() {
            @Override
            public void execute(int row, int column) {
                if (grid[row][column] == null) {
                    positions.add(new Position(row, column));
                }
            }
        });

        return positions;
    }

    @Override
    public Board mark(final Position position, final Player player) {
        final Player[][] newGrid = new Player[3][3];

        forEach(new Callback() {
            @Override
            public void execute(int row, int column) {
                Player mark;
                if (position.equals(new Position(row, column))) {
                    mark = player;
                } else {
                    mark = grid[row][column];
                }
                newGrid[row][column] = mark;
            }
        });

        return new ArrayBoard(newGrid);
    }

    @Override
    public BoardState getState() {
        if (isWin(X)) {
            return WIN_X;
        } else if (isWin(O)) {
            return WIN_O;
        } else if (getAvailablePositions().isEmpty()) {
            return DRAW;
        } else {
            return PLAY;
        }
    }

    private boolean isWin(Player player) {

        for (Collection<Position> winCombination : WIN_COMBINATIONS) {
            boolean win = true;

            for (Position position : winCombination) {
                final Player mark = grid[position.getRow()][position.getColumn()];
                if (mark != player) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ArrayBoard)) {
            return false;
        }

        final ArrayBoard that = (ArrayBoard) obj;

        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {

                final Player thisMark = ArrayBoard.this.grid[row][column];
                final Player thatMark = that.grid[row][column];

                if (thisMark == null ^ thatMark == null) {
                    return false;
                }

                if (thisMark != thatMark) {
                    return false;
                }
            }
        }

        return true;
    }
}