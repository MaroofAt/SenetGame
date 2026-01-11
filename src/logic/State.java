package logic;

import structure.Board;
import structure.Cell;
import structure.Piece;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class State extends Board{

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
        if(this.cells[piece_index].isEmpty()) return false;
        //TODO
    }

    // return the piece to start square or any empty cell before it (if it has piece on it)
    public void return_to_start_square(int piece_index){
        //TODO
    }

    // returns Arraylist of String that represents a possible action: 1,5 || 10,20
    public ArrayList<String> get_possible_actions(){
        //TODO
    }

    //
    public ArrayList<Integer> get_white_pieces(){
        //TODO
    }

    //
    public ArrayList<Integer> get_black_pieces(){
        //TODO
    }

    //
    public int where_can_piece_move(){
        //TODO
    }

    //
    public ArrayList<State> generate_next_states(){
        //TODO
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
