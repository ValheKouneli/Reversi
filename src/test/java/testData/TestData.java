/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testData;

import reversi.game.Game;
import reversi.game.reversi.BoardFactory;
import reversi.game.reversi.Reversi;
import reversi.game.reversi.ReversiBoard;

/**
 *
 * @author Valhe Kouneli
 */
public class TestData {
    
    private static final String BOARD_SITUATION1 = "   0 1 2 3 4 5 6 7 \n" +
                                     "0 |●|●|●|●|●|●|●|●|\n" +
                                     "1 |○|○|○|○|○|○|●|●|\n" +
                                     "2 |○|○|●|○|●|●|●|●|\n" +
                                     "3 |○|○|○|●|○|●|●|●|\n" +
                                     "4 |○|○|●|○|●|○|●|●|\n" +
                                     "5 |○|○|○|○|●|●|●|●|\n" +
                                     "6 |○|○|○|○|●|●| |●|\n" +
                                     "7 |●|●|●|●|●|○| |●|\n";
    
    private static final String BOARD_SITUATION2 = "   0 1 2 3 4 5 6 7 \n" +
                                                   "0 |●|●|●|●| | | | |\n" +
                                                   "1 |●|●|●|○| | | | |\n" +
                                                   "2 |●|●|○|●|○| | | |\n" +
                                                   "3 |●| |○|●|○| | | |\n" +
                                                   "4 |●| |○|●|○| | | |\n" +
                                                   "5 |●|●|○|○|○|○| | |\n" +
                                                   "6 |●| |○| | | | | |\n" +
                                                   "7 |●|●|●|●|●| | | |\n";
    
    public static ReversiBoard getBoard1() {
        return BoardFactory.makeBoard(BOARD_SITUATION1);
    }
    
    public static Reversi getReversi1() {
        Reversi reversi = new Reversi();
        reversi.setBoard(getBoard1());
        reversi.setTurn(1);
        reversi.setTurnNumber(62);
        return reversi;
    }
    
    public static Game getGame1() {
        return (Game) getReversi1();
    }
    
}
