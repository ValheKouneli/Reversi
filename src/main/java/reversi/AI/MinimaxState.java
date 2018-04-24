/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

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
