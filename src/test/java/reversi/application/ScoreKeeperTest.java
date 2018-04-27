package reversi.application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import reversi.AI.MCTS.MCTSBot;
import reversi.AI.minimax.MinimaxAI;
import reversi.game.reversi.ReversiEvaluator;

/**
 *
 * @author Valhe Kouneli
 */
public class ScoreKeeperTest {
    
    private ScoreKeeper scores;
    private ScoreKeeper scores2;
    
    @Before
    public void setUp() {
        MinimaxAI minimax = new MinimaxAI(new ReversiEvaluator(), 3);
        MCTSBot mcts = new MCTSBot(20);
        scores = new ScoreKeeper(minimax, mcts);
        scores2 = new ScoreKeeper(minimax, mcts);
        scores2.playSwitchingColors(true);
    }
    

    
}
