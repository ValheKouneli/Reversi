package reversi.application;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import reversi.AI.minimax.MinimaxAI;
import reversi.game.reversi.ReversiEvaluator;

/**
 *
 * @author Valhe Kouneli
 */
public class RecordMoveTimeMinimaxTest {
    
    private RecordMoveTimeMinimax record;
    private RecordMoveTimeMinimax record2;
    private int depth;
    private int estimatedAvgTime;
    String botName;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        depth = 3;
        MinimaxAI bot = new MinimaxAI(new ReversiEvaluator(), depth);
        botName = bot.name();
        record = new RecordMoveTimeMinimax(bot);
        record2 = new RecordMoveTimeMinimax(bot);
        long before = System.currentTimeMillis();
        record2.record();
        estimatedAvgTime = (int) (System.currentTimeMillis() - before ) / 64;
    }
    
    @Test
    public void getMinimaxDepthWorks() {
        assertEquals(depth, record.getMinimaxDepth());
    }
    
    //@Test
    public void avgTimeGivesSomethingSensible() {
        assertEquals(estimatedAvgTime, record2.avgTime(), 10);
        //error margin is system dependant
    }
    
    @Test
    public void toStringWorksForRecordedGame() {
        int avgTime = record2.avgTime();
        String right = botName + " has an average move time of " + 
                    avgTime + " ms.";
        assertEquals(right, record2.toString());
    }
    
    @Test
    public void toStringWorksForNonRecordedGame() {
        String right = "Recording average move time has not yet been done to " + 
                    botName + ".";
        assertEquals(right, record.toString());
    }
    
    @Test
    public void avgTimeThrowsExceptionWhenCalledTooEarly() {
        exception.expect(IllegalStateException.class);
        record.avgTime();
    }
    
}
