package structure;

public class Cell {
    private int number;
    private Piece piece;
    private String special;

    public Cell(int number) {
        this.number = number;
        this.piece = null;
        this.special = switch (number) {
            case 15 -> "📍";
            case 26 -> "🪸"; // "📍📍📍";
            case 27 -> "🌊";
            case 28 -> "3️⃣"; // "🦢";
            case 29 -> "2️⃣"; // "🚶‍♂️🚶‍♂️";
            case 30 -> "⭕";
            default -> null;
        };
    }

    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }
    public boolean isEmpty() { return piece == null; }

    public String getDisplaySymbol() {
        if (piece != null) return piece.getSymbol(); // if there is piece on a special cell -> just the piece symbol will appear
        if (special != null) return special;
        return "🟫";
    }

    public int getNumber() { return number; }

    public Cell deepcopy(){
        Cell new_cell = new Cell(this.number);
        new_cell.piece = this.piece.deepcopy();
        return new_cell;
    }
}
