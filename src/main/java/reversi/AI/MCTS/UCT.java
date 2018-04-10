/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;

import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Valhe Kouneli
 */
public class UCT {
    
    private UCT() {}
    
    /** Returns the UCT (Upper Confidence bound applied to Trees)
     * value for the node whose information is given as parameter.
     * 
     * @param parentVisitCount  the node's parent's visitCount
     * @param nodeWinScore the node's winScore
     * @param nodeVisitCount the node's visitCount
     * @return UCT value for the node calculated according to the UCT formula
     */
    public static double uctValue(int parentVisitCount,
            double nodeWinScore, int nodeVisitCount) {
        if (nodeVisitCount == 0) {
            return Integer.MAX_VALUE;
        } else {
            return ((double) nodeWinScore / (double) nodeVisitCount) +
                    1.41 * Math.sqrt(Math.log(parentVisitCount) / 
                            (double) nodeVisitCount);
        }
    }
    
    /** Returns the child with the best UCT value for the given node.
     * 
     * @param node  a node
     * @return node's child node with the best UCT value out of all child nodes
     */
    public static Node getChildWithBestUCTValue(Node node) {
        int parentVisitCount = node.getState().getVisitCount();
        return Collections.max(node.getChildren(),
                Comparator.comparing(c -> 
                        uctValue(parentVisitCount,
                                c.getState().getWinScore(),
                                c.getState().getVisitCount())));
    }
    
}
