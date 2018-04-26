package reversi.AI.minimax;

/**
 *
 * @author Valhe Kouneli
 */
public interface Evaluator {
    /**
     *
     * @param game
     * @return
     */
    public int eval(Object game);
    public String name();
    public Class getGameType();
    
    
}
