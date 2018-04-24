/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
