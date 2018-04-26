package reversi.game;


/**
 *
 * @author Valhe Kouneli
 */
public interface Player {

    /**
     *
     * @param game
     * @return
     */
    public Object getNextMove(Game game);
    public String name();
}
