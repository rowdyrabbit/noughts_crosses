package au.com.denisefernandez;


import java.util.Arrays;

public class Board {

    private static final int SIZE = 3;

    private BoardSymbol[][] board = new BoardSymbol[SIZE][SIZE];

    public Board() {
        fillBoardWithEmptySymbols();
    }

    public boolean isValidMove(int x, int y) {
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

    public boolean isWinningMove(Player player, int x, int y) {
        BoardSymbol symbol = player.getSymbol();
        board[x][y] = symbol;
        //check whether this player is a winner

        int horizontalCount = 0;
        int verticalCount = 0;
        int forwardDiagonalCount = 0;
        int backwardDiagonalCount = 0;

        for (int i = 0; i < SIZE; i++) {
            if (board[i][y] == symbol) {
               horizontalCount++;
            }
            if (board[x][i] == symbol) {
                verticalCount++;
            }
            if (board[i][i] == symbol) {
                forwardDiagonalCount++;
            }
            if (board[i][SIZE - 1 - i] == symbol) {
                backwardDiagonalCount++;
            }
        }

        if (horizontalCount == 3 || verticalCount == 3 || forwardDiagonalCount == 3 || backwardDiagonalCount == 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        final String rowSeparator = "  +-+-+-+\n";
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