package reversi.AI.minimax;

/** This evaluator is created just for test purposes.
 *
 * @author Valhe Kouneli
 */
public class NimEvaluator implements Evaluator {

    public NimEvaluator() {}
    
    @Override
    public int eval(Object game) {
        if (game.getClass() != Nim.class) {
            throw new java.lang.IllegalArgumentException(
                    "Wrong game type for this evaluator.");
        }
        Nim nim = (Nim) game;
        int heap = nim.getHeap();
        int turn = nim.getTurn();
        if (heap == 1) {
            return turn == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else if (heap <= 4) {
            return turn == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            return -1; //This evaluator just assumes all other situations are
                       //good for the second player
        }
    }

    @Override
    public String name() {
        return "nim evaluator";
    }

    @Override
    public Class getGameType() {
        return Nim.class;
    }
    
}
