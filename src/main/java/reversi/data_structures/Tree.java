package reversi.data_structures;


/**
 * Represents a tree structure
 * @author Valhe Kouneli
 */
public class Tree  {
    
    Node root;
    
    /**
     * Creates a new Tree
     * @param node is this tree's root node
     */
    public Tree(Node node) {
        root = node;
    }
    
    public void setRoot(Node root) {
        this.root = root;
    }
    
    public Node getRoot() {
        return root;
    }
    
}
