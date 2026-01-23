package exec;

import logic.State;
import logic.Expectiminimax;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    int count = 0;
//    State state_class;

    public void computer_play(State s){
        System.out.println("Computer Turn...");
        try {
            Thread.sleep(150);
        }catch (Exception e){
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);
        int steps = scan.nextInt();//s.throwSticks();
        System.out.println("Throwing sticks...");
        try {
            Thread.sleep(300);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("sticks = " + steps);
        State next = Expectiminimax.play(s, steps, 3, true);
//        if(s.getBlackScore() ==7){
//            System.out.println("========== COMPUTER WIN ! ==========");
//            return;
//        }
        if(next.is_terminal()){
            if(next.Action != null) System.out.println("Action : "+next.correct_action(next.Action));
            System.out.println(next);
            System.out.println("========== COMPUTER WIN ! ==========");
            return;
        }
//        System.out.println("s.get_possible_actions(false, steps) = "+ s.get_possible_actions(false, steps));
        if(s.get_possible_actions(false, steps).isEmpty()){
            // Skip Computer Turn
            System.out.println("Computer Has No Possible Actions to do -> skip turn...");
            try {
                Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
            State next_s = s.search_for_returns_and_return_to_start_square(s, false);
            Start_Game(next_s);
            return;
        }
        Start_Game(next);
        return;


    }
    public boolean is_Human_turn(){
        if(count %2 == 0){
            return true;
        }
        return false;
    }

    public void Start_Game(State state){
        if(state == null){
            state = new State();
        }
        if(state.Action != null) System.out.println("Action : "+state.correct_action(state.Action));
        System.out.println(state);
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Press Enter");
        System.out.println("Press Enter to Throw Stick");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
        }
//        System.out.print("Enter sticks= ");
        int steps = 1;//state.throwSticks(); // scanner.nextInt();
        System.out.println("sticks = " + steps);
        ArrayList <String> actions = state.get_possible_actions(is_Human_turn(), steps);
        if(actions.isEmpty()){
            // Skip Human Turn
            System.out.println("You Have No Possible Actions to do -> skip turn...");
            try {
                Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
            State next_s = state.search_for_returns_and_return_to_start_square(state, true);
            computer_play(next_s);
            return;
        }
        System.out.println("\nChoose Move :");

        for (int i=0 ; i< actions.size() ; i++){
            System.out.println("["+(i+1)+"]- "+ state.correct_action(actions.get(i)));
        }
        int choice;
        do {
            choice = scanner.nextInt() - 1;
        }while (choice > actions.size()-1 || choice < 0);

        State next = state.apply_action(
                actions.get(choice)
        );
        if(next.Action != null) System.out.println("Action : "+next.correct_action(next.Action));
        System.out.println(next);
//        if(state.getWhiteScore() == 7){
//            System.out.println("============== HUMAN WIN ==============");
//        }else{
//            computer_play(next);
//        }
        if(next.is_terminal()){
            System.out.println("============== HUMAN WIN ==============");
            return;
        }
        else{
            computer_play(next);
            return;
        }

    }

}
