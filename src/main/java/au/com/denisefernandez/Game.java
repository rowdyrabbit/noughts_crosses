package au.com.denisefernandez;

public class Game {

    private Player[] players;
    private int currentPlayer;
    private Board board = new Board();
    private int numberOfMoves = 0;
    private Player winner = null;


    public void addPlayers(Player[] players) {
        this.players = players;
        currentPlayer = 0;  //The index of player 1 in the Players array
    }

    public boolean isRunning() {
        return numberOfMoves < Board.MAX_MOVES && winner == null;
    }

    public String getCurrentPlayerName() {
        return players[currentPlayer].getName();
    }

    public void execute(Move move) {
        final int x = move.getX();
        final int y = move.getY();
        if (board.isValidMove(x, y)) {
            numberOfMoves++;
            if (board.isWinningMove(getCurrentPlayer(), x, y)) {
                winner = getCurrentPlayer();
            } else {
                swapPlayers();
            }
        }
    }

    public String getResult() {
        if (numberOfMoves >= 9 && winner == null) {
            return "GAME OVER!! It was a draw!";
        } else if (winner != null) {
            return "GAME OVER!! "+winner.getName() + " (with the " + winner.getSymbol() + " symbol) was the winner this time!";
        } else {
            return "The game isn't over yet, keep playing!";
        }
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(board.toString());
        builder.append("Player 1: ");
        builder.append(players[0].toString());
        builder.append("\n");
        builder.append("Player 2: ");
        builder.append(players[1].toString());
        builder.append("\n\n");
        return builder.toString();
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == 0 ? 1 : 0;
    }

    private Player getCurrentPlayer() {
        return players[currentPlayer];
    }

}
