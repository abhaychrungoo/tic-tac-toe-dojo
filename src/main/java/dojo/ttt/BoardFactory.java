package dojo.ttt;

import dojo.ttt.TicTacToeUtils.Callback;
import static dojo.ttt.TicTacToeUtils.forEach;

public final class BoardFactory {

    private BoardFactory() {}

    /**
     * @return empty board
     */
    public static Board createBoard() {
        return new ArrayBoard();
    }

    /**
     * @param tokens 'X', 'O' or '-' (empty)
     * @return new board based on the tokens
     */
    public static Board createBoard(final char... tokens) {
        final Player[][] grid = new Player[3][3];

        forEach(new Callback() {

            @Override
            public void execute(int row, int column) {
                int index = 3 * row + column;
                grid[row][column] = getPlayer(tokens[index]);
            }

            private Player getPlayer(char token) {
                switch (token) {
                    case 'X':
                        return Player.X;
                    case 'O':
                        return Player.O;
                    default:
                        return null;
                }
            }
        });

        return new ArrayBoard(grid);
    }
}