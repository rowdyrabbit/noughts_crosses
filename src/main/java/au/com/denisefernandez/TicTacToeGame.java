package au.com.denisefernandez;

public class TicTacToeGame implements Game {

    private Player[] players;
    private int currentPlayer;
    private Board board;
    private Player winner = null;

    public TicTacToeGame(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        //The index of player 1 in the Players array
        currentPlayer = 0;
    }

    /**
     * @see Game#isRunning() ()
     */
    @Override
    public boolean isRunning() {
        return !board.isFull() && winner == null;
    }

    /**
     * @see Game#getCurrentPlayerName()
     */
    @Override
    public String getCurrentPlayerName() {
        return players[currentPlayer].getName();
    }

    /**
     *
     * @return Game#getCurrentPlayer
     */
    @Override
    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }


    /**
     * @see Game#execute(Move)
     */
    @Override
    public void execute(Move move) {
        final int x = move.getX();
        final int y = move.getY();
        if (board.isValidMove(move)) {

            if (board.makeMove(getCurrentPlayer().getSymbol(), move)) {
                winner = getCurrentPlayer();
            } else {
                swapPlayers();
            }
        }
    }

    /**
     * @see Game#executeComputerMove()
     */
    @Override
    public void executeComputerMove() {
        Move computerMove = board.getComputerMove(TicTacToeBoard.BoardSymbol.X);
        execute(computerMove);
    }

    /**
     * @see Game#getGameState() ()
     */
    @Override
    public String getGameState() {
        if (board.isFull() && winner == null) {
            return "GAME OVER!! It was a draw!";
        } else if (winner != null) {
            return "GAME OVER!! " + winner.getName() + " (with the " + winner.getSymbol() + " symbol) was the winner this time!";
        } else {
            return "The game isn't over yet, keep playing!";
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(board.toString());
        builder.append("\n");
        builder.append("Player 1");
        builder.append(players[0].toString());
        builder.append("\n");
        builder.append("Player 2");
        builder.append(players[1].toString());
        builder.append("\n\n");
        return builder.toString();
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == 0 ? 1 : 0;
    }





}
