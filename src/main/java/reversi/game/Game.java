package reversi.game;

import reversi.data_structures.List;

/**
 * Represents a game
 * @author Valhe Kouneli
 */
public interface Game {
    
    /**
     * @param move to be played the game
     * @return if the move was made (true, if move was legal)
     */
    public boolean move(Object move);
    /**
     * @return winner of the game represented as an integer
     */
    public int winner();
    /**
     * @return list of legal moves in this game situation
     */
    public List<Object> getMoves();
    /**
     * @return whose turn it is, represented as an integer
     */
    public int getTurn();
    /**
     * @return number of turns that has been played in the game
     */
    public int getTurnNumber();
    /**
     * @return copy of this game
     */
    public Game getCopy();
    /**
     * @return true is game is over, false if not
     */
    public boolean gameIsOver();
    /**
     * @return game situation represented as a string
     */
    @Override
    public String toString();
    
}
