/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTSbotTest {
    
    private Node parent;
    
    @Before
    public void setUp() {
        parent = MCTSTestHelper.getTestNode();
    }
    
    @Test
    public void getChildWithMaxScoreWorks() {
        assertEquals(100, MCTSbot.getChildWithMaxScore(parent).getState().getWinScore());
    }
    
}
