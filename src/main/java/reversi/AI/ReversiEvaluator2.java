
package reversi.AI;

import reversi.game.Reversi;
import reversi.game.ReversiBoard;

/**
 *
 * @author Valhe Kouneli
 */
public class ReversiEvaluator2 extends Evaluator <Reversi> {

    /**
     *
     * @param game
     * @return
     */
    @Override
    public int eval(Reversi game) {
        ReversiBoard board = game.getBoardCopy();
        int turnNumber = game.getTurnNumber();
        int piece;
        int eval = 0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                piece = board.getBoardXY(i, j);
                if (turnNumber < 40) {
                    if ((i==0 || i==7) && (j==0 || j==7)) { //corner
                        eval += piece * (100 - turnNumber);
                    } else if (((i==0 || i==7) && (j==1 || j==6)) ||//next to corner
                               ((i==1 && i==6) && (j==0 || j==1 || j==6 || j==7))) {
                        eval += piece * (-90);
                    } else if (i==0 || i==7 || j==0 || j==7) { //side
                        eval += piece * 30;
                    } else { //middle
                        eval += piece * 5;
                    }
                } else {
                    eval += piece;
                }
                
            }
        }
        return eval;
    }
    
}
