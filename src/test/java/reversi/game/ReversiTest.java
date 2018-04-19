/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.AI.MCTS.MCTSTestHelper;
/**
 *
 * @author Valhe Kouneli
 */
public class ReversiTest {
    
    Reversi game;
    
    @Before
    public void setUp() {
        this.game = new Reversi();
    }
    
    @Test
    public void getMovesGivesRightAmountOfMoves1() {
        ReversiBoard board = BoardFactory.makeBoard("   0 1 2 3 4 5 6 7 \n" +
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
        assertEquals(9, game.getMoves().size());
    }
    
    @Test
    public void getMovesGivesRightAmountOfMoves2() {
        assertEquals(4, game.getMoves().size());
    }
    
    @Test
    public void getMovesGivesRightAmountOfMoves3() {
        ReversiBoard board = BoardFactory.makeBoard("   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |●|○|○|○|○|○|○|○|\n" +
                                                    "1 |●|●|●|●|●|●|●|○|\n" +
                                                    "2 |●|○|○|○|●|●|○|○|\n" +
                                                    "3 |●|○|●|○|●|●|●|○|\n" +
                                                    "4 |●|○|○|○|○|●|●|○|\n" +
                                                    "5 |●|○|○|●|●|●|●|○|\n" +
                                                    "6 |●| |●|●|●|●|●|○|\n" +
                                                    "7 | |●|●|●|●|●|●|○|\n");
        game.setBoard(board);
        game.setTurn(1);
        game.setTurnNumber(62);
        assertEquals(2, game.getMoves().size());
    }
    
    @Test
    public void getMovesGivesRightAmountOfMoves4() {
        ReversiBoard board = BoardFactory.makeBoard("   0 1 2 3 4 5 6 7 \n" +
                                                   "0 |●|●|●|●| | | | |\n" +
                                                   "1 |●|●|●|○| | | | |\n" +
                                                   "2 |●|●|○|●|○| | | |\n" +
                                                   "3 |●| |○|●|○| | | |\n" +
                                                   "4 |●| |○|●|○| | | |\n" +
                                                   "5 |●|●|○|○|○|○| | |\n" +
                                                   "6 |●| |○| | | | | |\n" +
                                                   "7 |●|●|●|●|●| | | |\n");
        game.setBoard(board);
        game.setTurn(1);
        game.setTurnNumber(34);
        assertEquals(1, game.getMoves().size());
    }
    

   
    
    
}
