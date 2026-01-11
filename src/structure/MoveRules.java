package structure;

public class MoveRules {
    public static void movePiece(Board board, int fromIndex, int steps)
    {
     int toIndex= fromIndex+steps;
     if(toIndex>30) return;
     Cell fromCell=board.getCell(fromIndex);
        Cell toCell=board.getCell(toIndex);
        if (fromCell.isEmpty()) return;
        Piece movingPiece= fromCell.getPiece();
        fromCell.setPiece(null);
        if(!toCell.isEmpty())
        {
            Piece other=toCell.getPiece();
            toCell.setPiece(movingPiece);
            fromCell.setPiece(other);
        }
        else {
            toCell.setPiece(movingPiece);
        }
    }
}
