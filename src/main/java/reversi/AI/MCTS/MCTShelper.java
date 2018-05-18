package reversi.AI.MCTS;

import reversi.data_structures.Node;
import java.util.Random;
import reversi.data_structures.List;

/**
 * Helper class for MCSTBot
 * @author Valhe Kouneli
 */
public class MCTShelper {
    
    private final Random random;
    
    public MCTShelper(Random random) {
        this.random = random;
    }
    
    public MCTShelper() {
        this.random = new Random(System.currentTimeMillis());
    }
    
    /**
     * @param node
     * @return node's random child
     * @throws IllegalArgumentException of node has no children
     */
    protected Node getRandomChildNode(Node node) {
        int size = node.getChildren().size();
        if (size > 0) {
            int i = random.nextInt(size);
            return node.getChildren().get(i);
        } else {
            throw new java.lang.IllegalArgumentException("node has no childen");
        }
    }

    /**
     * @param node
     * @return node's child with the best score
     * @throws IllegalArgumentException if node has no children
     */
    protected Node getChildWithMaxScore(Node node) {
        List<Node> children = node.getChildren();
        double bestScore = Integer.MIN_VALUE;
        Node bestChild = null;
        Node child;
        for (int i = 0; i < children.size(); i++) {
            child = children.get(i);
            if (((MCTSState)child.getState()).getWinScore() > bestScore) {
                bestScore = ((MCTSState) child.getState()).getWinScore();
                bestChild = child;
            }
        }
        if (bestChild == null) {
            return getRandomChildNode(node);
            //throws an exception if node has no children
        }
        return bestChild;
    }

    /**
     * @param rootNode a node
     * @return if node has children, child with best UCT value; else node itself
     */
    protected Node selectPromisingBranch(Node rootNode) {
        if (rootNode.getChildren().isEmpty()) {
            return rootNode;
        }
        Node node = UCT.getChildWithBestUCTValue(rootNode);
        //can not be null, because node has children
        return node;
    }

    /**
     * Plays a random game starting from the game situation
     * specified in the node's state.
     * @param node to start the simulation from
     * @return winner of the simulated game
     */
    protected int simulateRandomPlayout(Node node) {
        if (node == null) {
            throw new java.lang.NullPointerException("node was null");
        }
        boolean gameIsOver = ((MCTSState) node.getState()).getGame().gameIsOver();
        if (gameIsOver &&
                ((MCTSState) node.getState()).getGame().getTurn()*(-1) ==
                ((MCTSState) node.getState()).getGame().winner()) {
            ((MCTSState) node.getParent().getState()).setWinScore(Integer.MIN_VALUE);
            //if the last move was made by the player who won,
            //the previous move was indefinitely bad
            return ((MCTSState) node.getState()).getGame().winner();
        } else {
            MCTSState tempState = ((MCTSState) node.getState()).getCopy();
            while (!gameIsOver) {
                tempState.randomPlay();
                gameIsOver = tempState.getGame().gameIsOver();
            }
            return tempState.getGame().winner();
        }
    }

    /**
     * Updates the state's of the nodeToExplore and it's ancestrial nodes
     * according to who was winner
     * @param nodeToExplore
     * @param winner of the simulation that was made starting from nodeToExplore
     */
    protected void backPropagation(Node nodeToExplore, int winner) {
        Node tempNode = nodeToExplore;
        while (tempNode != null) {
            ((MCTSState) tempNode.getState()).incrementVisit();
            //if node's turn is different from who won a game following it,
            //increment winScore
            if (((MCTSState) tempNode.getState()).getGame().getTurn() * (-1) == winner) {
                ((MCTSState) tempNode.getState()).addScore(1);
            }
            tempNode = tempNode.getParent();
        }
    }

    /**
     * Creates all the child nodes (following game situations after one move)
     * for the given node
     * @param node 
     */
    protected void expandNode(Node node) {
        if (node.getChildren().isEmpty()) {
            List<MCTSState> possibleStates = ((MCTSState)node.getState()).getAllPossibleStates();
            for (int i = 0; i < possibleStates.size(); i++) {
                MCTSState state = possibleStates.get(i).getCopy();
                Node newNode = new Node(state);
                newNode.setParent(node);
                node.addChild(newNode);
            }
        }
    }
    
}
