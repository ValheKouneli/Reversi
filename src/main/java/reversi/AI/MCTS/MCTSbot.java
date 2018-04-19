/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;


import java.util.Random;
import reversi.AI.AI;
import reversi.AI.Game;
import reversi.data_structures.List;
import reversi.data_structures.Pair;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTSbot <MoveType> implements AI <MoveType> {
    
    private static final int WIN_SCORE = 10;
    private final Random random = new Random(System.currentTimeMillis());
    private final int opponent;
    private final int timeToThink;
    
    public MCTSbot(int me, int opponent) {
        this.opponent = opponent;
        timeToThink = 2000;
    }
    
    public MCTSbot(int me, int opponent, int timeToThink) {
        this.timeToThink = timeToThink;
        this.opponent = opponent;
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

        Tree tree = new Tree(new State(game));
        
        while (System.currentTimeMillis() < end) {
            Node promisingNode = MCTShelper.selectPromisingBranch(tree.getRoot());
            if (!promisingNode.getState().getGame().gameIsOver()) {
                MCTShelper.expandNode(promisingNode);
            }
            Node nodeToExplore = promisingNode;
            if (!promisingNode.getChildren().isEmpty()) {
                nodeToExplore = MCTShelper.getRandomChildNode(promisingNode, random);
            }
            int playoutResult = MCTShelper.simulateRandomPlayout(nodeToExplore, opponent);
            MCTShelper.backPropagation(nodeToExplore, playoutResult, WIN_SCORE);
        }
        
        Node winnerNode = MCTShelper.getChildWithMaxScore(tree.getRoot());
        Object move = winnerNode.getState().getLatestMove(); //null pointer exception
        return (MoveType) move;
    }
}