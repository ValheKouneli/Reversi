package reversi.AI.minimax;

import reversi.game.Game;
import reversi.data_structures.List;


/**
 * Helper class that runs the minimax algorithm
 * @author aepiiroi
 */
public class Minimax {
    
    private Object bestMove;
   
    /**
     * Creates a Minimax to take care of your minimax needs
     */
    public Minimax() {
        bestMove = null;
    }
    
    /**
     * @return the best move according to the last run of the minimax algorithm
     * @throws IllegalStateException if the minimax algorithm is not run before calling this
     */
    public Object getBestMove() {
        if (bestMove == null) {
            throw new java.lang.IllegalStateException(
                    "minimax algorithm is not run yet");
        }
        return bestMove;
    }
    
    /**
     * Runs the minimax algorithm in the given game situation and saves the best move in bestMove
     * @param game to choose move to
     * @return Integer.MIN_VALUE if the player in turn is going to lose, Integer.MAX_VALUE if the player in turn is going to win
     */
    public int minimax(Game game) {
        return minimax(game, 0, Integer.MAX_VALUE, null);
    }
    
    /**
     * A recursive implementation of the minimax algorithm; saves the best move in bestMove
     * @param game to choose a move to
     * @param depth were are in at the moment
     * @param max_depth to look into
     * @param eval is an evaluator to use when at the max depth
     * @return best evaluated value for moves on this level
     */
    public int minimax(Game game, int depth, int max_depth, Evaluator eval) {
        if (game.gameIsOver()) {
            return (game.winner() == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE);
        } else if (depth == max_depth) {
            if (eval == null) {
                throw new NullPointerException("Evaluator not set.");
            }
            return eval.eval(game);
        } else {
            List<Object> moves = game.getMoves();
            int bestSoFar =
                    (game.getTurn() == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            Object bestMoveSoFar = null;
            
            for (int i=0; i<moves.size(); i++) {
                Game copy = game.getCopy();
                copy.move(moves.get(i));
                int value = minimax(copy, depth+1, max_depth, eval);
                if (game.getTurn() == 1 ? value>bestSoFar : value<bestSoFar) {
                    bestSoFar = value;
                    bestMoveSoFar = moves.get(i);
                }
            }
            if (depth==0) {
                if (bestMoveSoFar == null) {
                    bestMoveSoFar = moves.get(0);
                }
                bestMove = bestMoveSoFar;
            }
            return bestSoFar;
        }
    }
    
    
}
