package reversi.game;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import reversi.AI.MCTS.MCTSBot;
import reversi.game.reversi.Reversi;

/**
 *
 * @author Valhe Kouneli
 */
public class MatchTest {
    
    private Match match;
    private Match match2; //match that is finished
    private String botname;
    private long timeSpentTotal;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        MCTSBot bot = new MCTSBot(20);
        botname = bot.name();
        match = new Match(new Reversi(), bot, bot);
        match2 = new Match(new Reversi(), 
                bot, bot);
        long before = System.currentTimeMillis();
        match2.playMatch();
        timeSpentTotal = System.currentTimeMillis() - before;
    }
    
    @Test
    public void playMatchArgTruePrintsSomething() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        match.playMatch(true);
        assertEquals(true, outContent.toString().length() > 1000);
    }
    
    @Test
    public void playMatchArgFalseDoesNotPrintAnything() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        match.playMatch(false);
        assertEquals(true, outContent.toString().length() == 0);
    }
    
    @Test
    public void playMatchDoesNotPrintAnythingByDefault() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        match.playMatch();
        assertEquals(true, outContent.toString().length() == 0);
    }
    
    @Test
    public void avgTimePerPlayerGivesSensibleTime() {
        assertEquals(20, match2.averageTimePerMovePlayer1(), 5); //the error
        assertEquals(20, match2.averageTimePerMovePlayer2(), 5); //margin
        assertEquals(20, match2.averageTimePerMove(), 5); //is system sensitive
    }
    
    @Test
    public void returnWinnerReturnsSomethingSensible() {
        assertEquals(true, match2.returnWinner() == 1 ||
                match2.returnWinner() == -1 ||
                match2.returnWinner() == 0);
    }
    
    @Test
    public void returnWinnerNameReturnsSomethingSensible() {
        assertEquals(true, match2.returnWinnerName().equals(botname) ||
                match2.returnWinnerName().equals("Tie"));
    }
    
    @Test
    public void timeSpentTotalReturnSomethingSensible() {
        assertEquals(timeSpentTotal, match2.timeSpentTotal(), 10);
        //the error margin is system dependant
    }
    
    @Test
    public void tryingToGetAvgTimeTooSoonGivesError() {
        exception.expect(IllegalStateException.class);
        match.averageTimePerMove();
    }
    
    @Test
    public void tryingToGetAvgTimePlayer1TooSoonGivesError() {
        exception.expect(IllegalStateException.class);
        match.averageTimePerMovePlayer1();
    }
    
    @Test
    public void tryingToGetAvgTimePlayer2TooSoonGivesError() {
        exception.expect(IllegalStateException.class);
        match.averageTimePerMovePlayer2();
    }
    
    @Test
    public void tryingToGetWinnerTooSoonGivesError() {
        exception.expect(IllegalStateException.class);
        match.returnWinner();
    }
    
    @Test
    public void tryingToGetWinnerNameTooSoonGivesError() {
        exception.expect(IllegalStateException.class);
        match.returnWinnerName();
    }
    
    @Test
    public void tryingToGetTotalTimeTooSoonGivesError() {
        exception.expect(IllegalStateException.class);
        match.timeSpentTotal();
    }

    
}
