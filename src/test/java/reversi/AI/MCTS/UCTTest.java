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
public class UCTTest {
    
    private Node parent;
    
    @Before
    public void setUp() {
        parent = new Node(new State());
        
        addNewChild(2, 2, parent); //3.17377697326
        addNewChild(4, 4, parent); //2.53709243858
        addNewChild(1, 3, parent); //2.1082147997
        addNewChild(5, 6, parent); //2.088364054
        addNewChild(100, 101, parent); //1.29599183814

    }
    
    @Test
    public void uctValueWorks() {
        assertEquals(3.17377697326, UCT.uctValue(parent.getChildren().get(0)), 0.01);
        Node child = parent.getChildren().get(4);
        System.out.println(child.getState().getVisitCount());
        assertEquals(1.29599183814, UCT.uctValue(parent.getChildren().get(4)), 0.01);
        addNewChild(0, 0, parent);
        assertEquals(Integer.MAX_VALUE, UCT.uctValue(parent.getChildren().get(5)), 0.01);
    }
    
    @Test
    public void getChildWithBestUCTvalueWorks() {
        assertEquals(2, UCT.getChildWithBestUCTValue(parent).getState().getVisitCount());
    }
    
    private static void addNewChild(int childWinScore, int childVisitCount, Node parent) {
        State childState = new State();
        childState.setVisitCount(childVisitCount);
        childState.setWinScore(childWinScore);
        Node child = new Node(childState);
        child.setParent(parent);
        parent.addChild(child);
        parent.getState().setVisitCount(parent.getState().getVisitCount() +
                childVisitCount);
        parent.getState().setWinScore(parent.getState().getWinScore() + 
                childWinScore);
    }
    
    
    
}
