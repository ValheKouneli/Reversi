package reversi.AI.minimax;

/**
 *
 * @author Valhe Kouneli
 */
public interface Evaluator {
    /**
     *
     * @param game
     * @return number evaluation of the game situation
     */
    public int eval(Object game);
    public String name();
    public Class getGameType();
    
    
}
