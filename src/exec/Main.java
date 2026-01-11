package exec;

import structure.Board;
import structure.BoardPrinter;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Initializing Senet board...\n");

        Board board = new Board();
        BoardPrinter.printBoard(board);

//        System.out.println("structure.Board initialized successfully!");
    }
}