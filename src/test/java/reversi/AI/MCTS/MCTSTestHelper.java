/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTSTestHelper {
    
    private MCTSTestHelper() {}
    
    public static final void addNewChild(int childWinScore, int childVisitCount, Node parent) {
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
    
    public static Node getTestNode() {
        Node parent = new Node(new State());
        MCTSTestHelper.addNewChild(2, 2, parent); //3.17377697326
        MCTSTestHelper.addNewChild(4, 4, parent); //2.53709243858
        MCTSTestHelper.addNewChild(1, 3, parent); //2.1082147997
        MCTSTestHelper.addNewChild(5, 6, parent); //2.088364054
        MCTSTestHelper.addNewChild(100, 101, parent); //1.29599183814
        return parent;
    }
}
