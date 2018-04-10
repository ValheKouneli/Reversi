/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;

/**
 *
 * @author Valhe Kouneli
 */
public class Tree {
    
    Node root;
    
    public Tree() {
        root = new Node();
    }
    
    public void setRoot(Node root) {
        this.root = root;
    }
    
    public Node getRoot() {
        return root;
    }
    
}
