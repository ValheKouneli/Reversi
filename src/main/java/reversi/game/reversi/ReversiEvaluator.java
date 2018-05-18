
package reversi.game.reversi;
import reversi.AI.minimax.Evaluator;

/**
 * This evaluator evaluates the Reversi game situation by giving values to one's
 * own pieces based on their position on the board. Corners are most valuable,
 * and places next to corners have negative value. Sides are the second best
 * place to be. After certain amount of moves are played, does not give
 * different weights to different positions anymore.
 * 
 * @author Valhe Kouneli
 */
public class ReversiEvaluator implements Evaluator {

    /** Returns positive number value for game situations that are good for
     * white and negative values for situations that are good for black.
     * The bigger the absolute value of the number is, the better it is for
     * that player.
     *
     * @param game  object of the type Game
     * @return evaluated value of the game situation
     * @throw IllegalArgumentException if the given game is not type Reversi
     */
    @Override
    public int eval(Object game) {
        Reversi reversi;
        try {
            reversi = (Reversi) game;
        } catch (ClassCastException e) {            
            throw new java.lang.IllegalArgumentException(
                    "This evaluator evaluates only Reversi games.");
        }
        
        ReversiBoard board = reversi.getBoardCopy();
        int turnNumber = reversi.getTurnNumber();
        int piece;
        int eval = 0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                piece = board.getBoardXY(i, j);
                if (turnNumber < 58) {
                    if ((i==0 || i==7) && (j==0 || j==7)) { //corner
                        eval += piece * 1000;
//                    } else if (((i==0 || i==7) && (j==1 || j==6)) ||//next to corner
//                               ((i==1 && i==6) && (j==0 || j==1 || j==6 || j==7))) {
//                        eval += piece * (-90); 
                    } else if (i==0 || i==7 || j==0 || j==7) { //side
                        eval += piece * 50;
                    } else { //middle
                        eval += piece * 1;
                    }
                } else {
                    eval += piece;
                }
                
            }
        }
        return eval;
    }
    
    @Override
    public String name() {
        return "GoodEvaluator";
    }

    @Override
    public Class getGameType() {
        return Reversi.class;
    }
    
}
