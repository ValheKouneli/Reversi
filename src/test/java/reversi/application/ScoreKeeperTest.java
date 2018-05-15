package reversi.application;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
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
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        MinimaxAI minimax = new MinimaxAI(new ReversiEvaluator(), 3);
        MCTSBot mcts = new MCTSBot(20);
        /*
        * Very low search depth and very little thinking time is set
        * so that the tests would not take too long
        */
        scores = new ScoreKeeper(minimax, mcts);
        scores2 = new ScoreKeeper(minimax, mcts);
        scores2.playSwitchingColors(false);
    }
    
    @Test
    public void askingAvgTimePerMoveForMCTSBeforeAnyMatchesArePlayedThrowsError() {
        exception.expect(IllegalStateException.class);
        scores.getTimePerMoveMCTS();
    }
    
    @Test
    public void askingAvgTimePerMoveForMinimaxBeforeAnyMatchesArePlayedThrowsError() {
        exception.expect(IllegalStateException.class);
        scores.getTimePerMoveMinimax();
    }
    
    @Test
    public void avgTimePerMoveGivesSomethingSensible() {
        assertEquals(scores2.getTimePerMoveMCTS(), 20, 10);
        assertEquals(scores2.getTimePerMoveMinimax(), 20, 10);
        /*
        * very big error margin should be allowed
        */
    }
    
    @Test
    public void playSwitchingColorsWithParameterTruePrintsSomething() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        scores.playSwitchingColors(true);
        assertEquals(true, outContent.toString().length() != 0);
    }
    
    @Test
    public void playSwitchingColorsWithParameterFalseDoesNotPrintAnything() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        scores.playSwitchingColors(false);
        assertEquals(true, outContent.toString().length() == 0);
    }
    
    @Test
    public void toStringWorks() {
        String pattern = "Victories out of \\d+ matches:\n" +
                ".* : \\d+%\n" +
                ".* : \\d+%\n";
        assertEquals(true, Pattern.matches(pattern, scores2.toString()));
    }
    

    
}
