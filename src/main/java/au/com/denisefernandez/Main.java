package au.com.denisefernandez;


import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static final Game game = new Game();

    public static void main(String[] args) {
        out.println("*** NOUGHTS AND CROSSES ***");
        addPlayersToGame();
        out.println(game.getCurrentGameState());
        while(game.isRunning()) {
            out.println(game.getCurrentPlayerName() + ", please enter your next move in the format x,y");
            executeNextMove();
            out.println(game.getCurrentGameState());
        }
        out.println(game.getResult());
    }

    private static void executeNextMove() {
        Move move = parseMove();
        try {
            game.execute(move);
        } catch(Board.InvalidMoveException im) {
            out.println(im.getMessage());
        } catch(Board.PositionAlreadyTakenException pt) {
            out.println(pt.getMessage());
        }
    }

    private static Move parseMove() {
        String coordinate = in.next().trim();
        StringTokenizer st = new StringTokenizer(coordinate, ",");

        if (st.countTokens() != 2) {
            out.println("Invalid input, please enter your move in the format x,y");
            return parseMove();
        } else {
           return new Move (Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
        }
    }

    private static void addPlayersToGame() {
        out.println("Please enter the name of Player 1 [O]: ");
        String player1Name = in.nextLine();
        out.println("Please enter the name of Player 2 [X]: ");
        String player2Name = in.nextLine();
        game.addPlayers(player1Name, player2Name);
    }
}