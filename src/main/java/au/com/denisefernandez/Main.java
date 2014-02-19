package au.com.denisefernandez;


import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static final Game game = new Game();

    private static final String[] primaryMenuOptions = new String[]  {"[1] Start a new 2 player game", "[quit] Quit this program"};

    public static void main(String[] args) {
        out.println("*** NOUGHTS AND CROSSES ***");
        out.println("Welcome to Noughts and Crosses, please choose one of the following options:");
        printPrimaryMenuOptions();
        String menuSelection = in.nextLine();

        if (menuSelection.equals("1")) {
            startNewGame();
        } else if (menuSelection.trim().equalsIgnoreCase("quit")) {
            System.exit(0);
        } else {
            out.println("Your selection '" + menuSelection + " was not recognised. Please choose one of the following options:");
            printPrimaryMenuOptions();
        }
    }

    private static void startNewGame() {
        addPlayersToGame();
        out.println(game.getCurrentGameState());
        while(game.isRunning()) {
            executeNextMove();
            out.println(game.getCurrentGameState());
        }
        out.println(game.getResult());
    }

    private static void executeNextMove() {
        out.println(game.getCurrentPlayerName() + ", please enter your next move in the format x,y");
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

    private static void printPrimaryMenuOptions() {
        for (String option : primaryMenuOptions) {
            out.println(option);
        }
    }
}
