/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.Pair;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTShelperTest {
    
    private Node<Pair> parent;
    private MCTShelper<Pair> MCTShelper;
    private Random random;
    
    @Before
    public void setUp() {
        MCTShelper = new MCTShelper<>();
        parent = MCTSTestHelper.getTestNodeWithChildrenButNoSetGames();
        random = new Random(System.currentTimeMillis());
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
    public void getChildWithMaxScoreDoesNotReturnNullForNodeWithInfinetelyBadChildren() {
        assertNotNull(MCTShelper.getChildWithMaxScore(MCTSTestHelper.getTestNodeWithChildrenWithInfenitelyBadScores()));
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
