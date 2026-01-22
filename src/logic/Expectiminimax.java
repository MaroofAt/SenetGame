package logic;

import structure.Board;

import java.util.HashMap;
import java.util.Map;

public class Expectiminimax {

    static class BestMove{
        double value;
        State state;

        public BestMove(State state, double value) {
            this.value = value;
            this.state = state;
        }
    }
    public static State play(State s, int steps, int depth, boolean isMaxPlayer){
        return expectimax(s,steps,depth,isMaxPlayer).state;
    }
    public static BestMove expectimax(State s, int steps, int depth, boolean isMaxPlayer){
        if(s.is_terminal() || depth == 0) return new BestMove(s,evaluation(s));

        BestMove best = null;
        if(isMaxPlayer){ // computer turn
            best = new BestMove(s,Integer.MAX_VALUE);
            for(State child_s: s.generate_next_states(steps, !isMaxPlayer)){
                BestMove new_best = chance(child_s,depth-1, isMaxPlayer);

                best.value = Math.min(best.value , new_best.value);

                if(new_best.value == best.value) best = new_best;
            }
//            best = chance(s,depth-1, isMaxPlayer);

        }else { // human turn
            best = new BestMove(s,Integer.MAX_VALUE);
            for(State child_s: s.generate_next_states(steps, !isMaxPlayer)){
                BestMove new_best = chance(child_s,depth-1, isMaxPlayer);

                best.value = Math.min(best.value , new_best.value);

                if(new_best.value == best.value) best = new_best;
            }
//            best = chance(s,depth-1, isMaxPlayer);
        }

        return best;
    }
    public static BestMove chance(State s, int depth, boolean isMaxPlayer){
        Map<Integer, Double> probabilities = Board.getSticksProbabilities();

        double value = 0;
        if(isMaxPlayer){

            for(int i = 1; i <= 5 ; i++){
                BestMove nextNode = expectimax(s, i, depth, !isMaxPlayer);
                value = value + (probabilities.get(i) * nextNode.value);
            }

        }else{

            for(int i = 1; i <= 5 ; i++){
                BestMove nextNode = expectimax(s, i, depth, !isMaxPlayer);
                value = value + (probabilities.get(i) * nextNode.value);
            }

        }

        return new BestMove(s, value);
    }
    public static double evaluation(State s) {
        return s.getBlackScore() - s.getWhiteScore();
    }
}
