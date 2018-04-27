package reversi.game.reversi;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import reversi.AI.minimax.Nim;
import reversi.game.Game;

/** This test tests if ReversiEvaluator gives sensible estimates
 to certain board situations relative to each other.
 *
 * @author Valhe Kouneli
 */
public class ReversiEvaluatorTest {
    
    private Reversi game1;
    private Reversi game2;
    private Reversi even;
    private Reversi game4;
    private Reversi game5;
    private Reversi game6;
    private ReversiEvaluator eval;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        eval = new ReversiEvaluator();
        //very good for black
        game1 = new Reversi();
        game1.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |●|●|●|●|●|●|●|●|\n" +
                                                "1 |●|●|●|○|●|●|●|●|\n" +
                                                "2 |●|●|○|●|○| | |●|\n" +
                                                "3 |●| |○|●|○|●|●|●|\n" +
                                                "4 |●| |○|●|○| | |●|\n" +
                                                "5 |●|●|○|○|○|○|●|●|\n" +
                                                "6 |●| |○| | | |●|●|\n" +
                                                "7 |●|●|●|●|●|●|●|●|\n"
                                                                        ));
        //good for black
        game2 = new Reversi();
        game2.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |●|●|●|●|●|●|○|○|\n" +
                                                "1 |●|●|●|○|●|●|●|○|\n" +
                                                "2 |●|●|○|●|○| | |○|\n" +
                                                "3 |●| |○|●|○|●|●|●|\n" +
                                                "4 |●| |○|●|○| | |●|\n" +
                                                "5 |●|●|○|○|○|○|●|○|\n" +
                                                "6 |●| |○| | | |○|○|\n" +
                                                "7 |●|●|●|●|●|○|○|●|\n"
                                                                        ));
        //even
        even = new Reversi();
        even.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |●|●|●|●|○|○|○|○|\n" +
                                                "1 |●|●|●|●|○|○|○|○|\n" +
                                                "2 |●|●|●|●|○|○|○|○|\n" +
                                                "3 |●|●|●|●|○|○|○|○|\n" +
                                                "4 |●|●|●|●|○|○|○|○|\n" +
                                                "5 |●|●|●|●|○|○|○|○|\n" +
                                                "6 |●|●|●|●|○|○|○|○|\n" +
                                                "7 |●|●|●|●|○|○|○|○|\n"
                                                                        ));
        //very good for white
        game4 = new Reversi();
        game4.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |○|○|●|●|●|●|○|○|\n" +
                                                "1 |○|●|●|○|●|●|●|○|\n" +
                                                "2 |●|●|○|○|○| | |○|\n" +
                                                "3 |●| |○|○|○|●|○|○|\n" +
                                                "4 |●| |○|○|○| | |○|\n" +
                                                "5 |●|●|○|○|○|○|○|○|\n" +
                                                "6 |○| |○| | | |○|○|\n" +
                                                "7 |○|○|○|○|○|○|○|○|\n"
                                                                        ));
        //good for white
        game5 = new Reversi();
        game5.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |○|○|●|○|○|○|●|●|\n" +
                                                "1 | | |●|○|●| | |●|\n" +
                                                "2 | | |○|○|○| | |●|\n" +
                                                "3 | | |○|○|○|●|○|○|\n" +
                                                "4 | | | |○|○| | |●|\n" +
                                                "5 | | |○|○| | | |○|\n" +
                                                "6 | | |○| | | | |●|\n" +
                                                "7 |○|○|○|○|○|○|●|●|\n"
                                                                        ));
        //very good for white
        game6 = new Reversi();
        game6.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |○|○|○|○|○|○|●|●|\n" +
                                                "1 | |○|●|○|●| | | |\n" +
                                                "2 | | |○|○|○| | | |\n" +
                                                "3 | | | |○|○|●|○|○|\n" +
                                                "4 | | | |○|○| | | |\n" +
                                                "5 | | |○| | |○| | |\n" +
                                                "6 | |○| | | | |○| |\n" +
                                                "7 |○| | | | | | |○|\n"
                                                                        ));
    }
    
    @Test
    public void givesZeroToEvenSituation() {
        assertEquals(0, eval.eval(even));
    }
    
    @Test
    public void givesPositiveValueForAllPositionsGoodForWhite() {
        assertEquals(true, eval.eval(game6) > 0);
        assertEquals(true, eval.eval(game5) > 0);
        assertEquals(true, eval.eval(game4) > 0);
    }
    
    @Test
    public void givesNegativeValueForAllPositionsGoodForBlack() {
        assertEquals(true, eval.eval(game1) < 0);
        assertEquals(true, eval.eval(game2) < 0);
    }
    
    @Test
    public void givesHigherValueForVeryGoodWhitePositionThanGoodWhitePosition() {
        assertEquals(true, eval.eval(game6) > eval.eval(game5));
        assertEquals(true, eval.eval(game4) > eval.eval(game5));
    }
    
    @Test
    public void givesHigherAbsoluteValueForVeryGoodBlackPositionThanGoodBlackPosition() {
        assertEquals(true, eval.eval(game2) > eval.eval(game1));
    }
    
    @Test
    public void nameReturnsTheRightName() {
        assertEquals("GoodEvaluator", eval.name());
    }
    
    @Test
    public void throwsAnExceptionIfGivenTheWrongTypeOfGame() {
        Game nim = new Nim(5);
        exception.expect(IllegalArgumentException.class);
        int value = eval.eval(nim);
    }
}
