/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Valhe Kouneli
 */
public class MCTSbotTest {
    
    MCTSbot bot;
    
    @Before
    public void setUp() {
        bot = new MCTSbot(1, -1, 9999999);
    }
    
    @Test
    public void botPlaysTheBestMoveWhenItIsItsLastTurn() {
        
    }
}
