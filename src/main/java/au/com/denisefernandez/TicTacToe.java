package au.com.denisefernandez;


import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TicTacToe {

    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;

    public TicTacToe() {
        final Game game = new Game();
        out.println("*** TIC TAC TOE ***");
        game.addPlayers(getPlayersForGame());
        startGameLoop(game);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    private void startGameLoop(Game game) {
        out.println(game);
        while(game.isRunning()) {
            out.println(game.getCurrentPlayerName() + ", please enter your next move in the format x,y");
            getNextMove(game);
            out.println(game);
        }
        out.println(game.getResult());
    }

    private void getNextMove(Game game) {
        Move move = parseMove();
        try {
            game.execute(move);
        } catch(Board.InvalidMoveException invalidMove) {
            out.println(invalidMove.getMessage());
        } catch(Board.PositionAlreadyTakenException positionTaken) {
            out.println(positionTaken.getMessage());
        }
    }

    private Move parseMove() {
        String coordinate = in.next().trim();
        StringTokenizer st = new StringTokenizer(coordinate, ",");

        if (st.countTokens() != 2) {
            out.println("Invalid input, please enter your move in the format x,y");
            return parseMove();
        } else {
           return new Move (Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
        }
    }

    private Player[] getPlayersForGame() {
        Player[] players = new Player[2];
        out.println("Please enter the name of Player 1 [O]: ");
        String player1Name = in.nextLine();
        out.println("Please enter the name of Player 2 [X]: ");
        String player2Name = in.nextLine();
        players[0] = new Player(player1Name, Board.BoardSymbol.O);
        players[1] = new Player(player2Name, Board.BoardSymbol.X);
        return players;
    }
}