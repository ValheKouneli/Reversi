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
 * @param <GameType> type of game the MinimaxAI will play
 * @param <MoveType>
 */
public class MinimaxAI <GameType, MoveType> implements AI <MoveType> {
    
    private final Evaluator<GameType> eval;
    private final Minimax minimax;
    
    /**
     *
     * @param eval evaluator of the same game type as this AI
     */
    public MinimaxAI(Evaluator<GameType> eval) {
        this.eval = eval;
        minimax = new Minimax();
    }
    
    public final static int MAX_DEPTH = 5;
    
    /**
     * Chooses a move in the given game situation based on a minimax algorithm.
     * @param game 
     * @return  
     */
    @Override
    public MoveType getNextMove(Game game) {
        minimax.minmax((Game) game, 0, MAX_DEPTH, eval);
        return (MoveType) minimax.getBestMove();
    }
    
}
