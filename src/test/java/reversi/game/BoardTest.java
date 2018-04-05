/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.game.Board;

/**
 *
 * @author Valhe Kouneli
 */
public class BoardTest {
    
    Board board;
    
    @Before
    public void setUp() {
        this.board = new Board();
    }
    
    @Test
    public void setBoardWorks() {
        board.setBoard("   0 1 2 3 4 5 6 7 \n" +
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
