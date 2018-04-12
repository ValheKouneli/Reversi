/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;

import java.util.Random;
import reversi.AI.Game;
import reversi.data_structures.List;
import reversi.data_structures.Pair;

/**
 *
 * @author Valhe Kouneli
 */
public class State {
    private final Game game;
    private int visitCount;
    private int winScore;
    private Pair latestMove;
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
    
    public State(Game game, Pair latestMove) {
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
    
    public Pair getLatestMove() {
        return latestMove;
    }
    
    public void setLatestMove(Pair move) {
        latestMove = move;
    }
    public void addScore(int score) {
        this.winScore += score;
    }
    
    public double getWinScore() {
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
    
    public List<State> getAllPossibleStates() {
        List<State> nextStates = new List<>();
        List<Pair> moves = game.getMoves();
        State state;
        Game copy;
        for (int i=0; i<moves.size(); i++) {
            copy = game.getCopy();
            copy.move(moves.get(i));
            state = new State(copy, moves.get(i));
            nextStates.add(state);
        }
        return nextStates;
    }
    
    public void randomPlay() {
        List<Pair> moves = game.getMoves();
        int i = RANDOM.nextInt(moves.size());
        game.move(moves.get(i));
    }
    
    public State getCopy() {
        State copy = new State(game.getCopy());
        copy.setVisitCount(visitCount);
        copy.setWinScore(winScore);
        copy.setLatestMove(latestMove);
        return copy;
    }
    
}
