package au.com.denisefernandez;

public class Game {

    private Player[] players = new Player[2];
    private int currentPlayerIndex;
    private Board board = new Board();
    private int numberOfMoves = 0;
    private Player winner = null;


    public void addPlayers(String player1, String player2) {
        this.players[0] = new Player(player1, BoardSymbol.X);
        this.players[1] = new Player(player2, BoardSymbol.O);
        currentPlayerIndex = 0;
    }

    public boolean isRunning() {
        return numberOfMoves < 9 && winner == null;
    }


    public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }

    public void execute(Move move) {
        final int x = move.getX();
        final int y = move.getY();
        if (board.isValidMove(x, y)) {
            if (board.isWinningMove(getCurrentPlayer(), x, y)) {
                winner = getCurrentPlayer();
            } else {
                rotatePlayers();
            }

        }
    }

    public String getCurrentGameState() {
        StringBuilder builder = new StringBuilder();
        builder.append(board.toString());
        builder.append("Player 1 (" + players[0].getSymbol() + ") : "  + players[0].getName() + "\n");
        builder.append("Player 2 (" + players[1].getSymbol() + ") : "  + players[1].getName() + "\n\n");
        return builder.toString();
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

    private void rotatePlayers() {
        currentPlayerIndex = currentPlayerIndex == 0 ? 1 : 0;
    }

    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

}
