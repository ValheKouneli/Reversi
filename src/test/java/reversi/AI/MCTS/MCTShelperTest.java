package reversi.AI.MCTS;
import reversi.data_structures.Node;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import reversi.game.reversi.BoardFactory;
import reversi.game.reversi.Reversi;
import reversi.game.reversi.ReversiBoard;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTShelperTest {
    
    private Node nodeWithChildren;
    private Node nodeWithoutChildren;
    private MCTShelper MCTShelper;
    private Random random;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        nodeWithChildren = MCTSTestHelper.getTestNodeWithChildrenButNoSetGames();
        nodeWithoutChildren = MCTSTestHelper.getTestNodeWithSetGameButNoChildren();
        random = new Random(System.currentTimeMillis());
        MCTShelper = new MCTShelper(random);
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
        //imaginary random playout ending in opponent (white) victory here
        MCTShelper.backPropagation(child, 1);
        assertEquals(1, ((MCTSState) child.getState()).getVisitCount());
        assertEquals(1, ((MCTSState) node.getState()).getVisitCount());
    }
    
    @Test
    public void backPropagationIncrementsWinCountForNodesWhereAIjustPlayed() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node child = node.getChildren().get(0);
        //imaginary random playout ending in own (black) victory here
        MCTShelper.backPropagation(child, -1);
        assertEquals(1, ((MCTSState) child.getState()).getWinScore());
        assertEquals(0, ((MCTSState) node.getState()).getWinScore());
    }
    
    @Test
    public void backPropagationIncrementsWinCountForNodesWhereAIsOpponentJustPlayed() {
        Node node = nodeWithoutChildren;
        MCTShelper.expandNode(node);
        Node child = node.getChildren().get(0);
        //imaginary random playout ending in opponent (white) victory here
        MCTShelper.backPropagation(child, 1);
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
        //it is known that the opponent, white (1), wins in grandchild situation
        assertEquals(Integer.MIN_VALUE, ((MCTSState) child.getState()).getWinScore());
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
    
    @Test
    public void getRandomChildNodeGivesAChildNode() {
        Node randomChild = MCTShelper.getRandomChildNode(nodeWithChildren);
        assertEquals(true, nodeWithChildren.getChildren().contains(randomChild));
    }
    
    @Test
    public void simulateRandomPlayoutReturnRightWinner() {
        int winner = MCTShelper.simulateRandomPlayout(nodeWithoutChildren);
        assertEquals(1, winner);
    }
    
    @Test
    public void simulateRandomPlayoutSetsLosingNodesParentsValueToSuperBad() {
        /*
        Set-up starts
        */

        Node node = nodeWithoutChildren;
        //in all of the following situations,
        //the last move is played by the winner
        
        MCTShelper.expandNode(node);
        for (int i=0; i<node.getChildren().size(); i++) {
            MCTShelper.expandNode(node.getChildren().get(i));
        }
        Node grandchild = node.getChildren().get(0)
                .getChildren().get(0);
        /*
        Set-up ends
        */
        MCTShelper.simulateRandomPlayout(grandchild);
        assertEquals(Integer.MIN_VALUE, ((MCTSState) grandchild.getParent().getState()).getWinScore());
        
    }
   
    @Test
    public void tryingToGetRandomChildNodeFromNodeWithoutChildrenThrowsException() {
        exception.expect(IllegalArgumentException.class);
        Node node = MCTShelper.getRandomChildNode(nodeWithoutChildren);
    }
   
    
    
    
    
}
