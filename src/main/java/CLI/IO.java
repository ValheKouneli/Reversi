package CLI;

import reversi.data_structures.IntPair;

/**
 *
 * @author Valhe Kouneli
 */
public interface IO {
    public String nextInput();
    public void nextOutput();

    /**
     *
     * @return
     */
    public IntPair nextMove();
}