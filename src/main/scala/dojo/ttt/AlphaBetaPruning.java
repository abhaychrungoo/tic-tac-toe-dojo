package dojo.ttt;

import scala.collection.Iterator;

public class AlphaBetaPruning {

    /*@Override
    public Position bestMove(GameBoard board, PlayerToken player) {
        int bestScore = Integer.MIN_VALUE;
        Position bestPosition = null;

        final Evaluator evaluator = new Evaluator(player);
        final Search search = new Search(evaluator);

        final Iterator<Position> iterator = board.availablePositions().iterator();
        while (iterator.hasNext()) {
            final Position position = iterator.next();
            final GameBoard newBoard = board.mark(position, player);

            if (newBoard.state().isTerminal()) {
                int score = evaluator.evaluate(board.state(), 0);
                if (score > bestScore) {
                    bestScore = score;
                    bestPosition = position;
                }
            }

            final int score = search.minimax(newBoard, player.opposite(), 1, false, bestScore, Integer.MAX_VALUE);
            if (score > bestScore) {
                bestScore = score;
                bestPosition = position;
            }
        }

        return bestPosition;
    }



    private class Search {

        private final Evaluator evaluator;

        private Search(final Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        int minimax(GameBoard board, PlayerToken player, int depth, boolean alphaTurn, int alpha, int beta) {
            if (board.state().isTerminal()) {
                return evaluator.evaluate(board.state(), depth);
            }

            int a = alpha;
            int b = beta;

            final Iterator<Position> iterator = board.availablePositions().iterator();
            while (iterator.hasNext()) {
                final Position position = iterator.next();
                final GameBoard newBoard = board.mark(position, player);
                int score = minimax(newBoard, player.opposite(), depth + 1, !alphaTurn, a, b);
                if (alphaTurn && score > a) a = score;
                else if (!alphaTurn && score < b) b = score;
                if (a >= b) break;
            }

            return alphaTurn ? a : b;
        }
    }

    private class Evaluator {

        private final PlayerToken player;

        private Evaluator(PlayerToken player) {
            this.player = player;
        }

        int evaluate(BoardState state, int depth) {
            if (player.isWinner(state))
                return 100 - depth;
            else if (player.opposite().isWinner(state))
                return -100 + depth;
            else
                return 0;
        }
    }*/
}