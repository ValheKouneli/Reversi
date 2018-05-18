package reversi.AI.minimax;

import reversi.game.Game;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Valhe Kouneli
 */
public class MinimaxTest {

    private Game nim;
    private Minimax minimax;
    private NimEvaluator eval;
    
    @Before
    public void setUp() {
        nim = new Nim(); //heap = 5
        minimax = new Minimax();
        eval = new NimEvaluator();
    }
    
    @Test
    public void minMaxWithoutLimitWorks() {
        int result = minimax.minimax(nim);
        assertEquals(Integer.MIN_VALUE, result);
    }
    
    @Test
    public void minMaxWithLimitWorks() {
        int result = minimax.minimax(nim, 0, 5, eval);
        assertEquals(Integer.MIN_VALUE, result);
    }
    
    @Test
    public void bestMoveIsSavedWhenNoLimit() {
        nim = new Nim(4);
        minimax.minimax(nim);
        assertEquals(3, minimax.getBestMove());
    }
    
    @Test
    public void bestMoveIsSavedWhenLimitIsUsed() {
        nim = new Nim(4);
        minimax.minimax(nim, 0, 3, eval);
        assertEquals(3, minimax.getBestMove());
    }
    
 
    
}
