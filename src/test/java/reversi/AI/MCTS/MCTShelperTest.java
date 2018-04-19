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
    
    private Node<Pair> nodeWithChildren;
    private Node<Pair> nodeWithoutChildren;
    private MCTShelper<Pair> MCTShelper;
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
        assertEquals(100, MCTShelper.getChildWithMaxScore(nodeWithChildren)
                .getState().getWinScore());
    }
    
    @Test
    public void expandNodeExpandsNodeTheRightAmountOfNodes() {
        Node<Pair> node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        assertEquals(2, node.getChildren().size());
    }
    
    @Test
    public void expandNodeSetsTheStateOfTheExpansionNodes() {
        Node<Pair> node = nodeWithoutChildren;
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
        Node<Pair> node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        assertEquals(62, node.getChildren().get(0).getParent().getState().getGame().getTurnNumber());
    }
    
    @Test
    public void backPropagationIncrementsVisitCounts() {
        Node<Pair> node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node<Pair> child = node.getChildren().get(0);
        //imaginary random playout ending in opponent (black) victory here
        MCTShelper.backPropagation(child, -1);
        assertEquals(1, child.getState().getVisitCount());
        assertEquals(1, node.getState().getVisitCount());
    }
    
    @Test
    public void backPropagationIncrementsWinCountForNodesWhereAIjustPlayed() {
        Node<Pair> node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node<Pair> child = node.getChildren().get(0);
        //imaginary random playout ending in own (white) victory here
        MCTShelper.backPropagation(child, 1);
        assertEquals(1, child.getState().getWinScore());
        assertEquals(0, node.getState().getWinScore());
    }
    
    @Test
    public void backPropagationIncrementsWinCountForNodesWhereAIsOpponentJustPlayed() {
        Node<Pair> node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node<Pair> child = node.getChildren().get(0);
        //imaginary random playout ending in opponen (black) victory here
        MCTShelper.backPropagation(child, -1);
        assertEquals(0, child.getState().getWinScore());
        assertEquals(1, node.getState().getWinScore());
    }
    
    @Test
    public void simulateRandomPlayoutMarksMovesLeadingToOpponentInstantVictoryAsIndefinitelyBad() {
        Node<Pair> node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node<Pair> child = node.getChildren().get(0);
        MCTShelper.expandNode(child);
        Node<Pair> grandchild = child.getChildren().get(0);
        MCTShelper.simulateRandomPlayout(grandchild);
        //it is known that the opponent, black(-1), wins in grandchild situation
        assertEquals(Integer.MIN_VALUE, child.getState().getWinScore());
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
        assertEquals(2, MCTShelper.selectPromisingBranch(nodeWithChildren)
                .getState().getWinScore());
    }
    
    @Test
    public void selectPromisingBranchReturnsNodeItselfForNodeWithoutChildren() {
        assertEquals(2, MCTShelper.selectPromisingBranch(MCTShelper.selectPromisingBranch(nodeWithChildren))
                .getState().getWinScore());
    }
   
    
    
    
    
}
