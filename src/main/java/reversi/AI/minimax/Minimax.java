/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.minimax;

import reversi.game.Game;
import reversi.data_structures.List;


/**
 *
 * @author aepiiroi
 */
public class Minimax {
    
    private Object bestMove;
    
    public Minimax() {
        bestMove = null;
    }
    
    public Object getBestMove() {
        return bestMove;
    }
    
    public int minmax(Game game) {
        return minmax(game, 0, Integer.MAX_VALUE, null);
    }
    
    public int minmax(Game game, int depth, int max_depth, Evaluator eval) {
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
                int value = minmax(copy, depth+1, max_depth, eval);
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
