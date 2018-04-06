/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;





public class AI  {
    
    private Evaluator eval;
    
    public AI(Evaluator eval) {
        this.eval = eval;
    }
    
    public final static int MAX_DEPTH = 7;
    
    public void makeNextMove(Game game) {
        MinMax.minmax(game, 0, MAX_DEPTH, eval);
    }
    
}
