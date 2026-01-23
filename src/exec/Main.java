package exec;

import structure.Board;
import logic.State;
import structure.Cell;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.println("Initializing Senet board...\n");

//        State state = new State();
//        System.out.println(state);
//        state = state.move_piece(13, 12);
//        System.out.println(state);
//        state = state.move_piece(25, 2);
//        System.out.println(state);
//        state = state.move_piece(11, 3);
//        System.out.println(state);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Press Enter");
//        while (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            if (input.isEmpty()) {
//                break;
//            }
//        }
//        System.out.println(state);
        Scanner cin = new Scanner(System.in);
        System.out.println("Do you want to se the Algorithm ? (Y/N)  ");
        char c = cin.next().charAt(0);
        boolean ok ;
        if(c == 'y' || c== 'Y'){
            ok = true;
        }else {
            ok = false;
        }
        Game game = new Game();
        game.ok = ok;
        State s = new State();
//        Cell[] cells = s.deepcopy_cells();
//
//
//        cells[1].setPiece(null);
//        cells[3].setPiece(null);
//        cells[5].setPiece(null);
//        cells[7].setPiece(null);
//        cells[9].setPiece(null);
//        cells[11].setPiece(null);
//        s = new State(cells);
//        s.setBlackScore(6);
        game.Start_Game(s);
//        System.out.println("structure.Board initialized successfully!");
    }
}