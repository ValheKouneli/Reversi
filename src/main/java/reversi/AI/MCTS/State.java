/*
 * Implemented following the tutorial on http://www.baeldung.com/java-monte-carlo-tree-search
 */
package reversi.AI.MCTS;

import java.util.List;
import reversi.AI.Game;

/**
 *
 * @author Valhe Kouneli
 */
public class State {
    Game game;
    int visitCount;
    double winScore;
    
    public State(Game game) {
        this.game = game;
        visitCount = 0;
        winScore = 0;
    }
    
    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }
    
    public int getVisitCount() {
        return visitCount;
    }
    
    public void setWinScore(double winScore) {
        this.winScore = winScore;
    }
    
    public double getWinScore() {
        return winScore;
    }
    
    public List<State> getAllPossibleStates() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    public void randomPlay() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
}
