package reversi.AI.minimax.timelimited;

import reversi.game.Game;

/**
 *
 * @author Valhe Kouneli
 */
public class MinimaxState {
    
    private final Game game;
    private final Object latestMove;
    private int value;
    
    public MinimaxState(Game game) {
        this.game = game;
        latestMove = null;
        value = 0;
    }
    
    public MinimaxState(Game game, Object latestMove) {
        this.game = game;
        this.latestMove = latestMove;
        value = 0;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public Object getLatestMove() {
        return latestMove;
    }
    
    public Game getGame() {
        return game;
    }
    
}
