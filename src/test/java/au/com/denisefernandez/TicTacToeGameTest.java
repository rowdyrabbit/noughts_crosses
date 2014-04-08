package au.com.denisefernandez;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TicTacToeGameTest {

    private Player[] players = new Player[2];
    private Game ticTacToe;
    @Mock
    private TicTacToeBoard board;


    @Before
    public void setup() {
        players[0] = new Player("Jack", TicTacToeBoard.BoardSymbol.O.toString(), PlayerType.HUMAN);
        players[1] = new Player("Jill", TicTacToeBoard.BoardSymbol.X.toString(), PlayerType.HUMAN);
        ticTacToe = new TicTacToeGame(board, players);
    }

    @Test
    public void currentPlayerNameShouldBeFirstPlayerInList() {
        assertThat(ticTacToe.getCurrentPlayerName(), is("Jack"));
    }

    @Test
    public void currentPlayerNameShouldBeSecondPlayerInListAfterFirstPlayerHasHadTheirTurn() {
        when(board.isValidMove(any(Move.class))).thenReturn(true);
        when(board.isWinningMove(anyString(), any(Move.class))).thenReturn(false);

        ticTacToe.execute(new Move(0, 0));

        assertThat(ticTacToe.getCurrentPlayerName(), is("Jill"));
    }

    @Test
    public void gameShouldNotBeRunningWhenBoardIsFull() {
        when(board.isFull()).thenReturn(true);
        assertThat(ticTacToe.isRunning(), is(false));
    }

    @Test
    public void gameShouldBeRunningWhenBoardIsNotFullAndNoWinnerYet() {
        when(board.isFull()).thenReturn(false);

        assertThat(ticTacToe.isRunning(), is(true));
    }

    @Test
    public void executingAMoveShouldCheckIfMoveIsValid() {
        Move move = new Move(0, 0);
        ticTacToe.execute(move);

        verify(board).isValidMove(move);
    }

    @Test
    public void executingAMoveShouldCheckIfMoveIsAWinningMove() {
        Move move = new Move(0, 0);
        when(board.isValidMove(any(Move.class))).thenReturn(true);

        ticTacToe.execute(move);

        verify(board).isValidMove(eq(move));
        verify(board).isWinningMove(eq(players[0].getSymbol().toString()), eq(move));
    }
}
