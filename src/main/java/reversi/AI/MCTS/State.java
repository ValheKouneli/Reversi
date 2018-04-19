/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;

import java.util.Random;
import reversi.AI.Game;
import reversi.data_structures.List;

/**
 * Stores information about nodes used in Monte Carlo Tree Search
 * in class MCTSbot.
 *
 * @author Valhe Kouneli
 * @param <MoveType>
 */
public class State <MoveType> {
    private final Game game;
    private int visitCount;
    private int winScore;
    private MoveType latestMove;
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    
    public State() {
        latestMove = null;
        game = null;
        visitCount = 0;
        winScore = 0;
    }
    public State(Game game) {
        latestMove = null;
        this.game = game;
        visitCount = 0;
        winScore = 0;
    }
    
    public State(Game game, MoveType latestMove) {
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
    
    public MoveType getLatestMove() {
        return latestMove;
    }
    
    public void setLatestMove(MoveType move) {
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
    
    public List<State<MoveType>> getAllPossibleStates() {
        List<State<MoveType>> nextStates = new List<>();
        List<MoveType> moves = game.getMoves();
        for (int i=0; i<moves.size(); i++) {
            Game copy = game.getCopy();
            copy.move(moves.get(i));
            State<MoveType> state = new State<>(copy, moves.get(i));
            nextStates.add(state);
        }
        return nextStates;
    }
    
    public void randomPlay() {
        List<MoveType> moves = game.getMoves();
        int i = RANDOM.nextInt(moves.size());
        game.move(moves.get(i));
    }
    
    public State<MoveType> getCopy() {
        State<MoveType> copy = new State<>(game.getCopy());
        copy.setVisitCount(visitCount);
        copy.setWinScore(winScore);
        copy.setLatestMove(latestMove);
        return copy;
    }
    
}
