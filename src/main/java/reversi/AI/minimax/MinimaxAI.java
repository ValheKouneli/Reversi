package reversi.AI.minimax;

import reversi.game.Game;
import reversi.game.Player;

/**
 * Plays moves on a Game of GameType.
 * Evaluator GameType is given in constructor.
 * 
 * @author Valhe Kouneli
 */
public class MinimaxAI implements Player {
    
    private final Evaluator eval;
    private final Minimax minimax;
    
    /**
     *
     * @param eval evaluator of the same game type as this Player
     */
    public MinimaxAI(Evaluator eval) {
        this.eval = eval;
        minimax = new Minimax();
    }
    
    public final static int MAX_DEPTH = 5;
    
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
        minimax.minmax((Game) game, 0, MAX_DEPTH, eval);
        return minimax.getBestMove();
    }
    
    @Override
    public String name() {
        return "Minimax AI + " + eval.name() + ", depth " + MAX_DEPTH;
    }
    
}
