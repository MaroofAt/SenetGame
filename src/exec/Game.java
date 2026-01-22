package exec;

import logic.State;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    int count = 0;
    State state_class;

    public void computer_play(State s){
        int steps = s.throwSticks();



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
        int steps = state.throwSticks();
        System.out.println("sticks = " + steps);
        ArrayList <String> actions = state.get_possible_actions(is_Human_turn(), steps);
        System.out.println("\nChoose Move :");

        for (int i=0 ; i<actions.size() ; i++){
            System.out.println("["+(i+1)+"]- "+ actions.get(i));
        }
        int choice;
        do {
            choice = scanner.nextInt() - 1;
        }while (choice > actions.size()-1 || choice < 0);

        State next = state.apply_action(
                state.correct_action( actions.get(choice) )
        );
        System.out.println(next);
        if(next.is_terminal()){
            System.out.println("============== HUMAN WIN ==============");
        }
        else{
            computer_play(next);
        }

    }

}
