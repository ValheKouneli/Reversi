package reversi.AI.minimax;

/**
 * Evaluates game situations 
 * @author Valhe Kouneli
 */
public interface Evaluator {
    /**
     *
     * @param game
     * @return number evaluation of the game situation
     */
    public int eval(Object game);
    /**
     * @return name of this evaluator
     */
    public String name();
    /**
     * @return class of the game this evaluator evaluates
     */
    public Class getGameType();
    
    
}
