package logic;

import structure.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Expectiminimax {


    static boolean ok;
    final static boolean DEBUG = false;
    final static int SKIP_LOAD = 6;
    static class BestMove{
        double value;
        State state;

        public BestMove(State state, double value) {
            this.value = value;
            this.state = state;
        }
    }
    public static State play(State s, int steps, int depth, boolean isMaxPlayer , boolean ok ){
        Expectiminimax.ok = ok;
        return expectimax(s,steps,depth,isMaxPlayer).state;
    }
    public static BestMove expectimax(State s, int steps, int depth, boolean isMaxPlayer){
        if (DEBUG) System.out.println("expectimax s:\n" + s + " expectimax( "+ "steps= " + steps + " depth= " + depth + " isMaxPlayer= " + isMaxPlayer +" )");
        if (DEBUG) System.out.println("s.isTerminal = " + s.is_terminal());
        if(s.is_terminal() || depth == 0) return new BestMove(s,evaluation(s));

        BestMove best = null;
        if (DEBUG) System.out.println("possible_actions : " + s.get_possible_actions(!isMaxPlayer, steps));
        if (DEBUG) System.out.println("next_states.isEmpty? : " + s.generate_next_states(steps, !isMaxPlayer).isEmpty());
        if(isMaxPlayer) { // computer turn
            best = new BestMove(s, Integer.MIN_VALUE);
            if (s.get_possible_actions(false, steps).isEmpty()){
//                best = chance(s, depth - 1, !isMaxPlayer);
                best = new BestMove(s, -SKIP_LOAD);
                return best;
            }

            for(State child_s: s.generate_next_states(steps, false)){
                if (DEBUG) System.out.println("next_states: child_s: \n" + child_s);

                BestMove new_best = chance(child_s,depth-1, isMaxPlayer);
                if(ok){
                    System.out.println(new_best.value + "\n" + best.state);
                }
                best.value = Math.max(best.value , new_best.value);

                if(new_best.value == best.value) best = new_best;
            }
//            best = chance(s,depth-1, isMaxPlayer);

        }else { // human turn
            best = new BestMove(s,Integer.MAX_VALUE);
            if(s.get_possible_actions(true, steps).isEmpty()){
//                best = chance(s, depth-1, !isMaxPlayer);
                best = new BestMove(s, SKIP_LOAD);
                return best;
            }
            for(State child_s: s.generate_next_states(steps, true)){
                if (DEBUG) System.out.println("next_states: child_s: \n" + child_s);

                BestMove new_best = chance(child_s,depth-1, isMaxPlayer);
                if(ok){
                    System.out.println(new_best.value + "\n" + best.state);
                }
                best.value = Math.min(best.value , new_best.value);

                if(new_best.value == best.value) best = new_best;
            }
//            best = chance(s,depth-1, isMaxPlayer);
        }
        if (DEBUG) System.out.println("return best.value= " + best.value);
        if (DEBUG) System.out.println("return best.state:\n" + best.state);
        return best;
    }
    public static BestMove chance(State s, int depth, boolean isMaxPlayer){
        Map<Integer, Double> probabilities = Board.getSticksProbabilities();

        double value = 0;
        if(isMaxPlayer){

            for(int i = 1; i <= 5 ; i++){
                BestMove nextNode = expectimax(s, i, depth, false);
                value = value + (probabilities.get(i) * nextNode.value);
            }

        }else{

            for(int i = 1; i <= 5 ; i++){
                BestMove nextNode = expectimax(s, i, depth, true);
                value = value + (probabilities.get(i) * nextNode.value);
            }

        }

        return new BestMove(s, value);
    }
    public static double evaluation(State s) {
        ArrayList <Integer> Blacks = s.get_black_pieces();
        ArrayList <Integer> Whites = s.get_white_pieces();
        int black_sum = 0;
        for(int black : Blacks){
            if(black == 29){
                black_sum+=35;
                continue;
            } else if (black == 26) {
                black_sum-=26;
            }
            black_sum+=black;
        }
        int white_sum = 0;
        for(int white : Whites){
            if(white == 29){
                white_sum+=35;
                continue;
            }
            else if (white == 26) {
                white_sum-=26;
            }
            white_sum+=white;
        }
        int total_sum = black_sum - white_sum;
        int score_eva = 41*(s.getBlackScore() - s.getWhiteScore());
        return total_sum + score_eva;
    }
}
