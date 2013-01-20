package dojo.ttt;

public enum Player {

    X {
        @Override
        public Player opposite() {
            return O;
        }
        

        @Override
        public boolean isWinner(BoardState state) {
            return state == BoardState.WIN_X;
        }
    },

    O {
        @Override
        public Player opposite() {
            return X;
        }

        @Override
        public boolean isWinner(BoardState state) {
            return state == BoardState.WIN_O;
        }
    };

    public abstract Player opposite();

    public abstract boolean isWinner(BoardState state);

}