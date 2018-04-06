/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

/**
 *
 * @author Valhe Kouneli
 * @param <GameType>
 */
public interface Evaluator <GameType> {

    public int eval(GameType game);
    
}
