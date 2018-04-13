/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

/**
 * Plays moves on a Game of GameType.
 * Evaluator GameType is given in constructor.
 * 
 * @author Valhe Kouneli
 * @param <GameType> type of game the AI will play
 */
public class AI <GameType> {
    
    private final Evaluator<GameType> eval;
    
    /**
     *
     * @param eval evaluator of the same game type as this AI
     */
    public AI(Evaluator<GameType> eval) {
        this.eval = eval;
    }
    
    public final static int MAX_DEPTH = 5;
    
    /**
     * Chooses a move in the given game situation based on a minimax algorithm.
     * @param game 
     */
    public void makeNextMove(GameType game) {
        MinMax.minmax((Game) game, 0, MAX_DEPTH, eval);
    }
    
}
