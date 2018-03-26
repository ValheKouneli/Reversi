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
import reversi.game.ReverseHelper;
/**
 *
 * @author Valhe Kouneli
 */
public class ReverseHelperTest {
    
    Board board;
    
    @Before
    public void setUp() {
        board = new Board();
        board.setBoardXY(3, 3, 1);
        board.setBoardXY(4, 3, -1);
    }
    
    @Test
    public void reverseUpWorks() {
        assertEquals(true, ReverseHelper.reverseUp(board, 1, 5, 3));
    }
}
