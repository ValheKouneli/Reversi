/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;

import java.util.Random;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTShelper {
    
    private MCTShelper() {}
    
    protected static Node getRandomChildNode(Node node, Random random) {
        int size = node.getChildren().size();
        if (size > 0) {
            int i = random.nextInt(size);
            return node.getChildren().get(i);
        } else {
            return null;
        }
    }

    protected static Node getChildWithMaxScore(Node node) {
        List<Node> children = node.getChildren();
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
        return bestChild;
    }

    /**
     * @param rootNode a node
     * @return if node has children, child with best UCT value; else node itself
     */
    protected static Node selectPromisingLeaf(Node rootNode) {
        Node node = rootNode;
        if (!node.getChildren().isEmpty()) {
            node = UCT.getChildWithBestUCTValue(node);
        }
        return node;
    }

    protected static int simulateRandomPlayout(Node node, int opponent) {
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

    protected static void backPropagation(Node nodeToExplore, int playerNo, int WIN_SCORE) {
        Node tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.getState().incrementVisit();
            //if node's turn is different from who won a game following it,
            //increment winScore
            if (tempNode.getState().getGame().getTurn() * (-1) == playerNo) {
                tempNode.getState().addScore(WIN_SCORE);
            }
            tempNode = tempNode.getParent();
        }
    }

    protected static void expandNode(Node node) {
        List<State> possibleStates = node.getState().getAllPossibleStates();
        State state;
        Node newNode;
        for (int i = 0; i < possibleStates.size(); i++) {
            state = possibleStates.get(i);
            newNode = new Node(state);
            newNode.setParent(node);
            node.getChildren().add(newNode);
        }
    }
    
}
