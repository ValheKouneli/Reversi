package reversi.AI.minimax;

import reversi.game.Game;
import reversi.game.Player;

/**
 * Chooses move to play in the given game using minimax to the set depth.
 * Evaluator GameType is given in constructor.
 * 
 * @author Valhe Kouneli
 */
public class MinimaxAI implements Player {
    
    private final Evaluator eval;
    private int depth;
    private final Minimax minimax;
    
    /**
     *
     * @param eval evaluator of the same game type as this Player
     */
    public MinimaxAI(Evaluator eval) {
        this.eval = eval;
        depth = 5;
        minimax = new Minimax();
    }
    
    public MinimaxAI(Evaluator eval, int depth) {
        this.eval = eval;
        this.depth = depth;
        minimax = new Minimax();
    }
    
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    /**
     * Chooses a move in the given game situation based on a minimax algorithm.
     * @param game 
     * @return  
     */
    @Override
    public Object getNextMove(Game game) {
        if (game.getMoves().size() == 0) {
            return null;
        } else {
            if (game.getClass() != eval.getGameType()) {
                throw new java.lang.IllegalArgumentException(
                        "This MinimaxAI does not play games "
                                + "of the given type.");
            }
        }
        minimax.minmax(game, 0, depth, eval);
        return minimax.getBestMove();
    }
    
    @Override
    public String name() {
        return "MinimaxAI (depth " + depth + ") + " + eval.name();
    }
    
}
