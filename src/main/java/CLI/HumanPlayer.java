/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import reversi.AI.Game;
import reversi.AI.Player;
import reversi.data_structures.IntPair;
/**
 *
 * @author Valhe Kouneli
 */
public class HumanPlayer implements Player <IntPair> {

    private final IO io;
    private String name;
    
    public HumanPlayer(IO io) {
        this.io = io;
        
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String name() {
        if (name == null) {
            return "Human player";
        } else {
            return name;
        }
    }
    @Override
    public IntPair getNextMove(Game game) {
        return io.nextMove();
    }

   
    
}
