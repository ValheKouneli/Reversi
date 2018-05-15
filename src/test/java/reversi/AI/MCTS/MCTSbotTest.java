package reversi.AI.MCTS;
import testData.TestData;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import reversi.game.reversi.BoardFactory;
import reversi.game.reversi.Reversi;
/**
 *
 * @author Valhe Kouneli
 */
public class MCTSbotTest {
    
    MCTSBot bot;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        bot = new MCTSBot(2000);
    }
    
    @Test
    public void botPlaysSomethingEvenWhenItIsItsLastTurnAndItIsLosingAnyway() {
        Object move = bot.getNextMove(TestData.getGame1());
        assertNotNull(move);
    }
    
    @Test
    public void getNextMovesReturnsZeroInSituationWithNoLegalMoves() {
        Reversi game = new Reversi();
        game.setBoard(BoardFactory.makeBoard(   "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |●|●|●|●|●|●|●|●|\n" +
                                                "1 |●|●|●|●|●|●|●|●|\n" +
                                                "2 |●|●|●|●|●|●|●|●|\n" +
                                                "3 |●|●|●|●|●|●|●| |\n" +
                                                "4 |●|●|●|●|●|●| | |\n" +
                                                "5 |●|●|●|●|●|●|○| |\n" +
                                                "6 |●|●|●|●|●|●|●| |\n" +
                                                "7 |●|●|●|●|●|●|●|●|\n"
                                                                        ));
        //it's white's turn
        Object move = bot.getNextMove(game);
        assertNull(move);
    }
    
    @Test
    public void botDoesNotTakeSignificantlyMoreTimeToThinkThanAllowed() {
        bot = new MCTSBot(500);
        Reversi game = new Reversi();
        long timeNow = System.currentTimeMillis();
        bot.getNextMove(game);
        long spentTime = System.currentTimeMillis() - timeNow;
        assertEquals(true, spentTime < 505);
        /* 
        Notice that this measure might need adjusting
        */
    }
    
    @Test
    public void nameReturnsTheRightName() {
        assertEquals("MCTS Bot, 2000 ms time/move", bot.name());
    }
    
    @Test
    public void botDoesNotCrashEvenIfItIsGivenSuperLittleTime() {
        bot = new MCTSBot(1);
        Reversi game = new Reversi();
        //should not produce exception
        bot.getNextMove(game);
    }
}
