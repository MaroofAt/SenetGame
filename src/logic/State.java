package logic;

import structure.Board;
import structure.Cell;
import structure.Piece;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class State extends Board{
    State parent;
    public State(){super();}
    public State(Cell[] cells){super(cells);}

    // move
    public State move_piece(int fromIndex, int steps){
        if(!can_move_piece_to(fromIndex, steps)) return null;

        Cell[] new_cells = this.deepcopy_cells(); //deepcopy
        State new_state = new State(new_cells);


        int toIndex = fromIndex + steps;

        Cell fromCell = new_state.getCell(fromIndex);
        if (fromCell.isEmpty()) return null;

        Piece movingPiece = fromCell.getPiece();

        boolean isWhite = movingPiece.isWhite();

        if(toIndex>30){
            if(fromIndex < 25){
                return null;
            }
            // eat the piece
            fromCell.setPiece(null);
            if(isWhite){
                this.whiteScore++;
            }else{
                this.blackScore++;
            }
            return new_state;
        }

        Cell toCell = new_state.getCell(toIndex);


        // move the piece now 👇
        fromCell.setPiece(null);
        if(!toCell.isEmpty()) {
            if(toCell.getPiece().isWhite() == isWhite) return null;
            Piece other = toCell.getPiece();
            toCell.setPiece(movingPiece);
            fromCell.setPiece(other);
        }else{
            toCell.setPiece(movingPiece);
        }

        // making sure that the piece belongs to the same player
        if(!new_state.cells[25].isEmpty() && toIndex != 25 && (new_state.cells[25].getPiece().isWhite() == isWhite))
            new_state = return_to_start_square(new_state,25);

        if(!new_state.cells[27].isEmpty() && toIndex != 27 && (new_state.cells[27].getPiece().isWhite() == isWhite))
            new_state = return_to_start_square(new_state,27);

        if(!new_state.cells[28].isEmpty() && toIndex != 28 && (new_state.cells[28].getPiece().isWhite() == isWhite))
            new_state = return_to_start_square(new_state,28);

        return new_state;
    }

    // return bool whether is it a legal move or not
    public boolean can_move_piece_to(int piece_index, int steps){
        if(this.cells[piece_index].isEmpty()) return false;
        //TODO
    }

    // return the piece to start square or any empty cell before it (if it has piece on it)
    public State return_to_start_square(State state, int piece_index){
        // the StartSquare == 14

        int i = 14;
        while(i >= 0){
            if(state.cells[i].isEmpty()){
                return state.move_piece(piece_index, i);
            }
            i--;
        }

        i = 15;
        while(i <= 24){
            if(state.cells[i].isEmpty()){
                return state.move_piece(piece_index, i);
            }
            i++;
        }

        System.out.println("State: void return_to_start_square: piece can't come back to any cell because all of them have pieces !!!!!!");
        return null;
    }
    public State return_to_start_square(int piece_index){
        // the StartSquare == 14

        int i = 14;
        while(i >= 0){
            if(this.cells[i].isEmpty()){
                return this.move_piece(piece_index, i);
            }
            i--;
        }

        i = 15;
        while(i <= 24){
            if(this.cells[i].isEmpty()){
                return this.move_piece(piece_index, i);
            }
            i++;
        }

        System.out.println("State: State return_to_start_square: piece can't come back to any cell because all of them have pieces !!!!!!");
    }

    // returns Arraylist of String that represents a possible action: 1,5 || 10,20
    public ArrayList<String> get_possible_actions(){
        //TODO
    }

    //
    public ArrayList<Integer> get_white_pieces(){
        ArrayList<Integer> positions = new ArrayList<>();
        for(int i = 0 ; i < this.cells.length ; i++){
            if(!this.cells[i].isEmpty()){
                if(this.cells[i].getPiece().isWhite())
                    positions.add(i);
            }
        }
        return positions;
    }

    //
    public ArrayList<Integer> get_black_pieces(){
        ArrayList<Integer> positions = new ArrayList<>();
        for(int i = 0 ; i < this.cells.length ; i++){
            if(!this.cells[i].isEmpty()){
                if(this.cells[i].getPiece().isBlack())
                    positions.add(i);
            }
        }
        return positions;
    }

    //
//    public int where_can_piece_move(){
//        //TODO
//    }

    //
    public ArrayList<State> generate_next_states(){
        //TODO
    }

    //
    public int get_piece_index_from_string(String s){
        String piece_index = s.substring(0, s.indexOf(','));
        return Integer.parseInt(piece_index);
    }

    //
    public int get_destination_from_string(String s){
        String dist_index = s.substring(s.indexOf(',')+1);
        return Integer.parseInt(dist_index);
    }

}
