package structure;

public class BoardPrinter {
    public static void printBoard(Board board) {
        System.out.println("\n========================================== SENET BOARD ============================================");

        System.out.print("Row 1 (1-10) :    ");
        for (int i = 1; i <= 10; i++) {
            if(i<10) System.out.print("0");
            System.out.print(i + ":" + board.getCell(i).getDisplaySymbol() + " \t");
        }
        System.out.println();

        System.out.print("Row 2 (20-11):    ");
        for (int i = 20; i >= 11; i--) {
            System.out.print(i + ":" + board.getCell(i).getDisplaySymbol() + " \t");
        }
        System.out.println();

        System.out.print("Row 3 (21-30):    ");
        for (int i = 21; i <= 30; i++) {
            System.out.print(i + ":" + board.getCell(i).getDisplaySymbol() + " \t");
        }
        System.out.println("\n===================================================================================================");
    }
}
