package reversi.data_structures;

/**
 * Represents a node in a Tree
 * @author Valhe Kouneli
 */
public class Node {
    
    private Object state; //information about this node
    private Node parent;
    private final List<Node> children;
    
    public Node(Object state) {
        this.state = state;
        children = new List<>();
    }
    
    public void setState(Object state) {
        this.state = state;
    }
    
    public Object getState() {
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
    
}
