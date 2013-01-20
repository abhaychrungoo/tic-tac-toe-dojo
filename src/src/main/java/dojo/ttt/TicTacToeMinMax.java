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
import java.util.Set;

public class TicTacToeMinMax implements Search {

	class maxComparator implements Comparator<Board> {
		private Player player;

		public maxComparator(Player player) {
			super();
			this.player = player;
		}

		public int compare(Board arg0, Board arg1) {
			return boardUtility(arg0, player) - boardUtility(arg1, player);
		}
	}

	private final int cacheSize = 100;
	private Map<Player, LinkedHashMap<Board, Position>> bestmoveCache;
	private Map<Player, LinkedHashMap<Board, Integer>> utilityCache;

	public TicTacToeMinMax() {
		bestmoveCache = new HashMap<Player, LinkedHashMap<Board, Position>>();
		utilityCache = new HashMap<Player, LinkedHashMap<Board, Integer>>();
		for (Player player : Arrays.asList(Player.X, Player.O)) {
			bestmoveCache.put(player, new LinkedHashMap<Board, Position>(
					cacheSize, .75F, true) {
				@Override
				public boolean removeEldestEntry(Map.Entry eldest) {
					if (size() > cacheSize) {
						System.out.println("best move cache overflow...");
						return true;
					}
					return false;
				}
			});
			utilityCache.put(player, new LinkedHashMap<Board, Integer>(
					cacheSize, .75F, true) {
				@Override
				public boolean removeEldestEntry(Map.Entry eldest) {
					if (size() > cacheSize) {
						System.out.println("utility cache overflow...");
						return true;
					}
					return false;
				}
			});
		}
	}

	private Collection<Board> childBoards(Board board, Player player) {
		Set<Position> availableMoves = new HashSet<Position>(
				board.getAvailablePositions());
		if (availableMoves.size() == 0)
			assert (false);

		List<Board> childBoards = new ArrayList<Board>();
		for (Position candidateMove : availableMoves)
			childBoards.add(board.mark(candidateMove, player));
		return Collections.unmodifiableCollection(childBoards);
	}

	private int boardUtility(Board board, Player player) {
		Integer result = utilityCache.get(player).get(board);
		if (result == null) {
			switch (board.getState()) {
			case DRAW:
				result = 0;
				break;
			case WIN_X:
				result = (player == Player.X ? 100 : -100);
				break;
			case WIN_O:
				result = (player == Player.O ? 100 : -100);
				break;
			case PLAY:
				result = 0 - boardUtility(Collections.max(
						childBoards(board, player.opposite()),
						new maxComparator(player.opposite())),
						player.opposite());
				break;
			default:
				assert (false);
				result = 0;
			}
			utilityCache.get(player).put(board, result);
		} else
			System.out.println("Utility cache hit!!");
		return result;
	}

	public Position getBestMove(Board board, Player player) {
		Random rand = new Random();
		if (board.getAvailablePositions().size() == 9)
			return new Position(rand.nextInt(2), rand.nextInt(2));
		Position bestMove = bestmoveCache.get(player).get(board);
		if (bestMove == null) {
			Board bestBoard = Collections.max(childBoards(board, player),
					new maxComparator(player));
			Collection<Position> tempMoves = new ArrayList<Position>(
					board.getAvailablePositions());
			tempMoves.removeAll(bestBoard.getAvailablePositions());
			bestMove = tempMoves.iterator().next();
			bestmoveCache.get(player).put(board, bestMove);
		} else
			System.out.println("Best Move cache Hit!!");
		return bestMove;
	}
}
