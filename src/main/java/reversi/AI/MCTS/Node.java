/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;

import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class Node <MoveType> {
    
    private State<MoveType> state; //information about this node
    private Node<MoveType> parent;
    private final List<Node<MoveType>> children;
    
    public Node(State<MoveType> state) {
        this.state = state;
        children = new List<>();
    }
    
    public void setState(State<MoveType> state) {
        this.state = state;
    }
    
    public State<MoveType> getState() {
        return state;
    }
    
    public void setParent(Node<MoveType> parent) {
        this.parent = parent;
    }
    
    public Node getParent() {
        return parent;
    }
    
    public void addChild(Node<MoveType> child) {
        children.add(child);
    }
    
    public List<Node<MoveType>> getChildren() {
        return children;
    }
    
}
