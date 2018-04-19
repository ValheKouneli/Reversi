package reversi.AI.MCTS;

import java.util.Random;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class MCTShelper <MoveType> {
    
    private final Random random;
    
    public MCTShelper(Random random) {
        this.random = random;
    }
    
    public MCTShelper() {
        this.random = new Random(System.currentTimeMillis());
    }
    
    protected Node<MoveType> getRandomChildNode(Node<MoveType> node) {
        int size = node.getChildren().size();
        if (size > 0) {
            int i = random.nextInt(size);
            return node.getChildren().get(i);
        } else {
            return null;
        }
    }

    protected Node<MoveType> getChildWithMaxScore(Node<MoveType> node) {
        List<Node<MoveType>> children = node.getChildren();
        double bestScore = Integer.MIN_VALUE;
        Node bestChild = null;
        Node child;
        for (int i = 0; i < children.size(); i++) {
            child = children.get(i);
            if (child.getState().getWinScore() > bestScore) {
                bestScore = child.getState().getWinScore();
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
    protected Node<MoveType> selectPromisingBranch(Node<MoveType> rootNode) {
        Node node = rootNode;
        if (!node.getChildren().isEmpty()) {
            node = UCT.getChildWithBestUCTValue(node);
        }
        return node;
    }

    protected int simulateRandomPlayout(Node<MoveType> node, int opponent) {
        boolean gameIsOver = node.getState().getGame().gameIsOver();
        if (gameIsOver &&
                node.getState().getGame().getTurn() != opponent &&
                node.getState().getGame().winner() == opponent) {
            node.getParent().getState().setWinScore(Integer.MIN_VALUE);
            return opponent;
        } else {
            State tempState = node.getState().getCopy();
            while (!gameIsOver) {
                tempState.randomPlay();
                gameIsOver = tempState.getGame().gameIsOver();
            }
            return tempState.getGame().winner();
        }
    }

    protected void backPropagation(Node<MoveType> nodeToExplore, int winner) {
        Node tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.getState().incrementVisit();
            //if node's turn is different from who won a game following it,
            //increment winScore
            if (tempNode.getState().getGame().getTurn() * (-1) == winner) {
                tempNode.getState().addScore(1);
            }
            tempNode = tempNode.getParent();
        }
    }

    protected void expandNode(Node<MoveType> node) {
        if (node.getChildren().isEmpty()) {
            List<State<MoveType>> possibleStates = node.getState().getAllPossibleStates();
            for (int i = 0; i < possibleStates.size(); i++) {
                State state = possibleStates.get(i).getCopy();
                Node newNode = new Node(state);
                newNode.setParent(node);
                node.addChild(newNode);
            }
        }
    }
    
}
