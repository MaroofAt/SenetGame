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
    public State apply_action(String action){
        int piece_index = get_piece_index_from_string(action);
        int dest = get_destination_from_string(action);
        return move_piece(piece_index , dest-piece_index);
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
        return null;
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
        String piece_index = s.substring(0, s.indexOf(','));
        return Integer.parseInt(piece_index);
    }

    //
    public int get_destination_from_string(String s){
        String dist_index = s.substring(s.indexOf(',')+1);
        return Integer.parseInt(dist_index);
    }

}
