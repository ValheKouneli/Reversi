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
public class MCTShelperTest {
    
    private Node parent;
    
    @Before
    public void setUp() {
        parent = MCTSTestHelper.getTestNodeWithChildrenButNoSetGames();
    }
    
    @Test
    public void getChildWithMaxScoreWorksForNodeWithChildren() {
        assertEquals(100, MCTShelper.getChildWithMaxScore(parent)
                .getState().getWinScore());
    }
    
    @Test
    public void getChildWithMaxScoreReturnsNullForNodeWithoutChildren() {
        assertEquals(null, MCTShelper
                .getChildWithMaxScore(MCTShelper.getChildWithMaxScore(parent)));
    }
    
    @Test
    public void selectPromisingBranchWorksForNodeWithChildren() {
        assertEquals(2, MCTShelper.selectPromisingBranch(parent)
                .getState().getWinScore());
    }
    
    @Test
    public void selectPromisingBranchReturnsNodeItselfForNodeWithoutChildren() {
        assertEquals(2, MCTShelper.selectPromisingBranch(MCTShelper.selectPromisingBranch(parent))
                .getState().getWinScore());
    }
    
    
    
    
    
}
