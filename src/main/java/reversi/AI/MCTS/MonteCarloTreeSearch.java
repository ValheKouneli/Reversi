/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;


import java.util.Random;
import reversi.AI.Game;
import reversi.data_structures.List;
import reversi.data_structures.Pair;

/**
 *
 * @author Valhe Kouneli
 */
public class MonteCarloTreeSearch {
    
    int level;
    static final int WIN_SCORE = 10;
    Random random = new Random(System.currentTimeMillis());
    int opponent;
    int me;
    
    public MonteCarloTreeSearch(int me, int opponent) {
        this.me = me;
        this.opponent = opponent;
    }
    
    public void makeNextMove(Game game) {
        long end = System.currentTimeMillis() + 1000;
        //how long to continue before selecting final move

        Tree tree = new Tree(new State(game));
        
        while (System.currentTimeMillis() < end) {
            Node promisingNode = selectPromisingLeaf(tree.getRoot());
            if (!promisingNode.getState().getGame().gameIsOver()) {
                expandNode(promisingNode);
            }
            Node nodeToExplore = promisingNode;
            if (!promisingNode.getChildren().isEmpty()) {
                nodeToExplore = getRandomChildNode(promisingNode);
            }
            int playoutResult = simulateRandomPlayout(nodeToExplore);
            backPropagation(nodeToExplore, playoutResult);
        }
        
        Node winnerNode = getChildWithMaxScore(tree.getRoot());
        Pair move = winnerNode.getState().getLatestMove();
        game.move(move);
    }
    
    private Node getRandomChildNode(Node node) {
        int size = node.getChildren().size();
        if (size > 0) {
            int i = random.nextInt(size);
            return node.getChildren().get(i);
        } else {
            return null;
        }
    }
    
    private Node getChildWithMaxScore(Node node) {
        List<Node> children = node.getChildren();
        double bestScore = Integer.MIN_VALUE;
        Node bestChild = null;
        Node child;
        for (int i=0; i<children.size(); i++) {
            child = children.get(i);
            if (child.getState().getWinScore() > bestScore) {
                bestScore = child.getState().getWinScore();
                bestChild = child;
            }
        }
        return bestChild;
    }
    
    /**
     * @param rootNode a node
     * @return if node has children, child with best UCT value; else node itself
     */
    private Node selectPromisingLeaf(Node rootNode) {
        Node node = rootNode;
        if (!node.getChildren().isEmpty()) {
            node = UCT.getChildWithBestUCTValue(node);
        }
        return node;
    }
    
    private int simulateRandomPlayout(Node node) {
        Node tempNode = node.getCopyWithoutChildren();
        State tempState = tempNode.getState();
        boolean gameIsOver = tempState.getGame().gameIsOver();
        if (gameIsOver && tempState.getGame().winner() == opponent) {
            tempNode.getParent().getState().setWinScore(Integer.MIN_VALUE);
            return opponent;
        }
        while (!gameIsOver) {
            tempState.randomPlay();
            gameIsOver = tempState.getGame().gameIsOver();
        }
        return tempState.getGame().winner();
    }
    
    private void backPropagation(Node nodeToExplore, int playerNo) {
        Node tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.getState().incrementVisit();
            //if node's turn is different from who won a game following it,
            //increment winScore
            if (tempNode.getState().getGame().getTurn()*(-1) == playerNo) {
                tempNode.getState().addScore(WIN_SCORE);
            }
            tempNode = tempNode.getParent();
        }
    }
    
    private void expandNode(Node node) {
        List<State> possibleStates = node.getState().getAllPossibleStates();
        State state;
        Node newNode;
        for (int i=0; i<possibleStates.size(); i++) {
            state = possibleStates.get(i);
            newNode = new Node(state);
            newNode.setParent(node);
            node.getChildren().add(newNode);
        }
    }
    
}
