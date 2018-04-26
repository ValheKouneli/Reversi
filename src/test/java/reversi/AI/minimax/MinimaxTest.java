/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

import reversi.game.Game;
import reversi.AI.minimax.Minimax;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class MinimaxTest {
    
    /**
     * This implementation of Game is created for test purposes.
     * Nim is a game for two players.
     * There is a heap of tokens.
     * Players take turns removing 1-3 tokens from the heap.
     * The player who is forced to take the last token loses.
     * The other player wins.
     */
    public class Nim implements Game {
        private int heap;
        private int turn;
        private int turnNumber;
        
        public Nim() {
            heap = 5;
            turn = 1;
            turnNumber = 0;
        }
        
        @Override
        public boolean move(Object move) {
            int amount = (Integer) move;
            if (heap-amount>=0 && 0<amount && amount<=3) {
                heap -= amount;
                turn = (turn == 1 ? -1 : 1);
                turnNumber++;
                return true;
            } else {
                return false;
            }
        }
        
        public int getHeap() {
            return heap;
        }
        
        private void setHeap(int heap) {
            this.heap = heap;
        }
        
        private void setTurn(int turn) {
            this.turn = turn;
        }
        
        @Override
        public int getTurnNumber() {
            return turnNumber;
        }

        @Override
        public int winner() {
            return turn == 1 ? 1 : -1;
        }

        @Override
        public List<Object> getMoves() {
            List<Object> list = new List<>();
            for (int i=1; i<= (heap>3 ? 3 : heap); i++) {
                list.add(i);
            }
            return list;
        }

        @Override
        public int getTurn() {
            return turn;
        }

        @Override
        public Nim getCopy() {
            Nim copy = new Nim();
            copy.setHeap(heap);
            copy.setTurn(turn);
            return copy;
        }

        @Override
        public boolean gameIsOver() {
            return heap == 0;
        }
        
    }
    
    
    private Game nim;
    private Minimax minimax;
    
    @Before
    public void setUp() {
        nim = new Nim();
        minimax = new Minimax();
    }
    
    @Test
    public void minMaxWithoutLimitWorks() {
        int result = minimax.minmax(nim);
        assertEquals(Integer.MIN_VALUE, result);
    }
    
 
    
}
