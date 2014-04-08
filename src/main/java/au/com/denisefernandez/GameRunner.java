package au.com.denisefernandez;


import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GameRunner {

    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;


    public static void main(String[] args) {
        new GameRunner().run(new TicTacToeGame(new TicTacToeBoard(), getPlayersForGame()));
    }

    private void run(Game game) {
        startGameLoop(game);
    }

    private void startGameLoop(Game game) {
        out.println("*** TIC TAC TOE ***");
        out.println(game);
        while(game.isRunning()) {
            Player currentPlayer = game.getCurrentPlayer();

            if (currentPlayer.getPlayerType() == PlayerType.HUMAN) {
                out.println(currentPlayer.getName() + ", please enter your next move in the format x,y");
                getNextMove(game);
            } else {
                out.println("It is the computer's turn to play now. Please wait.");
                game.executeComputerMove();
                out.println(game);
            }

        }
        out.println(game.getGameState());
    }


    private void getNextMove(Game game) {
        try {
            Move move = parseMove();
            game.execute(move);
            out.println(game); //only print game state if move was successful
        } catch(TicTacToeBoard.InvalidMoveException invalidMove) {
            out.println(invalidMove.getMessage());
        } catch(TicTacToeBoard.PositionAlreadyTakenException positionTaken) {
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

    private static Player[] getPlayersForGame() {
        Player[] players = new Player[2];

        out.println("Would you like to play a 1 player or 2 player game? (Please enter [1] or [2] to make a selection");
        int selection = in.nextInt();
        if (selection == 1) {
            out.println("Please enter the name of Player 1 [O]: ");
            String player1Name = in.next();
            players[0] = new Player(player1Name, TicTacToeBoard.BoardSymbol.O.toString(), PlayerType.HUMAN);
            players[1] = new Player("Computer", TicTacToeBoard.BoardSymbol.X.toString(), PlayerType.COMPUTER);
        } else {
            out.println("Please enter the name of Player 1 [O]: ");
            String player1Name = in.next();
            out.println("Please enter the name of Player 2 [X]: ");
            String player2Name = in.next();
            players[0] = new Player(player1Name, TicTacToeBoard.BoardSymbol.O.toString(), PlayerType.HUMAN);
            players[1] = new Player(player2Name, TicTacToeBoard.BoardSymbol.X.toString(), PlayerType.HUMAN);
        }



        return players;
    }
}