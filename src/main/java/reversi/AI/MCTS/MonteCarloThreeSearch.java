/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;

import reversi.AI.Game;

/**
 *
 * @author Valhe Kouneli
 */
public class MonteCarloThreeSearch {
    
    int level;
    
    public Game findNextMove(Game game) {
        long end = System.currentTimeMillis() + 1000;
        //how long to continue before selecting final move
        
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    private Node getRandomChildNode() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    private Node getChildWithMaxScore() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    private Node selectPromisingNode() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    private int simulateRandomPlayout(Node node) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    private void backPropogation(Node nodeToExplore, int playoutResult) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    private void expandNode(Node node) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}