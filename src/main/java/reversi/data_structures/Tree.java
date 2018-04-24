package reversi.data_structures;


/**
 *
 * @author Valhe Kouneli
 */
public class Tree  {
    
    Node root;
    
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
