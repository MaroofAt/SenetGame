package structure;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Cell[] cells = new Cell[31];

    public Board() {
        for (int i = 1; i <= 30; i++) {
            cells[i] = new Cell(i);
        }
        setupInitialPosition();
    }

    private void setupInitialPosition() {
        Player white = new Player("White", true);
        Player black = new Player("Black", false);

        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pieces.add(white.getPieces().get(i));
            pieces.add(black.getPieces().get(i));
        }

        for (int i = 0; i < 14; i++) {
            Piece p = pieces.get(i);
            cells[i + 1].setPiece(p);
            p.setPosition(i + 1);
        }
    }

    public Cell getCell(int number) {
        return cells[number];
    }

    public Cell[] getCells(){
        Cell[] new_cells = new Cell[31];
        for(int i = 1 ; i < 31 ; i++){
            new_cells[i] = this.cells[i];
        }
        return new_cells;
    }
}
