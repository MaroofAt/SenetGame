package structure;

import java.util.*;

public class Board {
    protected Cell[] cells = new Cell[30];
    protected int whiteScore;
    protected int blackScore;

    public Board() {
        for (int i = 0; i < 30; i++) {
            cells[i] = new Cell(i);
        }
        setupInitialPosition();
        this.whiteScore = 0;
        this.blackScore = 0;
    }
    public Board(Cell[] cells) {
        this.cells = cells;
        this.whiteScore = 0;
        this.blackScore = 0;
    }

    private void setupInitialPosition() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pieces.add(new Piece(true));
            pieces.add(new Piece(false));
        }

        for (int i = 0; i < 14; i++) {
            Piece p = pieces.get(i);
            cells[i].setPiece(p);
        }
    }

    public Cell getCell(int number) {
        return cells[number];
    }

    public Cell[] deepcopy_cells(){
        Cell[] new_cells = new Cell[31];
        for(int i = 1 ; i < 31 ; i++){
            new_cells[i] = this.cells[i].deepcopy();
        }
        return new_cells;
    }

    public int throwSticks(){
        Random rand = new Random();
        int sum=0;
        for(int i=0 ; i<4;i++) {
            Stick stick = new Stick();
            sum += stick.getValue();
        }
        return sum == 0 ? 5 : sum;
    }
    public static Map<Integer, Double> getSticksProbabilities(){
        Map<Integer, Double> probs = new HashMap<>();
        probs.put(1, 4.0 / 16); // c(4,1)
        probs.put(2, 6.0 / 16); // c(4,2)
        probs.put(3, 4.0 / 16); // c(4,3)
        probs.put(4, 1.0 / 16); // c(4,4)
        probs.put(5, 1.0 / 16); // كلها فاتحة
        return probs;
    }


    @Override
    public String toString() {
        String result = "\n========================================== SENET BOARD ============================================" + "\n";

        result += "Row 1 (1-10) :    ";
        for (int i = 0; i < 10; i++) {
            if(i<9) result += "0";
            result += (Integer.toString(i+1) + ":" + this.getCell(i).getDisplaySymbol() + " \t");
        }
        result += "\n";

        result += "Row 2 (20-11):    ";
        for (int i = 19; i >= 10; i--) {
            result += (Integer.toString(i+1) + ":" + this.getCell(i).getDisplaySymbol() + " \t");
        }
        result += "\n";

        result += "Row 3 (21-30):    ";
        for (int i = 20; i < 30; i++) {
            result += (Integer.toString(i+1) + ":" + this.getCell(i).getDisplaySymbol() + " \t");
        }
        result += "\n===================================================================================================" + "\n";
        return result;
    }
}
