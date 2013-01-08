package dojo.ttt;

public enum BoardState {

    PLAY(false),
    DRAW(true),
    WIN_X(true),
    WIN_O(true);

    /**
     * Terminal state means that the game has stopped - either draw or one of the players won.
     */
    public final boolean terminal;

    private BoardState(boolean terminal) {
        this.terminal = terminal;
    }

}