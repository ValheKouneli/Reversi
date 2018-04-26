package reversi.AI.minimax.timelimited;

import reversi.game.Game;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import reversi.AI.minimax.Evaluator;
import reversi.game.Player;
import reversi.data_structures.Node;
import reversi.data_structures.Tree;

/**
 *
 * @author Valhe Kouneli
 */
public class MinimaxAITimeLimited implements Player {
    
    private final Evaluator eval;
    private int timeLimit;
    private final MinimaxTLHelper helper;
    private final Random random;
    
    public MinimaxAITimeLimited(Evaluator eval) {
        timeLimit = 1000;
        this.eval = eval;
        helper = new MinimaxTLHelper();
        random = new Random(System.currentTimeMillis());
    }
    
    public MinimaxAITimeLimited(int timeLimit, Evaluator eval) {
        this.timeLimit = timeLimit;
        this.eval = eval;
        helper = new MinimaxTLHelper();
        random = new Random(System.currentTimeMillis());
    }
    
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
    
    public long getTimeLimit() {
        return timeLimit;
    }
    /**
     *
     * @param game
     * @return
     */
    @Override
    public Object getNextMove(Game game) {
        if (game.getMoves().size() == 0) {
            return null;
        }
        
        long timeNow = System.currentTimeMillis();
        
        MinimaxState rootState = new MinimaxState(game);
        Node rootNode = new Node(rootState);
        Tree tree = new Tree(rootNode);
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(rootNode);
        Node nodeToProcess;

        helper.branchOutAndEvaluate(rootNode, eval, queue);
        
        while (System.currentTimeMillis() < timeNow + timeLimit) {
            
            nodeToProcess = queue.poll();
            if (nodeToProcess == null) {
                break;
            }
            helper.branchOutAndEvaluate(rootNode, eval, queue);
        }
        
        int bestValue = game.getTurn() == 1 ?
                Integer.MIN_VALUE : Integer.MAX_VALUE;
        Object bestMove = null;
        
        for (int i=0; i<rootNode.getChildren().size(); i++) {
            MinimaxState childState = (MinimaxState) rootNode.getChildren()
                    .get(i).getState();
            int value = childState.getValue();
            if (game.getTurn() == 1 ? value > bestValue : value < bestValue) {
                bestValue = value;
                bestMove = childState.getLatestMove();
            }
        }
        
        if (bestMove == null) {
            Node randomNode = rootNode.getChildren()
                    .get(random.nextInt(rootNode.getChildren().size()));
            bestMove =  ((MinimaxState) randomNode.getState())
                    .getLatestMove();
        }
        
        
        return bestMove;
    }

    @Override
    public String name() {
        return "MinimaxAI, " + timeLimit + " ms time/move";
    }
    
}
