package reversi.game.reversi;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import reversi.data_structures.IntPair;
/**
 *
 * @author Valhe Kouneli
 */
public class ReversiTest {
    
    Reversi game;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        this.game = new Reversi();
    }
    
    @Test
    public void blackStartsTheGame() {
        assertEquals(-1, game.getTurn());
    }
    
    @Test
    public void inTheBeginningTurnNumberIsZero() {
        assertEquals(0, game.getTurnNumber());
    }
    
    @Test
    public void setBoardSetsTurnNumberAndTurnRight() {
        game.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |○|○|○|○|○|○|○|○|\n" +
                                                "1 |○|○|○|○|○|○|○|○|\n" +
                                                "2 |○|○|○|○|○|○|○|○|\n" +
                                                "3 |○|○|○|○|○|○|○| |\n" +
                                                "4 |○|○|○|○|○|○| | |\n" +
                                                "5 |○|○|○|○|○|○| |●|\n" +
                                                "6 |○|○|○|○|○|○|○| |\n" +
                                                "7 |○|○|○|○|○|○|○|○|\n"
                                                                        ));
        assertEquals(59, game.getTurnNumber());
        assertEquals(1, game.getTurn());
        
        game.setBoard(BoardFactory.makeBoard(       "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |●|○|○|○|○|○|○|○|\n" +
                                                    "1 |●|●|●|●|●|●|●|○|\n" +
                                                    "2 |●|○|○|○|●|●|○|○|\n" +
                                                    "3 |●|○|●|○|●|●|●|○|\n" +
                                                    "4 |●|○|○|○|○|●|●|○|\n" +
                                                    "5 |●|○|○|●|●|●|●|○|\n" +
                                                    "6 |●| |●|●|●|●|●|○|\n" +
                                                    "7 | |●|●|●|●|●|●|○|\n"));
        assertEquals(62, game.getTurnNumber());
        assertEquals(-1, game.getTurn());
    }
    @Test
    public void getMovesGivesRightAmountOfMoves1() {
        game.setBoard(BoardFactory.makeBoard(   "   0 1 2 3 4 5 6 7 \n" +
                                                "0 | | | | | | | | |\n" +
                                                "1 | | | | | | | | |\n" +
                                                "2 | | |○| |●| | | |\n" +
                                                "3 |●| |○|●|●| | | |\n" +
                                                "4 | |●|○|●|●|●|●| |\n" +
                                                "5 |●| |○| | | | | |\n" +
                                                "6 |●|●|○|○| | | | |\n" +
                                                "7 |●| |●| | | | | |\n"));
        //it's black's turn
        assertEquals(9, game.getMoves().size());
    }
    
    @Test
    public void getMovesGivesRightAmountOfMoves2() {
        assertEquals(4, game.getMoves().size());
    }
    
    @Test
    public void getMovesGivesRightMoves1() {
        game.setBoard(BoardFactory.makeBoard(       "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |●|○|○|○|○|○|○|○|\n" +
                                                    "1 |●|●|●|●|●|●|●|○|\n" +
                                                    "2 |●|○|○|○|●|●|○|○|\n" +
                                                    "3 |●|○|●|○|●|●|●|○|\n" +
                                                    "4 |●|○|○|○|○|●|●|○|\n" +
                                                    "5 |●|○|○|●|●|●|●|○|\n" +
                                                    "6 |●| |●|●|●|●|●|○|\n" +
                                                    "7 | |●|●|●|●|●|●|○|\n"));
        //it's black's turn
        IntPair move = new IntPair(6, 1);
        
        assertEquals(true, game.getMoves().contains(move));
        assertEquals(1, game.getMoves().size());
        
    }
    
    @Test
    public void getMovesGivesRightMoves2() {
        game.setBoard(BoardFactory.makeBoard(       "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |○|○|○|○| | | | |\n" +
                                                    "1 |○|○|○|●| | | | |\n" +
                                                    "2 |○|○|●|○|●| | | |\n" +
                                                    "3 |○| |●|○|●| | | |\n" +
                                                    "4 |○| |●|○|●| | | |\n" +
                                                    "5 |○|○|●|●|●|●| | |\n" +
                                                    "6 |○| |●| | | | | |\n" +
                                                    "7 |○|○|○|○|○| | | |\n"));
        //it's blacks turn
        IntPair move = new IntPair(1, 4);
        assertEquals(true, game.getMoves().contains(move));
        assertEquals(1, game.getMoves().size());
    }
    
    @Test
    public void getScoreWorks() {
        game.setBoard(BoardFactory.makeBoard(       "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |○|○|○|○| | | | |\n" +
                                                    "1 |○|○|○|●| | | | |\n" +
                                                    "2 |○|○|●|○|●| | | |\n" +
                                                    "3 |○| |●|○|●| | | |\n" +
                                                    "4 |○| |●|○|●| | | |\n" +
                                                    "5 |○|○|●|●|●|●| | |\n" +
                                                    "6 |○| |●| | | | | |\n" +
                                                    "7 |○|○|○|○|○| | | |\n"));
        assertEquals(10, game.getScore());
    }
    
    @Test
    public void winnerWorks() {
        game.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |○|○|○|○|○|○|○|○|\n" +
                                                "1 |○|○|○|○|○|○|○|○|\n" +
                                                "2 |○|○|○|○|○|○|○|○|\n" +
                                                "3 |○|○|○|○|○|○|○| |\n" +
                                                "4 |○|○|○|○|○|○| | |\n" +
                                                "5 |○|○|○|○|○|○| |●|\n" +
                                                "6 |○|○|○|○|○|○|○| |\n" +
                                                "7 |○|○|○|○|○|○|○|○|\n"
                                                                        ));
        assertEquals(1, game.winner());
    }
    
    @Test
    public void gameIsOverWorks() {
        game.setBoard(BoardFactory.makeBoard(   "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |○|○|○|○|○|○|○|○|\n" +
                                                "1 |○|○|○|○|○|○|○|○|\n" +
                                                "2 |○|○|○|○|○|○|○|○|\n" +
                                                "3 |○|○|○|○|○|○|○| |\n" +
                                                "4 |○|○|○|○|○|○| | |\n" +
                                                "5 |○|○|○|○|○|○| |●|\n" +
                                                "6 |○|○|○|○|○|○|○| |\n" +
                                                "7 |○|○|○|○|○|○|○|○|\n"
                                                                        ));
        assertEquals(true, game.gameIsOver());
        
        game.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 | | | | |○| | | |\n" +
                                                "1 | | | | |○|○| | |\n" +
                                                "2 |○|○|○|○|○|○|○|●|\n" +
                                                "3 | | |○|○|○|○| |●|\n" +
                                                "4 | | |○|○|○| | |●|\n" +
                                                "5 | | | | | | | | |\n" +
                                                "6 | | | | | | | | |\n" +
                                                "7 | | | | | | | | |\n"
                                                                        ));
        assertEquals(true, game.gameIsOver());
        
        game.setBoard(BoardFactory.makeBoard(  
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
        assertEquals(false, game.gameIsOver());
        
        game.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |●|●|●|●|●|●|●|●|\n" +
                                                "1 |●|●|●|●|●|●|●|●|\n" +
                                                "2 |●|●|●|●|●|●|●|●|\n" +
                                                "3 |●|●|●|●|●|●|●| |\n" +
                                                "4 |●|●|●|●|●|●| | |\n" +
                                                "5 |●|●|●|●|●|●|○| |\n" +
                                                "6 |●|●|●|●|●|●|●| |\n" +
                                                "7 |●|●|●|●|●|●|●|●|\n"
                                                                        ));
        assertEquals(false, game.gameIsOver());
    }
    
    @Test
    public void moveReturnsIfMoveWasLegalAndMakesTheMoveIfYesAndChangesTurn() {
        game.setBoard(BoardFactory.makeBoard(       "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |○|○|○|○| | | | |\n" +
                                                    "1 |○|○|○|●| | | | |\n" +
                                                    "2 |○|○|●|○|●| | | |\n" +
                                                    "3 |○| |●|○|●| | | |\n" +
                                                    "4 |○| |●|○|●| | | |\n" +
                                                    "5 |○|○|●|●|●|●| | |\n" +
                                                    "6 |○| |●| | | | | |\n" +
                                                    "7 |○|○|○|○|○| | | |\n"));
        int turnNumberBefore = game.getTurnNumber();
        int turnBefore = game.getTurn();
        assertEquals(false, game.move(0, 4));
        assertEquals(turnBefore, game.getTurn());
        assertEquals(turnNumberBefore, game.getTurnNumber());
        assertEquals(true, game.move(1, 4));
        assertEquals(turnNumberBefore + 1, game.getTurnNumber());
        assertEquals(turnBefore*(-1), game.getTurn());
    }
    
    @Test
    public void legalMoveChangesTheBoard() {
        game.setBoard(BoardFactory.makeBoard(       "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |○|○|○|○| | | | |\n" +
                                                    "1 |○|○|○|●| | | | |\n" +
                                                    "2 |○|○|●|○|●| | | |\n" +
                                                    "3 |○| |●|○|●| | | |\n" +
                                                    "4 |○| |●|○|●| | | |\n" +
                                                    "5 |○|○|●|●|●|●| | |\n" +
                                                    "6 |○| |●| | | | | |\n" +
                                                    "7 |○|○|○|○|○| | | |\n"));
        //it's black's turn
        game.move(1, 4);
        assertEquals(-1, game.getBoardCopy().getBoardXY(1, 4));
    }
    
    @Test
    public void toStringWorks() {
        game.setBoard(BoardFactory.makeBoard(       "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |○|○|○|○| | | | |\n" +
                                                    "1 |○|○|○|●| | | | |\n" +
                                                    "2 |○|○|●|○|●| | | |\n" +
                                                    "3 |○| |●|○|●| | | |\n" +
                                                    "4 |○| |●|○|●| | | |\n" +
                                                    "5 |○|○|●|●|●|●| | |\n" +
                                                    "6 |○| |●| | | | | |\n" +
                                                    "7 |○|○|○|○|○| | | |\n"));
        
        String correct =                            "   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |○|○|○|○| | | | |\n" +
                                                    "1 |○|○|○|●| | | | |\n" +
                                                    "2 |○|○|●|○|●| | | |\n" +
                                                    "3 |○| |●|○|●| | | |\n" +
                                                    "4 |○| |●|○|●| | | |\n" +
                                                    "5 |○|○|●|●|●|●| | |\n" +
                                                    "6 |○| |●| | | | | |\n" +
                                                    "7 |○|○|○|○|○| | | |\n" +
                                                    "Turn #34\n" +
                                                    "It's black's turn.\n";
        assertEquals(correct, game.toString());
        
        game.setBoard(BoardFactory.makeBoard(  
                                                "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |●|●|●|●|●|●|●|●|\n" +
                                                "1 |●|●|●|●|●|●|●|●|\n" +
                                                "2 |●|●|●|●|●|●|●|●|\n" +
                                                "3 |●|●|●|●|●|●|●| |\n" +
                                                "4 |●|●|●|●|●|●| | |\n" +
                                                "5 |●|●|●|●|●|●| |○|\n" +
                                                "6 |●|●|●|●|●|●|●| |\n" +
                                                "7 |●|●|●|●|●|●|●|●|\n"
                                                                        ));
        
        correct =                               "   0 1 2 3 4 5 6 7 \n" +
                                                "0 |●|●|●|●|●|●|●|●|\n" +
                                                "1 |●|●|●|●|●|●|●|●|\n" +
                                                "2 |●|●|●|●|●|●|●|●|\n" +
                                                "3 |●|●|●|●|●|●|●| |\n" +
                                                "4 |●|●|●|●|●|●| | |\n" +
                                                "5 |●|●|●|●|●|●| |○|\n" +
                                                "6 |●|●|●|●|●|●|●| |\n" +
                                                "7 |●|●|●|●|●|●|●|●|\n" +
                                            "Winner is black with 57 points.\n";
        assertEquals(correct, game.toString());
    }
    
    @Test
    public void getCopyWorks() {
        Reversi copy = game.getCopy();
        assertEquals(0, game.getTurnNumber());
        copy.move(2, 3);
        assertEquals(true, game.getTurn() != copy.getTurn());
    }
    
    @Test
    public void askingWinnerBeforeGameIsOverThrowsAnException() {
        exception.expect(IllegalStateException.class);
        int winner = game.winner();
    }

   
    
    
}
