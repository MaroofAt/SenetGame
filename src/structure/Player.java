package structure;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private boolean isWhite;
    private List<Piece> pieces;

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
        this.pieces = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pieces.add(new Piece(this));
        }
    }

    public boolean isWhite() { return isWhite; }
    public List<Piece> getPieces() { return pieces; }
}
