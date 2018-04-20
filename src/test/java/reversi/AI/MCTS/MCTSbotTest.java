/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import testData.TestData;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.IntPair;
/**
 *
 * @author Valhe Kouneli
 */
public class MCTSbotTest {
    
    MCTSbot<IntPair> bot;
    
    @Before
    public void setUp() {
        bot = new MCTSbot<>(2000);
    }
    
    @Test
    public void botPlaysSomethingEvenWhenItIsItsLastTurnAndItIsLosingAnyway() {
        IntPair move = bot.getNextMove(TestData.getGame1());
        assertNotNull(move);
    }
}
