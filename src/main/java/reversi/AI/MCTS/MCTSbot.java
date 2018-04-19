/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;


import java.util.Random;
import reversi.AI.AI;
import reversi.AI.Game;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class MCTSbot <MoveType> implements AI <MoveType> {
    
    private static final int WIN_SCORE = 10;
    private final Random random = new Random(System.currentTimeMillis());
    private final int opponent;
    private final MCTShelper<MoveType> MCTShelper;
    private final int timeToThink;
    
    public MCTSbot(int me, int opponent) {
        this.opponent = opponent;
        timeToThink = 2000;
        MCTShelper = new MCTShelper<>();
    }
    
    public MCTSbot(int me, int opponent, int timeToThink) {
        this.timeToThink = timeToThink;
        this.opponent = opponent;
        MCTShelper = new MCTShelper<>();
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
                nodeToExplore = MCTShelper.getRandomChildNode(promisingNode, random);
                playoutResult = MCTShelper.simulateRandomPlayout(nodeToExplore, opponent);
            } else {
                nodeToExplore = promisingNode;
                playoutResult = promisingNode.getState().getGame().winner();
            }
            
            MCTShelper.backPropagation(nodeToExplore, playoutResult, WIN_SCORE);
        }
        
        Node<MoveType> winnerNode = MCTShelper.getChildWithMaxScore(tree.getRoot(), random);
        State<MoveType> state = winnerNode.getState();
        MoveType move = state.getLatestMove(); //null pointer exception
        return move;
    }
}