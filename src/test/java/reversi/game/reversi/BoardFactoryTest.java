package reversi.game.reversi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author aepiiroi
 */
public class BoardFactoryTest {
    
    @Test
    public void setBoardWorks() {
        ReversiBoard board = BoardFactory.makeBoard
                      ("   0 1 2 3 4 5 6 7 \n" +
                       "0 | | | | | | | | |\n" +
                       "1 | | | | | | | | |\n" +
                       "2 | | |●| |○| | | |\n" +
                       "3 |○| |●|○|○| | | |\n" +
                       "4 | |○|●|○|○|○|○| |\n" +
                       "5 |○| |●| | | | | |\n" +
                       "6 |○|○|●|●| | | | |\n" +
                       "7 |○| |○| | | | | |\n");
        assertEquals("   0 1 2 3 4 5 6 7 \n" +
                     "0 | | | | | | | | |\n" +
                     "1 | | | | | | | | |\n" +
                     "2 | | |●| |○| | | |\n" +
                     "3 |○| |●|○|○| | | |\n" +
                     "4 | |○|●|○|○|○|○| |\n" +
                     "5 |○| |●| | | | | |\n" +
                     "6 |○|○|●|●| | | | |\n" +
                     "7 |○| |○| | | | | |\n", board.toString());
    }
}
