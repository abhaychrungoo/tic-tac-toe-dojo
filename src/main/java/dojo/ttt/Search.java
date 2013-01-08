package dojo.ttt;

public interface Search {

    /**
     * Find the best move for the current player.
     * @param board current game board
     * @param player current player
     * @return position that this player should mark next
     */
    Position getBestMove(Board board, Player player);

}