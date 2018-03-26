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

/**
 *
 * @author Valhe Kouneli
 */
public class BoardTest {
    
    Board board;
    
    @Before
    public void setUp() {
        this.board = new Board();
    }
    
    @Test
    public void setBoardWorks() {
        board.setBoardXY(3, 3, -1);
        assertEquals(-1, board.getBoardXY(3, 3));
    }
    
}
