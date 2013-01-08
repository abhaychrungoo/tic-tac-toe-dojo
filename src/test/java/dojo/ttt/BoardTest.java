package dojo.ttt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void emptyBoard() {
        assertEquals(9, BoardFactory.createBoard().getAvailablePositions().size());
        assertEquals(9, BoardFactory.createBoard(
            '-', '-', '-',
            '-', '-', '-',
            '-', '-', '-'
        ).getAvailablePositions().size());
    }

    @Test
    public void fullBoard() {
        Board board = BoardFactory.createBoard(
            'X', 'O', 'X',
            'X', 'X', 'O',
            'O', 'O', 'X'
        );
        assertTrue(board.getAvailablePositions().isEmpty());
    }

    @Test
    public void partiallyFullBoard() {
        final Board board = BoardFactory.createBoard(
            '-', 'X', '0',
            'O', '-', 'X',
            'X', 'O', '-'
        );

        List<Position> expectedPositions = new ArrayList<Position>(3);
        for (int i = 0; i <= 2; i++) {
            expectedPositions.add(new Position(i, i));
        }

        assertEquals(board.getAvailablePositions(), expectedPositions);
    }

    @Test
    public void mark() {
        Board board = BoardFactory.createBoard();

        board = board.mark(new Position(0, 0), Player.X);
        board = board.mark(new Position(2, 2), Player.O);
        board = board.mark(new Position(0, 2), Player.X);
        board = board.mark(new Position(0, 1), Player.O);
        board = board.mark(new Position(2, 0), Player.X);
        board = board.mark(new Position(1, 1), Player.O);
        board = board.mark(new Position(1, 0), Player.O);

        Board expectedBoard = BoardFactory.createBoard(
            'X', 'O', 'X',
            'X', 'O', '-',
            'X', '-', 'O'
        );

        assertEquals(board, expectedBoard);
    }

    @Test
    public void statePlay() {
        assertTrue(BoardFactory.createBoard().getState() == BoardState.PLAY);
        assertTrue(BoardFactory.createBoard(
            'X', 'O', '-',
            'O', 'O', 'X',
            'X', '-', '-'
        ).getState() == BoardState.PLAY);
    }

    @Test
    public void stateDraw() {
        assertTrue(BoardFactory.createBoard(
            'X', 'O', 'X',
            'O', 'O', 'X',
            'X', 'X', 'O'
        ).getState() == BoardState.DRAW);

        assertTrue(BoardFactory.createBoard(
            'X', 'X', 'O',
            'O', 'X', 'X',
            'X', 'O', 'O'
        ).getState() == BoardState.DRAW);
    }

    @Test
    public void stateWinX() {
        assertTrue(BoardFactory.createBoard(
            'X', 'O', 'X',
            '-', 'X', 'O',
            'O', '-', 'X'
        ).getState() == BoardState.WIN_X);

        assertTrue(BoardFactory.createBoard(
            'O', 'X', 'X',
            '-', 'X', 'O',
            'X', 'O', '-'
        ).getState() == BoardState.WIN_X);
    }

    @Test
    public void stateWinO() {
        assertTrue(BoardFactory.createBoard(
            'X', '-', 'X',
            'O', 'O', 'O',
            'X', '-', '-'
        ).getState() == BoardState.WIN_O);

        assertTrue(BoardFactory.createBoard(
            'X', 'O', 'X',
            '-', 'O', '-',
            'X', 'O', '-'
        ).getState() == BoardState.WIN_O);
    }

}