package au.com.denisefernandez;


import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TicTacToeBoardTest {

    private Board board;

    @Before
    public void setup() {
        board = new TicTacToeBoard();
    }

    @Test
    public void boardShouldNotBeFullWhenNewlyCreated() {
        assertFalse(board.isFull());
    }

    @Test
    public void aMoveWithCoordinatesWithinRangeShouldBeValid() {
        assertThat(board.isValidMove(new Move(0, 0)), is(true));
    }

    @Test(expected=TicTacToeBoard.PositionAlreadyTakenException.class)
    public void aMoveWithCoordinatesThatAreAlreadyOccupiedShouldNotBeValid() {
        board.makeMove("X", new Move(0, 0));
        assertThat(board.isValidMove(new Move(0, 0)), is(false));
    }

    @Test
    public void boardShouldBeFullOnceAllPositionsTaken() {
        for (int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                board.makeMove("X", new Move(i, j));
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    public void shouldReturnTrueWhenVerticalWinOccurs() {
        for (int i=0; i<3; i++) {
            board = new TicTacToeBoard();
            board.makeMove("X", new Move(0, 0));
            board.makeMove("X", new Move(1, 1));
            assertTrue(board.makeMove("X", new Move(2, 2)));
        }
    }

    @Test
    public void shouldReturnTrueWhenHorizontalWinOccurs() {
        board = new TicTacToeBoard();
        board.makeMove("X", new Move(0, 0));
        board.makeMove("X", new Move(1, 1));
        assertTrue(board.makeMove("X", new Move(2, 2)));
    }

    @Test
    public void shouldReturnTrueWhenDiagonalWinOccurs() {
        board = new TicTacToeBoard();
        board.makeMove("X", new Move(0, 0));
        board.makeMove("X", new Move(1, 1));
        assertTrue(board.makeMove("X", new Move(2, 2)));
    }

    @Test
    public void shouldReturnTrueWhenBackwardsDiagonalWinOccurs() {
        board = new TicTacToeBoard();
        board.makeMove("X", new Move(0, 2));
        board.makeMove("X", new Move(1, 1));
        assertTrue(board.makeMove("X", new Move(2, 0)));
    }
}
