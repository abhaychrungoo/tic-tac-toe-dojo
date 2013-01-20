package dojo.ttt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TicTacToeMinMax implements Search {

	class UtilityComparator implements Comparator<Board> {
		private Player player;

		public UtilityComparator(Player player) {
			super();
			this.player = player;
		}

		public int compare(Board arg0, Board arg1) {
			return boardUtility(arg0, player) - boardUtility(arg1, player);
		}
	}

	private final int utilityCacheSize;
	private Map<Player, LinkedHashMap<Board, Integer>> utilityCache;

	public TicTacToeMinMax() {
		this(100);
	}

	public TicTacToeMinMax(int cacheSize) {
		super();
		utilityCacheSize = cacheSize;
		utilityCache = new HashMap<Player, LinkedHashMap<Board, Integer>>(2,
				1.0f);
		for (Player player : Arrays.asList(Player.X, Player.O))
			utilityCache.put(player, new LinkedHashMap<Board, Integer>(
					utilityCacheSize + 1, .75F, true) {
				@Override
				public boolean removeEldestEntry(Map.Entry eldest) {
					return size() > utilityCacheSize;
				}
			});
	}

	private Collection<Board> childBoards(Board board, Player player) {
		Collection<Position> availableMoves = new HashSet<Position>(
				board.getAvailablePositions());
		assert (availableMoves.size() > 0) : "Leaf nodes cant have children";
		List<Board> childBoards = new ArrayList<Board>();
		for (Position candidateMove : availableMoves)
			childBoards.add(board.mark(candidateMove, player));
		return Collections.unmodifiableCollection(childBoards);
	}

	private int boardUtility(Board board, Player player) {
		Integer utility = utilityCache.get(player).get(board);
		if (utility == null) {
			switch (board.getState()) {
			case DRAW:
				utility = 0;
				break;
			case WIN_X:
				utility = (player == Player.X ? 100 : -100);
				break;
			case WIN_O:
				utility = (player == Player.O ? 100 : -100);
				break;
			case PLAY:
				utility = 0 - boardUtility(Collections.max(
						childBoards(board, player.opposite()),
						new UtilityComparator(player.opposite())),
						player.opposite());
				break;
			default:
				assert false : "Invalid board state";
			}
			utilityCache.get(player).put(board, utility);
		}
		return utility;
	}

	public Position getBestMove(Board board, Player player) {
		Random rand = new Random();
		if (board.getAvailablePositions().size() == 9)
			return new Position(rand.nextInt(2), rand.nextInt(2));

		Board bestBoard = Collections.max(childBoards(board, player),
				new UtilityComparator(player));
		Collection<Position> tempMoves = new ArrayList<Position>(
				board.getAvailablePositions());
		tempMoves.removeAll(bestBoard.getAvailablePositions());
		return tempMoves.iterator().next();
	}
}
