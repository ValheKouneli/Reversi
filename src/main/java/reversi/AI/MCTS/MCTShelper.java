package reversi.AI.MCTS;

import reversi.data_structures.Node;
import java.util.Random;
import reversi.data_structures.List;

/**
 *
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
    
    protected Node getRandomChildNode(Node node) {
        int size = node.getChildren().size();
        if (size > 0) {
            int i = random.nextInt(size);
            return node.getChildren().get(i);
        } else {
            throw new java.lang.IllegalArgumentException(
                    "random child node asked from a node without childen");
        }
    }

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
        }
        return bestChild;
    }

    /**
     * @param rootNode a node
     * @return if node has children, child with best UCT value; else node itself
     */
    protected Node selectPromisingBranch(Node rootNode) {
        Node node = rootNode;
        if (!node.getChildren().isEmpty()) {
            node = UCT.getChildWithBestUCTValue(node);
        }
        return node;
    }

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
