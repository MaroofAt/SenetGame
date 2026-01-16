package exec;

import structure.Board;
import logic.State;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Initializing Senet board...\n");

//        Board board = new Board();
//        System.out.println(board);
        State state = new State();
        System.out.println(state);
        state = state.move_piece(13, 12);
        System.out.println(state);
        state = state.move_piece(25, 2);
        System.out.println(state);
        state = state.move_piece(11, 3);
        System.out.println(state);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
        }
        System.out.println(state);

//        System.out.println("structure.Board initialized successfully!");
    }
}