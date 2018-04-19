/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.AI.Game;
import reversi.game.BoardFactory;
import reversi.game.Reversi;
import reversi.game.ReversiBoard;

/**
 *
 * @author Valhe Kouneli
 */
public class StateTest {
    
    State state;
    
    @Before
    public void setUp() {
        ReversiBoard board = BoardFactory.makeBoard("   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |●|○|○|○|○|○|○|○|\n" +
                                                    "1 |●|●|●|●|●|●|●|○|\n" +
                                                    "2 |●|○|○|○|●|●|○|○|\n" +
                                                    "3 |●|○|●|○|●|●|●|○|\n" +
                                                    "4 |●|○|○|○|○|●|●|○|\n" +
                                                    "5 |●|○|○|●|●|●|●|○|\n" +
                                                    "6 |●| |●|●|●|●|●|○|\n" +
                                                    "7 | |●|●|●|●|●|●|○|\n");
        Reversi game = new Reversi();
        game.setBoard(board);
        game.setTurn(1);
        game.setTurnNumber(62);
        state = new State((Game) game);
    }
    
    @Test
    public void getAllPossibleStatesGivesTheRightAmountOfStates() {
        assertEquals(2, state.getAllPossibleStates().size());
    }
    
    @Test
    public void getAllPossibleStatesGivesStatesThatHaveASetLatestMove() {
        assertNotNull(state.getAllPossibleStates().get(0).getLatestMove());
        assertNotNull(state.getAllPossibleStates().get(1).getLatestMove());
    }
    
    
}