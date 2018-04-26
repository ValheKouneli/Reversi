package reversi.AI.minimax.timelimited;

import reversi.game.Game;
import java.util.Queue;
import reversi.AI.minimax.Evaluator;
import reversi.data_structures.List;
import reversi.data_structures.Node;

/**
 *
 * @author Valhe Kouneli
 */
public class MinimaxTLHelper {
    
    public MinimaxTLHelper() {}
    
    public void branchOutAndEvaluate(Node node, Evaluator eval, Queue queue) {
        MinimaxState state = (MinimaxState) node.getState();
        Game game = state.getGame();
        int turn = game.getTurn();
        if (game.gameIsOver()) {
            if (game.winner() == 1) {
                state.setValue(Integer.MAX_VALUE);
            } else if (game.winner() == -1) {
                state.setValue(Integer.MIN_VALUE);
            } //default value is 0 anyway
            return;
        }
        List<Object> availableMoves = game.getMoves();
        
        int bestValue = turn == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int valueAfterMove;
        
        for (int i=0; i<availableMoves.size(); i++) {
            Object move = availableMoves.get(i);
            Game copy = game.getCopy();
            copy.move(move);

            MinimaxState childState = new MinimaxState(copy, move);
            Node child = new Node(childState);
            child.setParent(node);
            node.addChild(child);
            queue.add(child);
            
            valueAfterMove = eval.eval(copy);
            if (turn == 1 ?
                    valueAfterMove > bestValue : valueAfterMove < bestValue) {
                bestValue = valueAfterMove;
            }
            childState.setValue(valueAfterMove);
        }
        state.setValue(bestValue);
        backPropagate(node, bestValue, turn);
    }
    
    protected void backPropagate(Node node, int value, int turn) {
        turn *= -1;
        Node parent = node.getParent();
        if (parent == null) {
            return;
        }
        MinimaxState parentState = (MinimaxState) parent.getState();
        int parentValue = parentState.getValue();
        
        if (turn == 1 ? value > parentValue : value < parentValue) {
            parentState.setValue(value);
            backPropagate(parent, value, turn);
        }
    }
    
    
    
}
