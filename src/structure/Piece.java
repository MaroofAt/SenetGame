package structure;

public class Piece {
    private Player owner;
    private Integer position;

    public Piece(Player owner) {
        this.owner = owner;
        this.position = null;
    }

    public String getSymbol() {
        return owner.isWhite() ? "⚪" : "⚫"; // W = White, B = Black
    }

    public Player getOwner() { return owner; }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
}