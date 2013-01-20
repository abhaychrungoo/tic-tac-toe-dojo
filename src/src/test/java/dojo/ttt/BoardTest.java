package dojo.ttt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.TreeSet;

import org.junit.Test;

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
            '-', 'X', 'O',
            'O', '-', 'X',
            'X', 'O', '-'
        );

        Position[] expectedPositions = new Position[3];
        for (int i = 0; i <= 2; i++) {
            expectedPositions[i] = new Position(i, i);
        }
//        for (Position  position: expectedPositions) System.out.println(position);
//        System.out.println("-----------------------");
//        for (Position  position: board.getAvailablePositions()) System.out.println(position);
//		Original test is incorrect. Order should not matter. Incorrect usage of AssertArrayEquals.
        assertEquals(new TreeSet(board.getAvailablePositions()), new TreeSet(Arrays.asList(expectedPositions)));
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
        board = board.mark(new Position(1, 0), Player.X);

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
                'X', 'O', 'O',
                '-', 'O', 'O'
            ).getState() == BoardState.WIN_O);

        assertTrue(BoardFactory.createBoard(
            'X', 'O', 'X',
            '-', 'O', '-',
            'X', 'O', '-'
        ).getState() == BoardState.WIN_O);
    }

}