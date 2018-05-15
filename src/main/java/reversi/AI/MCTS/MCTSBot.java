package reversi.AI.MCTS;


import reversi.data_structures.Node;
import reversi.data_structures.Tree;
import java.util.Random;
import reversi.game.Game;
import reversi.game.Player;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTSBot implements Player {
    
    private final Random random = new Random(System.currentTimeMillis());
    private final MCTShelper MCTShelper;
    private final int timeToThink;
    
    public MCTSBot() {
        timeToThink = 1000;
        MCTShelper = new MCTShelper(random);
    }
    
    public MCTSBot(int timeToThink) {
        this.timeToThink = timeToThink;
        MCTShelper = new MCTShelper(random);
    }
    
    /**
     * Chooses a move to play in the given game situation
     * based on Monte Carlo Tree Search algorithm.
     * @param game some game situation where it's the MCTSBot's turn
     * @return 
     */
    @Override
    public Object getNextMove(Game game) {
        if (game.getMoves().isEmpty()) {
            return null;
        }
        
        long end = System.currentTimeMillis() + timeToThink;
        //how long to continue before selecting final move
        MCTSState rootState = new MCTSState(game);
        Node root = new Node(rootState);
        Tree tree = new Tree(root);
        boolean whileEntered = false;
        
        while (System.currentTimeMillis() < end) {
            whileEntered = true;
            
            Node promisingNode = tree.getRoot();
            
            while (!promisingNode.getChildren().isEmpty()) {
                promisingNode = MCTShelper
                        .selectPromisingBranch(promisingNode);
            }

            int playoutResult;
            Node nodeToExplore;
            
            if (!((MCTSState) promisingNode.getState()).getGame().gameIsOver()) {
                MCTShelper.expandNode(promisingNode);
                nodeToExplore = MCTShelper.getRandomChildNode(promisingNode);
                playoutResult = MCTShelper.simulateRandomPlayout(nodeToExplore);
            } else {
                nodeToExplore = promisingNode;
                playoutResult = ((MCTSState) promisingNode.getState()).getGame().winner();
            }
            
            MCTShelper.backPropagation(nodeToExplore, playoutResult);
        }
        if (!whileEntered) { //in case of ridicilously short timeToThink times
            MCTShelper.expandNode(root);
        }
        Node winnerNode = MCTShelper.getChildWithMaxScore(tree.getRoot());
        MCTSState state = (MCTSState) winnerNode.getState();
        Object move = state.getLatestMove();
        return move;
    }
    
    @Override
    public String name() {
        return "MCTS Bot, " + timeToThink + " ms time/move";
    }
}