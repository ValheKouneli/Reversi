/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;

import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class Node {
    
    private State state;
    private Node parent;
    private List<Node> children;
    
    public Node() {}
    
    public void setState(State state) {
        this.state = state;
    }
    
    public State getState() {
        return state;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public Node getParent() {
        return parent;
    }
    
    public void addChild(Node child) {
        children.add(child);
    }
    
    public List<Node> getChildren() {
        return children;
    }
    
    public Node getRandomChildNode() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    public Node getChildWithMaxScore() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    public Node selectPromisingNode() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
}
