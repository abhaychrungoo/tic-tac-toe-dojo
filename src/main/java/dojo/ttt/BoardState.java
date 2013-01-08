package dojo.ttt;

public enum BoardState {

    PLAY(false),
    DRAW(true),
    WIN_X(true),
    WIN_O(true);

    public final boolean terminal;

    private BoardState(boolean terminal) {
        this.terminal = terminal;
    }

}