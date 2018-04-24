package reversi.AI.MCTS;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class Tree <MoveType> {
    
    Node<MoveType> root;
    
    public Tree() {
        root = new Node(new State<>());
    }
    
    public Tree(State state) {
        root = new Node(state);
    }
    
    public void setRoot(Node root) {
        this.root = root;
    }
    
    public Node getRoot() {
        return root;
    }
    
}
