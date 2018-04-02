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
    
    Board boardSingleFlips;
    Board boardDoubleFlips;
    Board boardComboFlips;
    Game gameComboFlips;
    
    @Before
    public void setUp() {
        boardSingleFlips = new Board();
        boardSingleFlips.setBoard("   0 1 2 3 4 5 6 7 \n" +
                                  "0 | | | | | | | | |\n" +
                                  "1 | |○|○|○| | | | |\n" +
                                  "2 | |○|●|○| | | | |\n" +
                                  "3 | |○|○|○| | | | |\n" +
                                  "4 | | | | | | | | |\n" +
                                  "5 | | | | | | | | |\n" +
                                  "6 | | | | | | | | |\n" +
                                  "7 | | | | | | | | |\n");
        
        boardDoubleFlips = new Board();
        boardDoubleFlips.setBoard("   0 1 2 3 4 5 6 7 \n" +
                                  "0 | | | | | | | | |\n" +
                                  "1 | |○| |○| |○| | |\n" +
                                  "2 | | |○|○|○| | | |\n" +
                                  "3 | |○|○|●|○|○| | |\n" +
                                  "4 | | |○|○|○| | | |\n" +
                                  "5 | |○| |○| |○| | |\n" +
                                  "6 | | | | | | | | |\n" +
                                  "7 | | | | | | | | |\n");
        
        boardComboFlips = new Board();
        boardComboFlips.setBoard("   0 1 2 3 4 5 6 7 \n" +
                                  "0 | | | | | | | | |\n" +
                                  "1 |○|○|○| |○|●|○| |\n" +
                                  "2 |●| |●| |○| |○| |\n" +
                                  "3 |○|○|○| |○|●|○| |\n" +
                                  "4 | | | |●| | | | |\n" +
                                  "5 | | | |○| | | | |\n" +
                                  "6 | |●|○| |○|●| | |\n" +
                                  "7 | | | | | | | | |\n"); 
        
        gameComboFlips = new Game();
        gameComboFlips.setTurn(-1);
        gameComboFlips.setBoard(boardComboFlips);
    }
    
    /**
     * Testing flipping only one piece.
     */
    
    @Test
    public void reverseUpOneWorks() {
        ReverseHelper.reverseUp(boardSingleFlips, -1, 4, 2, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(3, 2));
    }
    
    @Test
    public void reverseDownOneWorks() {
        ReverseHelper.reverseDown(boardSingleFlips, -1, 0, 2, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(1, 2));
    }
    
    @Test
    public void reverseLeftOneWorks() {
        ReverseHelper.reverseLeft(boardSingleFlips, -1, 2, 4, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(2, 3));
    }
    
    @Test
    public void reverseRightOneWorks() {
        ReverseHelper.reverseRight(boardSingleFlips, -1, 2, 0, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(2, 1));
    }
    
    @Test
    public void reverseDownRightOneWorks() {
        ReverseHelper.reverseDownRight(boardSingleFlips, -1, 0, 0, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(1, 1));
    }
    
    @Test
    public void reverseDownLeftOneWorks() {
        ReverseHelper.reverseDownLeft(boardSingleFlips, -1, 0, 4, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(1, 3));
    }
    
    @Test
    public void reverseUpRightOneWorks() {
        ReverseHelper.reverseUpRight(boardSingleFlips, -1, 4, 0, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(3, 1));
    }
    
    @Test
    public void reverseUpLeftOneWorks() {
        ReverseHelper.reverseUpLeft(boardSingleFlips, -1, 4, 4, false);
        assertEquals(-1, boardSingleFlips.getBoardXY(3, 3));
    }
    
    /**
     * Testing flipping two pieces at once.
     */
    
    @Test
    public void reverseUpTwoWorks() {
        ReverseHelper.reverseUp(boardDoubleFlips, -1, 6, 3, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(5, 3));
        assertEquals(-1, boardDoubleFlips.getBoardXY(4, 3));
    }
    
    @Test
    public void reverseDownTwoWorks() {
        ReverseHelper.reverseDown(boardDoubleFlips, -1, 0, 3, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(1, 3));
        assertEquals(-1, boardDoubleFlips.getBoardXY(2, 3));
    }
    
    @Test
    public void reverseLeftTwoWorks() {
        ReverseHelper.reverseLeft(boardDoubleFlips, -1, 3, 6, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(3, 5));
        assertEquals(-1, boardDoubleFlips.getBoardXY(3, 4));
    }
    
    @Test
    public void reverseRightTwoWorks() {
        ReverseHelper.reverseRight(boardDoubleFlips, -1, 3, 0, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(3, 1));
        assertEquals(-1, boardDoubleFlips.getBoardXY(3, 2));
    }
    
    @Test
    public void reverseUpRightTwoWorks() {
        ReverseHelper.reverseUpRight(boardDoubleFlips, -1, 6, 0, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(5, 1));
        assertEquals(-1, boardDoubleFlips.getBoardXY(4, 2));
    }
    
    @Test
    public void reverseUpLeftTwoWorks() {
        ReverseHelper.reverseUpLeft(boardDoubleFlips, -1, 6, 6, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(5, 5));
        assertEquals(-1, boardDoubleFlips.getBoardXY(4, 4));
    }
    
    @Test
    public void reverseDownRightTwoWorks() {
        ReverseHelper.reverseDownRight(boardDoubleFlips, -1, 0, 0, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(1, 1));
        assertEquals(-1, boardDoubleFlips.getBoardXY(2, 2));
    }
    
    @Test
    public void reverseDownLeftTwoWorks() {
        ReverseHelper.reverseDownLeft(boardDoubleFlips, -1, 0, 6, false);
        assertEquals(-1, boardDoubleFlips.getBoardXY(1, 5));
        assertEquals(-1, boardDoubleFlips.getBoardXY(2, 4));
    }
    
    /**
     * Testing flipping pieces to multiple directions at once.
     * TODO: Move this to GameTest ??
     */
    
    @Test
    public void reverseDownAndDownRightWorks() {
        gameComboFlips.move(0, 0);
        assertEquals(-1, boardComboFlips.getBoardXY(1, 0));
        //assertEquals(-1, boardComboFlips.getBoardXY(1, 1));
    }
    
    @Test
    public void reverseDownAndDownLeftWorks() {
        gameComboFlips.move(0, 2);
        assertEquals(-1, boardComboFlips.getBoardXY(1, 2));
       //assertEquals(-1, boardComboFlips.getBoardXY(1, 1));
    }
}
