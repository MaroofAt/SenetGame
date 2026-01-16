package logic;

import structure.Board;
import structure.Cell;
import structure.Piece;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class State extends Board{
    State parent;
    // move
    public State move_piece(int fromIndex, int steps) {
        //TODO
        int toIndex= fromIndex+steps;
        if(toIndex>30) return null;
        Cell fromCell=this.getCell(fromIndex);
        Cell toCell=this.getCell(toIndex);
        if (fromCell.isEmpty()) return null;
        Piece movingPiece = fromCell.getPiece();
        fromCell.setPiece(null);
        if(!toCell.isEmpty()) {
            Piece other=toCell.getPiece();
            toCell.setPiece(movingPiece);
            fromCell.setPiece(other);
        }else{
            toCell.setPiece(movingPiece);
        }
    }

    // return bool whether is it a legal move or not
    public boolean can_move_piece_to(int piece_index, int steps){
//        if (this.cells[piece_index].isEmpty()) return false;
        if (piece_index+steps > 29) return false;
        else if (cells[piece_index+steps].getPiece().getSymbol() == cells[piece_index].getPiece().getSymbol()) return false;
        else if (piece_index < 25 && piece_index+steps > 25) return false;
        else if (piece_index == 27 && steps != 3) return false;
        else if (piece_index == 28 && steps != 2) return false;
        else if (cells[piece_index+steps].getPiece().getSymbol() != cells[piece_index].getPiece().getSymbol()) return true;
        else if (this.cells[piece_index+steps].isEmpty()) return true;
        return true;
        //TODO
    }

    // return the piece to start square or any empty cell before it (if it has piece on it)
    public void return_to_start_square(int piece_index){
        //TODO
    }

    // returns Arraylist of String that represents a possible action: 1,5 || 10,20
    public ArrayList<String> get_possible_actions(boolean IsWhite  , int StickResult){
        ArrayList <String> actions = new ArrayList();

        for (int i = 0; i < 30; i++) {
            if (this.cells[i].getPiece().getSymbolBool() == IsWhite && can_move_piece_to(i, StickResult)) {
                actions.add(i + "," + i + StickResult);
            } else if (this.cells[i].getPiece().getSymbolBool() == IsWhite && i + StickResult == 26) {

                return_to_start_square(i);
            } else if (this.cells[i].getPiece().getSymbolBool() == IsWhite && i == 27 && StickResult != 3) {

                return_to_start_square(i);
            } else if (this.cells[i].getPiece().getSymbolBool() == IsWhite && i == 28 && StickResult != 2) {

                return_to_start_square(i);
            }
        }
        return actions;
        //TODO
    }

//    public void Return_to_Start_Square(int piece_index , boolean IsWhite){
//        if(this.cells[14].isEmpty()){
//            Piece piece = new Piece(IsWhite);
//            this.cells[piece_index].setPiece(null);
//            this.cells[14].setPiece(piece);
//        }else {
//            for(int i=13 ; i>=0 ; i--){
//                if(this.cells[i].isEmpty()){
//                    Piece piece = new Piece(IsWhite);
//                    this.cells[piece_index].setPiece(null);
//                    this.cells[i].setPiece(piece);
//                    break;
//                }
//            }
//        }
//    }

    //
    public ArrayList<Integer> get_white_pieces(){
        //TODO
    }

    //
    public ArrayList<Integer> get_black_pieces(){
        //TODO
    }

    //
//    public int where_can_piece_move(){
//
//        //TODO
//    }

    //
    public ArrayList<State> generate_next_states(int StickThrow , boolean IsWhite , State parent){
        //TODO
        ArrayList <String> actions =  get_possible_actions(IsWhite , StickThrow);
        ArrayList <State> next_states = new ArrayList<>();
        for (String action:actions) {
            int from_index = get_piece_index_from_string(action);
            int steps = get_destination_from_string(action);
            next_states.add(move_piece(from_index , steps).parent = parent);
        }

        return next_states;
    }

    //
    public int get_piece_index_from_string(String s){
        //TODO
    }

    //
    public int get_destination_from_string(String s){
        //TODO
    }

}
