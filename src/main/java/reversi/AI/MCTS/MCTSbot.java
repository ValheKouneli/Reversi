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
public class MCTSbot implements AI <Game> {
    
    int level;
    static final int WIN_SCORE = 10;
    Random random = new Random(System.currentTimeMillis());
    int opponent;
    int me;
    
    public MCTSbot(int me, int opponent) {
        this.me = me;
        this.opponent = opponent;
    }
    
    /**
     * Chooses a move to play in the given game situation
     * based on Monte Carlo Tree Search algorithm.
     * @param game some game situation where it's the MCTSbot's turn
     */
    @Override
    public void makeNextMove(Game game) {
        long end = System.currentTimeMillis() + 2000;
        //how long to continue before selecting final move

        Tree tree = new Tree(new State(game));
        
        while (System.currentTimeMillis() < end) {
            Node promisingNode = MCTShelper.selectPromisingLeaf(tree.getRoot());
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
        Pair move = winnerNode.getState().getLatestMove();
        game.move(move);
    }
}