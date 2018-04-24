/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import reversi.data_structures.Node;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.IntPair;

/**
 *
 * @author Valhe Kouneli
 */
public class UCTTest {
    
    private UCT<IntPair> uct;
    
    private Node parent;
    
    @Before
    public void setUp() {
        parent = MCTSTestHelper.getTestNodeWithChildrenButNoSetGames();
        uct = new UCT<>();
    }
    
    @Test
    public void uctValueWorks() {
        assertEquals(3.17377697326, uct.uctValue(parent.getChildren().get(0)), 0.01);
        Node child = parent.getChildren().get(4);
        System.out.println(((MCTSState) child.getState()).getVisitCount());
        assertEquals(1.29599183814, uct.uctValue(parent.getChildren().get(4)), 0.01);
        MCTSTestHelper.addNewChild(0, 0, parent);
        assertEquals(Integer.MAX_VALUE, uct.uctValue(parent.getChildren().get(5)), 0.01);
    }
    
    @Test
    public void getChildWithBestUCTValueWorks() {
        assertEquals(2, ((MCTSState) uct.getChildWithBestUCTValue(parent).getState()).getVisitCount());
    }
    
}
