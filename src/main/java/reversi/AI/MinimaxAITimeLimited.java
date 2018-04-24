/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import reversi.data_structures.Node;
import reversi.data_structures.Tree;

/**
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class MinimaxAITimeLimited <MoveType> implements Player {
    
    private final Evaluator<MoveType> eval;
    private int timeLimit;
    private final MinimaxTLHelper<MoveType> helper;
    private final Random random;
    
    public MinimaxAITimeLimited(int timeLimit, Evaluator<MoveType> eval) {
        this.timeLimit = timeLimit;
        this.eval = eval;
        helper = new MinimaxTLHelper<>();
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
    public MoveType getNextMove(Game game) {
        long timeNow = System.currentTimeMillis();
        
        MinimaxState rootState = new MinimaxState(game);
        Node rootNode = new Node(rootState);
        Tree tree = new Tree(rootNode);
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(rootNode);
        Node nodeProcessed;

        helper.branchOutAndEvaluate(rootNode, eval, queue);
        
        while (System.currentTimeMillis() < timeNow + timeLimit) {
            nodeProcessed = queue.remove();
            helper.branchOutAndEvaluate(rootNode, eval, queue);
        }
        
        int bestValue = game.getTurn() == 1 ?
                Integer.MIN_VALUE : Integer.MAX_VALUE;
        MoveType bestMove = null;
        
        for (int i=0; i<rootNode.getChildren().size(); i++) {
            MinimaxState childState = (MinimaxState) rootNode.getChildren()
                    .get(i).getState();
            int value = childState.getValue();
            if (game.getTurn() == 1 ? value > bestValue : value < bestValue) {
                bestValue = value;
                bestMove = (MoveType) childState.getLatestMove();
            }
        }
        
        if (bestMove == null) {
            Node randomNode = rootNode.getChildren()
                    .get(random.nextInt(rootNode.getChildren().size()));
            bestMove = (MoveType) ((MinimaxState) randomNode.getState())
                    .getLatestMove();
        }
        
        
        return bestMove;
    }

    @Override
    public String name() {
        return "MinimaxAI, " + timeLimit + " ms time/move";
    }
    
}
