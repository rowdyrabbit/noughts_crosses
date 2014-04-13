package au.com.denisefernandez;


public interface Game {

    /**
     * Determines whether the game is still in progress
     *
     * @return true if the game is still running, false otherwise
     */
    public boolean isRunning();

    /**
     * Returns the name of the current player
     *
     * @return the name of the current player
     */
    public String getCurrentPlayerName();


    /**
     * Returns the current player object
     *
     * @return the current player
     */
    public Player getCurrentPlayer();

    /**
     * Executes the move
     *
     * @param move the coordinates of the move to make
     */
    public void execute(Move move);

    /**
     * Executes a computer move
     */
    public void executeComputerMove();

    /**
     * Returns a message with the game's current state, it can be one of the following
     * - The game was a draw
     * - The game was won by a player
     * - The game is still underway
     *
     *
     * @return a String representing a message format of the game's current state
     */
    public String getGameState();


}
