package reversi.AI.minimax;

import reversi.game.Game;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import reversi.game.reversi.Reversi;


/**
 *
 * @author Valhe Kouneli
 */
public class MinimaxAITest {
    
    private MinimaxAI bot;
    private Game nim;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        bot = new MinimaxAI(new NimEvaluator(), 1);
        nim = new Nim(4);
    }
    
    @Test
    public void getMovesReturnsNullIfThereAreNoLegalMoves() {
        nim = new Nim(0);
        Object move = bot.getNextMove(nim);
        assertEquals(null, move);
    }
    
    @Test
    public void getNextMovesReturnsTheRightMoveInObviousSituation() {
        Object move = bot.getNextMove(nim);
        assertEquals(3, move);
    }
    
    @Test
    public void nameReturnsTheRightName() {
        assertEquals("MinimaxAI (depth 1) + nim evaluator", bot.name());
    }
    
    @Test
    public void givingWrongKindOfGameToGetNextMoveThrowsIllegalArgumentException() {
        Game reversi = new Reversi();
        exception.expect(IllegalArgumentException.class);
        Object move = bot.getNextMove(reversi);
    }
    
    
    
}
