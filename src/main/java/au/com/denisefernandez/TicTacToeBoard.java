package au.com.denisefernandez;


import java.util.Arrays;

public class TicTacToeBoard implements Board {

    private static final int BOARD_SIZE = 3;  //the length and width of the board
    private static final int MAX_MOVES = BOARD_SIZE * BOARD_SIZE;

    private BoardSymbol[][] board = new BoardSymbol[BOARD_SIZE][BOARD_SIZE]; //2D array representing a tic tac toe board
    private int numberOfMoves = 0;


    public TicTacToeBoard() {
        fillBoardWithEmptySymbols();  //initializes the board state
    }


    /**
     * @see Board#isValidMove(Move)
     *
     * @throws PositionAlreadyTakenException if position is already taken
     * @throws InvalidMoveException if position is outside of the board's range
     */
    @Override
    public boolean isValidMove(Move move) {
        int x = move.getX();
        int y = move.getY();
        if ((0 <= x && x < 3) && (0 <= y && y < 3)) {
            if (isSquareEmpty(x, y)) {
                return true;
            } else {
                throw new PositionAlreadyTakenException(x, y);
            }
        } else {
            throw new InvalidMoveException(x, y);
        }
    }

    /**
     * @see Board#isWinningMove(String, Move)
     *
     */
    @Override
    public boolean isWinningMove(String symbolString, Move move) {
        BoardSymbol symbol = BoardSymbol.valueOf(symbolString);
        int x = move.getX();
        int y = move.getY();
        //put the player's symbol at this move on the board
        board[x][y] = symbol;
        numberOfMoves++;

        int horizontalCount = 0;
        int verticalCount = 0;
        int forwardDiagonalCount = 0;
        int backwardDiagonalCount = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][y] == symbol) {
               horizontalCount++;
            }
            if (board[x][i] == symbol) {
                verticalCount++;
            }
            if (board[i][i] == symbol) {
                forwardDiagonalCount++;
            }
            if (board[i][BOARD_SIZE - 1 - i] == symbol) {
                backwardDiagonalCount++;
            }
        }
        //if there are 3 of the same symbols in any of the possible winning directions, then we have a winner
        if (horizontalCount == BOARD_SIZE || verticalCount == BOARD_SIZE || forwardDiagonalCount == BOARD_SIZE || backwardDiagonalCount == BOARD_SIZE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @see Board#isFull()
     *
     */
    @Override
    public boolean isFull() {
        return numberOfMoves >= TicTacToeBoard.MAX_MOVES;
    }

    @Override
    public String toString() {
        final String rowSeparator = "   - - - \n";
        StringBuilder builder = new StringBuilder();
        builder.append(rowSeparator);
        for (int col = board.length-1; col >= 0; col-- ) {
            builder.append(col);
            builder.append(" ");
            for (int row = 0; row < board[0].length; row++) {
                builder.append("|" + board[row][col]);
            }
            builder.append("|\n" + rowSeparator);
        }
        builder.append("   0 1 2 \n");
        return builder.toString();
    }


    private void fillBoardWithEmptySymbols() {
        for (BoardSymbol[] row : board) {
            Arrays.fill(row, BoardSymbol.Empty);
        }
    }

    private boolean isSquareEmpty(int x, int y) {
        return BoardSymbol.Empty == board[x][y];
    }

    public enum BoardSymbol {
        X, O, Empty {
            @Override
            public String toString() {
                return " ";
            }
        }
    }

    public class InvalidMoveException extends RuntimeException {
        public InvalidMoveException(int x, int y) {
            super("The coordinates " + x + "," + y + " are outside the board's range of coordinates.");
        }
    }

    public class PositionAlreadyTakenException extends RuntimeException {
        public PositionAlreadyTakenException(int x, int y) {
            super("The coordinates " + x + "," + y + " are already occupied with " + board[x][y]);
        }
    }
}
