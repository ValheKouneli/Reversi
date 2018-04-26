package reversi.AI.MCTS;
import testData.TestData;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Valhe Kouneli
 */
public class MCTSbotTest {
    
    MCTSbot bot;
    
    @Before
    public void setUp() {
        bot = new MCTSbot(2000);
    }
    
    @Test
    public void botPlaysSomethingEvenWhenItIsItsLastTurnAndItIsLosingAnyway() {
        Object move = bot.getNextMove(TestData.getGame1());
        assertNotNull(move);
    }
}
