package dojo.ttt;

import java.util.Collection;

/**
 * A 3 x 3 tic-tac-toe game board:
 *
 * 1 | 2 | 3
 * ---------
 * 4 | 5 | 6
 * ---------
 * 7 | 8 | 9
 *
 * Rows are indexed from 0 to 2, for example:
 * row 0 corresponds to 1, 2, 3
 * row 2 corresponds to 7, 8, 9
 *
 * Columns are also indexed from 0 to 2, for example:
 * column 0 corresponds to 1, 4, 7
 * column 2 corresponds to 3, 6, 9
 */
public interface Board {
    /**
     * @return positions that are not yet occupied by X or O
     */
    Collection<Position> getAvailablePositions();

    /**
     * @param position where to place the player token
     * @param player X or O
     * @return board that represents the updated grid
     */
    Board mark(Position position, Player player);

    /**
     * @return current board state (PLAY, DRAW, WIN_X, WIN_O)
     */
    BoardState getState();

}