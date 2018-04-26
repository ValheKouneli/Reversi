package reversi.AI.minimax.timelimited;

import reversi.game.Game;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class MinimaxState <MoveType> {
    
    private final Game game;
    private final MoveType latestMove;
    private int value;
    
    public MinimaxState(Game game) {
        this.game = game;
        latestMove = null;
        value = 0;
    }
    
    public MinimaxState(Game game, MoveType latestMove) {
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
    
    public MoveType getLatestMove() {
        return latestMove;
    }
    
    public Game getGame() {
        return game;
    }
    
}
