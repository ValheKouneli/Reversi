package reversi.game.reversi;

import reversi.AI.minimax.Evaluator;

/**
 * This evaluator evaluates the Reversi game situation just by counting how
 * many black pieces and write pieces there are.
 * 
 * @author Valhe Kouneli
 */
public class ReversiEvaluator1 implements Evaluator {

    /**
     *
     * @param game
     * @return
     */
    @Override
    public int eval(Object game) {
        Reversi reversi = (Reversi) game;
        return reversi.getScore();
    }
    
    @Override
    public String name() {
        return "SimpleEvaluator";
    }

    @Override
    public Class getGameType() {
        return Reversi.class;
    }
    
}
