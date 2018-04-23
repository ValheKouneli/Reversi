package reversi.AI.MCTS;


import java.util.Random;
import reversi.AI.Game;
import reversi.AI.Player;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class MCTSbot <MoveType> implements Player <MoveType> {
    
    private final Random random = new Random(System.currentTimeMillis());
    private final MCTShelper<MoveType> MCTShelper;
    private final int timeToThink;
    
    public MCTSbot() {
        timeToThink = 1000;
        MCTShelper = new MCTShelper<>(random);
    }
    
    public MCTSbot(int timeToThink) {
        this.timeToThink = timeToThink;
        MCTShelper = new MCTShelper<>(random);
    }
    
    /**
     * Chooses a move to play in the given game situation
     * based on Monte Carlo Tree Search algorithm.
     * @param game some game situation where it's the MCTSbot's turn
     * @return 
     */
    @Override
    public MoveType getNextMove(Game game) {
        long end = System.currentTimeMillis() + timeToThink;
        //how long to continue before selecting final move

        Tree<MoveType> tree = new Tree<>(new State(game));
        
        while (System.currentTimeMillis() < end) {
            
            Node<MoveType> promisingNode = tree.getRoot();
            
            while (!promisingNode.getChildren().isEmpty()) {
                promisingNode = MCTShelper
                        .selectPromisingBranch(promisingNode);
            }

            int playoutResult;
            Node<MoveType> nodeToExplore;
            
            if (!promisingNode.getState().getGame().gameIsOver()) {
                MCTShelper.expandNode(promisingNode);
                nodeToExplore = MCTShelper.getRandomChildNode(promisingNode);
                playoutResult = MCTShelper.simulateRandomPlayout(nodeToExplore);
            } else {
                nodeToExplore = promisingNode;
                playoutResult = promisingNode.getState().getGame().winner();
            }
            
            MCTShelper.backPropagation(nodeToExplore, playoutResult);
        }
        
        Node<MoveType> winnerNode = MCTShelper.getChildWithMaxScore(tree.getRoot());
        State<MoveType> state = winnerNode.getState();
        MoveType move = state.getLatestMove(); //null pointer exception
        return move;
    }
    
    @Override
    public String name() {
        return "MCTS Bot";
    }
}