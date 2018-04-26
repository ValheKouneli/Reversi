package reversi.AI.MCTS;

import java.util.Random;
import reversi.game.Game;
import reversi.data_structures.List;

/**
 * Stores information about nodes used in Monte Carlo Tree Search
 * in class MCTSbot.
 *
 * @author Valhe Kouneli
 */
public class MCTSState {
    private final Game game;
    private int visitCount;
    private int winScore;
    private Object latestMove;
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    
    public MCTSState() {
        latestMove = null;
        game = null;
        visitCount = 0;
        winScore = 0;
    }
    public MCTSState(Game game) {
        latestMove = null;
        this.game = game;
        visitCount = 0;
        winScore = 0;
    }
    
    public MCTSState(Game game, Object latestMove) {
        this.latestMove = latestMove;
        this.game = game;
        visitCount = 0;
        winScore = 0;
    }
    
    public void incrementVisit() {
        visitCount++;
    }
    
    public int getVisitCount() {
        return visitCount;
    }
    
    public Object getLatestMove() {
        return latestMove;
    }
    
    public void setLatestMove(Object move) {
        latestMove = move;
    }
    public void addScore(int score) {
        this.winScore += score;
    }
    
    public int getWinScore() {
        return winScore;
    }
    
    public void setWinScore(int winScore) {
        this.winScore = winScore;
    }
    
    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }
    
    public Game getGame() {
        return game;
    }
    
    public List<MCTSState> getAllPossibleStates() {
        List<MCTSState> nextStates = new List<>();
        List<Object> moves = game.getMoves();
        if (moves.isEmpty()) {
            Game copy = game.getCopy();
            copy.move(null);
            MCTSState state = new MCTSState(copy, null);
            nextStates.add(state);
        } else {
            for (int i=0; i<moves.size(); i++) {
                Game copy = game.getCopy();
                copy.move(moves.get(i));
                MCTSState state = new MCTSState(copy, moves.get(i));
                nextStates.add(state);
            }
        }
        return nextStates;
    }
    
    public void randomPlay() {
        List<Object> moves = game.getMoves();
        if (moves.size() == 0) {
            game.move(null);
        } else {       
            int i = RANDOM.nextInt(moves.size());
            game.move(moves.get(i));
        }
    }
    
    public MCTSState getCopy() {
        MCTSState copy = new MCTSState(game.getCopy());
        copy.setVisitCount(visitCount);
        copy.setWinScore(winScore);
        copy.setLatestMove(latestMove);
        return copy;
    }
    
}
