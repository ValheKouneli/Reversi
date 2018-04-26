/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import reversi.game.Game;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public interface Player <MoveType> {

    /**
     *
     * @param game
     * @return
     */
    public MoveType getNextMove(Game game);
    public String name();
}
