package dojo.ttt;

import static java.lang.String.format;

public class Position implements Comparable<Position> {

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

    @Override
    public String toString() {
        return format("{row=%s, column=%s}", row, column);
    }

	public int compareTo(Position other) {
		// TODO Auto-generated method stub
		int difference = 3*this.row + this.column - 3*other.row - other.column;
		return (difference == 0 ? 0 : (difference > 0 ? 1 :-1));
	}

}