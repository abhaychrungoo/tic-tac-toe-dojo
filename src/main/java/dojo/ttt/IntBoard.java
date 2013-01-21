package dojo.ttt;

import java.util.Collection;
import java.util.HashSet;

/*
 *This board will be represented by 18 bits. Bitset vs integer. We 
 *choose the lowest  18 bits of an integer for this board.
 *First 9 bits represent positions for player X. 1 shows occupied. 
 *0 shows vacant. The next 9 bits store positions for Y. 
 */
public class IntBoard implements Board {
	private final int board;

	public IntBoard() {
		board = 0;
	}

	private IntBoard(int board) {
		this.board = board;
	}

	public IntBoard(char[] tokens) {
		int stagingboard = 0, bitmask = 0;
		int index = 0, offset = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				index = 3 * i + j;
				offset = (tokens[index] == 'X' ? 0 : (tokens[index] == 'O' ? 9
						: -1));
				bitmask = 1 << (index + offset);
				if (offset != -1)
					stagingboard = stagingboard | bitmask;
			}
		}
		this.board = stagingboard;
	}

	@Override
	public Collection<Position> getAvailablePositions() {
		int boardCopy = board;
		Collection<Position> availablePositions = new HashSet<Position>();
		int xPositions = boardCopy & ((1 << 9) - 1);
		int oPositions = (boardCopy >> 9) & ((1 << 9) - 1);
		int boardPositions = xPositions | oPositions;
		int counter = 0;
		while (counter < 9) {
			System.out.println(Integer.toBinaryString(boardPositions));
			if ((boardPositions & 0x1) == 0)
				availablePositions.add(new Position(counter / 3, counter % 3));
			boardPositions = boardPositions >> 1;
			counter++;
		}
		System.out.println("availablePositions " + availablePositions);
		return availablePositions;
	}

	@Override
	public Board mark(Position position, Player player) {
		int offset = (player == Player.X ? 0 : (player == Player.O ? 9 : -1));
		int index = 3 * position.getRow() + position.getColumn();
		int bitmask = 1 << (index + offset);
		return new IntBoard(this.board | bitmask);
	}

	@Override
	public BoardState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + board;
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
		IntBoard other = (IntBoard) obj;
		if (board != other.board)
			return false;
		return true;
	}

}
