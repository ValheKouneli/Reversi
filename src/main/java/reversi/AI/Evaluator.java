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
public abstract class Evaluator <GameType> {

    public Evaluator() {
        //check if GameType is a class that implements Game
    }

    /**
     *
     * @param game
     * @return
     */
    public abstract int eval(GameType game);
    
}
