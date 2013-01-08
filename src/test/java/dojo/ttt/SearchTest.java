package dojo.ttt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchTest {

    final Search search = SearchFactory.createSearch();

    @Test
    public void avoidImmediateLoss() {
        // X can win by row=0, column=1
        Board board = BoardFactory.createBoard(
            'X', '-', 'X',
            '-', '-', '-',
            '-', '-', 'O'
        );
        assertEquals(new Position(0, 1), search.getBestMove(board, Player.O));

        // O can win by row=2, column=1
        board = BoardFactory.createBoard(
            'X', 'O', 'X',
            '-', 'O', 'X',
            '-', '-', 'O'
        );
        assertEquals(new Position(2, 1), search.getBestMove(board, Player.X));

        // O can win by row=1, column=2
        board = BoardFactory.createBoard(
            '-', '-', 'X',
            'O', 'O', '-',
            'X', '-', '-'
        );
        assertEquals(new Position(1, 2), search.getBestMove(board, Player.X));
    }

    @Test
    public void pickImmediateWin() {
        // X can win by row=1, column=1
        Board board = BoardFactory.createBoard(
            '-', '-', 'X',
            '-', '-', 'O',
            'X', '-', 'O'
        );
        assertEquals(new Position(1, 1), search.getBestMove(board, Player.X));

        // O can win by row=1, column=0
        board = BoardFactory.createBoard(
            'X', '-', 'X',
            '-', 'O', 'O',
            '-', '-', 'X'
        );
        assertEquals(new Position(1, 0), search.getBestMove(board, Player.O));

        // X can win by row=2, column=0
        board = BoardFactory.createBoard(
            'O', 'O', 'X',
            'X', 'O', 'O',
            '-', 'X', 'X'
        );
        assertEquals(new Position(2, 0), search.getBestMove(board, Player.X));

        // O can win by row=1, column=2
        board = BoardFactory.createBoard(
            'X', '-', 'O',
            '-', 'X', '-',
            '-', 'X', 'O'
        );
        assertEquals(new Position(1, 2), search.getBestMove(board, Player.O));
    }

    @Test
    public void winByForking() {
        // X can win by creating a fork at row=2, column=0
        Board board = BoardFactory.createBoard(
            'X', 'O', 'X',
            '-', '-', 'O',
            '-', 'X', 'O'
        );
        assertEquals(new Position(2, 0), search.getBestMove(board, Player.X));

        // O can win by creating a fork at row=1, column=0
        board = BoardFactory.createBoard(
            '-', '-', 'X',
            '-', '-', 'O',
            'O', 'X', 'X'
        );
        assertEquals(new Position(1, 0), search.getBestMove(board, Player.O));
    }

}