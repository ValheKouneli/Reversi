package reversi.game;


/**
 *
 * @author Valhe Kouneli
 */
public interface Player {

    /**
     *
     * @param game
     * @return next move in game
     */
    public Object getNextMove(Game game);
    /**
     * @return name of this player
     */
    public String name();
}
