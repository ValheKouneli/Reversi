/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

import reversi.game.Reversi;

/**
 * This evaluator evaluates the Reversi game situation just by counting how
 * many black pieces and write pieces there are.
 * 
 * @author Valhe Kouneli
 */
public class ReversiEvaluator1 extends Evaluator <Reversi> {

    /**
     *
     * @param game
     * @return
     */
    @Override
    public int eval(Reversi game) {
        return game.getScore();
    }
    
    @Override
    public String name() {
        return "SimpleEvaluator";
    }
    
}
