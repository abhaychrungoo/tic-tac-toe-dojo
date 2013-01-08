package dojo.ttt;

import static java.lang.String.format;

public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Position)) {
            return false;
        }
        Position that = (Position) obj;
        return this.row == that.row && this.column == that.column;
    }

    @Override
    public String toString() {
        return format("{row=%s, column=%s}", row, column);
    }

}