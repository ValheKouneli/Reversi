/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.IntPair;

/**
 *
 * @author Valhe Kouneli
 */
public class UCTTest {
    
    private Node<IntPair> parent;
    
    @Before
    public void setUp() {
        parent = MCTSTestHelper.getTestNodeWithChildrenButNoSetGames();
    }
    
    @Test
    public void uctValueWorks() {
        assertEquals(3.17377697326, UCT.uctValue(parent.getChildren().get(0)), 0.01);
        Node child = parent.getChildren().get(4);
        System.out.println(child.getState().getVisitCount());
        assertEquals(1.29599183814, UCT.uctValue(parent.getChildren().get(4)), 0.01);
        MCTSTestHelper.addNewChild(0, 0, parent);
        assertEquals(Integer.MAX_VALUE, UCT.uctValue(parent.getChildren().get(5)), 0.01);
    }
    
    @Test
    public void getChildWithBestUCTValueWorks() {
        assertEquals(2, UCT.getChildWithBestUCTValue(parent).getState().getVisitCount());
    }
    
}
