package structure;

public class Piece {
    private boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public String getSymbol() {
        return isWhite ? "⚪" : "⚫";
    }

    public boolean isWhite(){
        return isWhite;
    }
    public boolean isBlack(){
        return !isWhite;
    }

}