/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;

import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class Node {
    
    private State state; //information about this node
    private Node parent;
    private final List<Node> children;
    
    public Node(State state) {
        this.state = state;
        children = new List<>();
    }
    
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
    
    public Node getCopyWithoutChildren() {
        Node copy = new Node(state.getCopy());
        copy.setParent(parent);
        return copy;
    }
    
}
