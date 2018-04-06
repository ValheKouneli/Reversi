/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

import reversi.data_structures.List;
import reversi.data_structures.Pair;
import reversi.data_structures.MoveAndValue;
import reversi.game.Game;

/**
 *
 * @author aepiiroi
 */
public class MinMax {
    
    private MinMax() {}
    
   
    public int minmax(Game game, int depth, int max_depth) {
        List<Pair> moves = game.getAvailableMoves();

        
        if (moves.isEmpty()) {
            return scoreFinal(game);
        } else if (depth == max_depth) {
            return evaluateSituation(game);
        }

        MoveAndValue bestMoveAndValue =
                findBestMove(game, moves, depth, game.getTurn(), max_depth);
        
        if (depth == 0) {
            game.move(bestMoveAndValue.getX(), bestMoveAndValue.getY());
        }
        return bestMoveAndValue.getValue();
    }
    
    /**
     * Go through given moves and return the best move with its value.
     * @param game game in question
     * @param moves moves to choose from
     * @param depth how deep we are in the search tree
     * @param turn whose turn it is
     * @param max_depth how many moves deep we should count
     * @return best move and its value
     */
    private MoveAndValue findBestMove(Game game, List<Pair> moves,
            int depth, int turn, int max_depth) {
        //go through all possible moves and
        //and find out which move brings the best result
       
        /* save best move here */
        MoveAndValue bestMoveAndValue = new MoveAndValue(null, 0);
        
        int bestScoreSoFar =
                (turn == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        
        /* go through moves */
        for (int i=0; i<moves.size(); i++) {
            /* copy game and play on copy */
            Game gameCopy = game.getCopy();
            gameCopy.move(moves.get(i).getX(), moves.get(i).getY());
            
            /* recursion ! */
            int value = minmax(game, depth+1, max_depth);
            
            boolean betterValueFound =
                (turn == 1 ? value > bestScoreSoFar : value < bestScoreSoFar);
            
            if (betterValueFound) {
                bestScoreSoFar = value;
                    bestMoveAndValue.setMove(moves.get(i));
                    bestMoveAndValue.setValue(value);
            }
        }
        
        return bestMoveAndValue;
    }
    
    private int scoreFinal(Game game) {
        if (game.getScore() > 0) {
            return Integer.MAX_VALUE;
        } else if (game.getScore() < 0) {
            return Integer.MIN_VALUE;
        } else {
            return 0;
        }
    }
    
    private int evaluateSituation(Game game) {
        //should never return Integer.MIN_VALUE or Integer.MAX_VALUE
        return game.getScore();
    }
    
}
