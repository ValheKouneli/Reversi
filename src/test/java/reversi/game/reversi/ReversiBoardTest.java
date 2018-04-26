package reversi.game.reversi;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Valhe Kouneli
 */
public class ReversiBoardTest {
    
    ReversiBoard board;
    
    @Before
    public void setUp() {
        this.board = new ReversiBoard();
    }
    
    @Test
    public void getCopyWorks() {
        ReversiBoard copy = board.getCopy();
        copy.setBoardXY(0, 0, 1);
        assertEquals(true, board.getBoardXY(0, 0) != copy.getBoardXY(0, 0));
    }
    
    @Test
    public void toStringWorks() {
        String expected =   "   0 1 2 3 4 5 6 7 \n" +
                            "0 | | | | | | | | |\n" +
                            "1 | | | | | | | | |\n" +
                            "2 | | | | | | | | |\n" +
                            "3 | | | |○|●| | | |\n" +
                            "4 | | | |●|○| | | |\n" +
                            "5 | | | | | | | | |\n" +
                            "6 | | | | | | | | |\n" +
                            "7 | | | | | | | | |\n";
        assertEquals(expected, board.toString());
    }
    

    
}
