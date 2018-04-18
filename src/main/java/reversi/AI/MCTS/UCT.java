/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;

import reversi.data_structures.List;

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
    
    public static double uctValue(Node node) {
        if (node.getParent() == null) {
            throw new java.lang.NullPointerException("Node has no parent.");
        }
        int parentVisitCount = node.getParent().getState().getVisitCount();
        double nodeWinScore = node.getState().getWinScore();
        int nodeVisitCount = node.getState().getVisitCount();
        return uctValue(parentVisitCount, nodeWinScore, nodeVisitCount);
    }
    
    /** Returns the child with the best UCT value for the given node.
     * 
     * @param node  a node
     * @return node's child node with the best UCT value out of all child nodes
     */
    public static Node getChildWithBestUCTValue(Node node) {
        int parentVisitCount = node.getState().getVisitCount();
        List<Node> children = node.getChildren();
        Node bestChild = null;
        double bestUCTvalue = Integer.MIN_VALUE;
        Node child;
        double uctValue;
        for (int i=0; i<children.size(); i++) {
            child = children.get(i);
            uctValue = uctValue(parentVisitCount, child.getState().getWinScore(), child.getState().getVisitCount());
            if (uctValue > bestUCTvalue) {
                bestUCTvalue = uctValue;
                bestChild = child;
            }
        }
        return bestChild;
    }
    
}
