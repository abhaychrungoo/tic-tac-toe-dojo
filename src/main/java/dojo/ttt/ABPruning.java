package dojo.ttt;

public enum ABPruning implements Search {

    INSTANCE;

    @Override
    public Position getBestMove(Board board, Player player) {
        return new MaximizerSearch(player).getBestMove(board, player);
    }

    private class MaximizerSearch implements Search {

        private final Player maximizer;

        private MaximizerSearch(Player maximizer) {
            this.maximizer = maximizer;
        }

        private boolean isMaximizer(Player player) {
            return player == maximizer;
        }

        @Override
        public Position getBestMove(Board board, Player player) {

            int alpha = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;

            Position bestPosition = null;
            int bestScore = alpha;

            for (Position position : board.getAvailablePositions()) {
                int score = search(board.mark(position, player), player.opposite(), 0, alpha, beta);
                if (score > bestScore) {
                    bestScore = score;
                    bestPosition = position;
                }
            }

            return bestPosition;
        }

        private int search(Board board, Player player, int depth, int alpha, int beta) {
            final BoardState state = board.getState();

            if (state.terminal) {
                return getScore(state, depth);
            }

            boolean maximizerTurn = isMaximizer(player);
            int a = alpha;
            int b = beta;

            for (Position position : board.getAvailablePositions()) {
                int score = search(board.mark(position, player), player.opposite(), depth + 1, a, b);
                if (maximizerTurn && score > a) {
                    a = score;
                } else if (!maximizerTurn && score < b) {
                    b = score;
                } else if (a >= b) {
                    break;
                }
            }

            return maximizerTurn ? a : b;
        }

        private int getScore(BoardState state, int depth) {
            int score = 0;

            if (maximizer.isWinner(state)) {
                score = 100 - depth;
            } else if (maximizer.opposite().isWinner(state)) {
                score = -100 + depth;
            } else if (state == BoardState.DRAW) {
                score = 1;
            }

            return score;
        }
    }
}