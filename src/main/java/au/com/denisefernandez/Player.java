package au.com.denisefernandez;

public class Player {
    private BoardSymbol symbol;
    private String name;

    public Player(String name, BoardSymbol allocatedSymbol) {
        this.name = name;
        this.symbol = allocatedSymbol;
    }

    public BoardSymbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}