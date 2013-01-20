package dojo.ttt;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class MapBoard implements Board {

	private final Map<Position, Player> currentBoard;
	private static final int size = 3;

	public MapBoard() {
		currentBoard = Collections
				.unmodifiableMap(new Hashtable<Position, Player>(1, 1.0f));
	}

	private MapBoard(Map<Position, Player> board) {
		currentBoard = Collections
				.unmodifiableMap(new Hashtable<Position, Player>(board));
	}

	public MapBoard(char... board) {
		super();
		if (board.length != size * size)
			assert (false) : "Malformed seed array for the board";
		Map<Position, Player> tempBoard = new Hashtable<Position, Player>(
				board.length, 1.0f);
		Player player = null;
		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				index = 3 * i + j;
				player = (board[index] == 'X' ? Player.X
						: (board[index] == 'O' ? Player.O : null));
				if (player != null) {
					tempBoard.put(new Position(i, j), player);
				}
			}
		}
		currentBoard = Collections.unmodifiableMap(tempBoard);
	}

	public Collection<Position> getAvailablePositions() {
		Collection<Position> availablePositions = new HashSet<Position>(
				TicTacToeUtils.allPositions);
		availablePositions.removeAll(currentBoard.keySet());
		return availablePositions;
	}

	public Board mark(Position position, Player player) {
		Map<Position, Player> markedBoard = new Hashtable<Position, Player>(
				currentBoard);
		markedBoard.put(position, player);
		return new MapBoard(markedBoard);
	}

	public BoardState getState() {

		if (currentBoard.size() == 0)
			return BoardState.PLAY;
		else if (currentBoard.size() == size * size)
			return BoardState.DRAW;
		Set<Position> boardPositions = new HashSet<Position>(
				currentBoard.keySet());
		Set<Position> XPositions = new HashSet<Position>(boardPositions);
		for (Position position : boardPositions)
			if (currentBoard.get(position) == Player.O)
				XPositions.remove(position);
		boardPositions.removeAll(XPositions);
		Set<Position> OPositions = boardPositions;
		// Arbitrarily decided to give X the advantage, so that if
		// both players have winning positions on the board, X will win
		for (Collection<Position> aWinningBoardState : TicTacToeUtils.WIN_COMBINATIONS) {
			if (XPositions.containsAll(aWinningBoardState)) {
				return BoardState.WIN_X;
			} else if (OPositions.containsAll(aWinningBoardState)) {
				return BoardState.WIN_O;
			}
		}
		return BoardState.PLAY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentBoard == null) ? 0 : currentBoard.hashCode());
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
		MapBoard other = (MapBoard) obj;
		if (currentBoard == null) {
			if (other.currentBoard != null)
				return false;
		} else if (!currentBoard.equals(other.currentBoard))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s,\n%s, %s, %s,\n%s, %s, %s,",
				currentBoard.get(new Position(0, 0)) == null ? "-"
						: currentBoard.get(new Position(0, 0)),
				currentBoard.get(new Position(0, 1)) == null ? "-"
						: currentBoard.get(new Position(0, 1)),
				currentBoard.get(new Position(0, 2)) == null ? "-"
						: currentBoard.get(new Position(0, 2)),
				currentBoard.get(new Position(1, 0)) == null ? "-"
						: currentBoard.get(new Position(1, 0)),
				currentBoard.get(new Position(1, 1)) == null ? "-"
						: currentBoard.get(new Position(1, 1)),
				currentBoard.get(new Position(1, 2)) == null ? "-"
						: currentBoard.get(new Position(1, 2)),
				currentBoard.get(new Position(2, 0)) == null ? "-"
						: currentBoard.get(new Position(2, 0)),
				currentBoard.get(new Position(2, 1)) == null ? "-"
						: currentBoard.get(new Position(2, 1)),
				currentBoard.get(new Position(2, 2)) == null ? "-"
						: currentBoard.get(new Position(2, 2))

		);
	}
}
