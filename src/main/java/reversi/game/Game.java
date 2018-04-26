package reversi.game;

import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public interface Game {
    
    public boolean move(Object move);
    public int winner();
    public List<Object> getMoves();
    public int getTurn();
    public int getTurnNumber();
    public Game getCopy();
    public boolean gameIsOver();
    
}
