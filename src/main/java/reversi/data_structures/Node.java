/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.data_structures;

/**
 *
 * @author Valhe Kouneli
 */
public class Node {
    
    private Node parent;
    private List<Node> children;
    
    public Node() {}
    
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
    
    public Node selectPromisingNode() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
}
