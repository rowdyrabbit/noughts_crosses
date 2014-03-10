package au.com.denisefernandez;

public class Player {
    private Board.BoardSymbol symbol;
    private String name;

    public Player(String name, Board.BoardSymbol allocatedSymbol) {
        this.name = name;
        this.symbol = allocatedSymbol;
    }

    public Board.BoardSymbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return " (" + this.getSymbol() + ") : "  + this.getName();
    }
}