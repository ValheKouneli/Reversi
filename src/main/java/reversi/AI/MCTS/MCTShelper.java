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
public class MCTShelper <MoveType> {
    
    public MCTShelper() {}
    
    protected Node<MoveType> getRandomChildNode(Node<MoveType> node, Random random) {
        int size = node.getChildren().size();
        if (size > 0) {
            int i = random.nextInt(size);
            return node.getChildren().get(i);
        } else {
            return null;
        }
    }

    protected Node<MoveType> getChildWithMaxScore(Node<MoveType> node, Random random) {
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
            return getRandomChildNode(node, random);
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

    protected void backPropagation(Node<MoveType> nodeToExplore, int playerNo, int WIN_SCORE) {
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
