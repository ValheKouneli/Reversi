/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import reversi.data_structures.Node;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.IntPair;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTShelperTest {
    
    private Node nodeWithChildren;
    private Node nodeWithoutChildren;
    private MCTShelper<IntPair> MCTShelper;
    private Random random;
    
    @Before
    public void setUp() {
        MCTShelper = new MCTShelper<>();
        nodeWithChildren = MCTSTestHelper.getTestNodeWithChildrenButNoSetGames();
        nodeWithoutChildren = MCTSTestHelper.getTestNodeWithSetGameButNoChildren();
        random = new Random(System.currentTimeMillis());
    }
    
    @Test
    public void getChildWithMaxScoreWorksForNodeWithChildren() {
        assertEquals(100, ((MCTSState) MCTShelper.getChildWithMaxScore(nodeWithChildren)
                .getState()).getWinScore());
    }
    
    @Test
    public void expandNodeExpandsNodeTheRightAmountOfNodes() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        assertEquals(2, node.getChildren().size());
    }
    
    @Test
    public void expandNodeSetsTheStateOfTheExpansionNodes() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        assertNotNull(node.getChildren().get(0).getState());
    }
    
    @Test
    public void expandNodeDoesNotAddDuplicateChildren() {
        MCTShelper.expandNode(nodeWithChildren);
        assertEquals(5, nodeWithChildren.getChildren().size());
    }
    
    @Test
    public void expandNodeSetsChildNodesParentRight() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        assertEquals(62, ((MCTSState) node.getChildren().get(0).getParent().getState()).getGame().getTurnNumber());
    }
    
    @Test
    public void backPropagationIncrementsVisitCounts() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node child = node.getChildren().get(0);
        //imaginary random playout ending in opponent (black) victory here
        MCTShelper.backPropagation(child, -1);
        assertEquals(1, ((MCTSState) child.getState()).getVisitCount());
        assertEquals(1, ((MCTSState) node.getState()).getVisitCount());
    }
    
    @Test
    public void backPropagationIncrementsWinCountForNodesWhereAIjustPlayed() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node child = node.getChildren().get(0);
        //imaginary random playout ending in own (white) victory here
        MCTShelper.backPropagation(child, 1);
        assertEquals(1, ((MCTSState) child.getState()).getWinScore());
        assertEquals(0, ((MCTSState) node.getState()).getWinScore());
    }
    
    @Test
    public void backPropagationIncrementsWinCountForNodesWhereAIsOpponentJustPlayed() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node child = node.getChildren().get(0);
        //imaginary random playout ending in opponen (black) victory here
        MCTShelper.backPropagation(child, -1);
        assertEquals(0, ((MCTSState) child.getState()).getWinScore());
        assertEquals(1, ((MCTSState) node.getState()).getWinScore());
    }
    
    @Test
    public void simulateRandomPlayoutMarksMovesLeadingToOpponentInstantVictoryAsIndefinitelyBad() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node child = node.getChildren().get(0);
        MCTShelper.expandNode(child);
        Node grandchild = child.getChildren().get(0);
        MCTShelper.simulateRandomPlayout(grandchild);
        //it is known that the opponent, black(-1), wins in grandchild situation
        assertEquals(Integer.MIN_VALUE, ((MCTSState) child.getState()).getWinScore());
    }
    
    @Test
    public void getChildWithMaxScoreReturnsNullForNodeWithoutChildren() {
        assertEquals(null, MCTShelper
                .getChildWithMaxScore(MCTShelper.getChildWithMaxScore(nodeWithChildren)));
    }
    
    @Test
    public void getChildWithMaxScoreDoesNotReturnNullForNodeWithInfinetelyBadChildren() {
        assertNotNull(MCTShelper.getChildWithMaxScore(MCTSTestHelper.getTestNodeWithChildrenWithInfenitelyBadScores()));
    }
    
    @Test
    public void selectPromisingBranchWorksForNodeWithChildren() {
        assertEquals(2, ((MCTSState) MCTShelper.selectPromisingBranch(nodeWithChildren)
                .getState()).getWinScore());
    }
    
    @Test
    public void selectPromisingBranchReturnsNodeItselfForNodeWithoutChildren() {
        assertEquals(2, ((MCTSState) MCTShelper.selectPromisingBranch(MCTShelper.selectPromisingBranch(nodeWithChildren))
                .getState()).getWinScore());
    }
   
    
    
    
    
}
