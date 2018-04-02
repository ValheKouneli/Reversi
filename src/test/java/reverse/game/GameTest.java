/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverse.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.game.Board;
import reversi.game.Game;
/**
 *
 * @author Valhe Kouneli
 */
public class GameTest {
    
    Game game;
    
    @Before
    public void setUp() {
        this.game = new Game();
    }
    
    @Test
    public void availableMovesWorks() {
        Board board = new Board();
        board.setBoard("   0 1 2 3 4 5 6 7 \n" +
                       "0 | | | | | | | | |\n" +
                       "1 | | | | | | | | |\n" +
                       "2 | | |●| |○| | | |\n" +
                       "3 |○| |●|○|○| | | |\n" +
                       "4 | |○|●|○|○|○|○| |\n" +
                       "5 |○| |●| | | | | |\n" +
                       "6 |○|○|●|●| | | | |\n" +
                       "7 |○| |○| | | | | |\n");
        game.setBoard(board);
        game.setTurn(1);
        assertEquals(9, game.getAvailableMoves().size());
    }
   
    
    
}
