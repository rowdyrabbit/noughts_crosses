package au.com.denisefernandez;

public class Player {
    private String symbol;
    private String name;
    private PlayerType playerType;

    public Player(String name, String allocatedSymbol, PlayerType playerType) {
        this.name = name;
        this.symbol = allocatedSymbol;
        this.playerType = playerType;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public String toString() {
        return " (" + this.getSymbol() + ") : "  + this.getName();
    }
}

enum PlayerType {
    HUMAN, COMPUTER
}