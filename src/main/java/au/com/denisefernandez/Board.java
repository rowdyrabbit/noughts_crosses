package au.com.denisefernandez;

public interface Board {

    /**
     * Determines whether the supplied coordinates are valid.
     * A move is considered valid if the coordinates are not currently occupied
     * and the coordinates are a valid position on the board.
     *
     * @param move the x,y coordinates of a position on the board
     * @return true if the move is allowed, false otherwise
     */
    public boolean isValidMove(Move move);

    /**
     * This method executes a move on the board and returns true if the move was a winning one.
     *
     * @param symbolString the symbol to place at the supplied coordinates
     * @param move the x,y coordinates of a position on the board
     * @return true if the supplied move won the game, otherwise false.
     */
    public boolean isWinningMove(String symbolString, Move move);

    /**
     * Determines whether the board is full.
     *
     * @return true if all positions on the board are occupied, false otherwise
     */
    public boolean isFull();

}
