/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public interface Game <MoveType> {
    
    public boolean move(MoveType move);
    public int winner();
    public List<MoveType> getMoves();
    public int getTurn();
    public int getTurnNumber();
    public Game getCopy();
    public boolean gameIsOver();
    
}
