/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class MinMaxTest {
    
    public class Nim implements Game <Integer> {
        private int heap;
        private int turn;
        
        public Nim() {
            heap = 5;
            turn = 1;
        }
        
        @Override
        public boolean move(Integer move) {
            if (heap-move>=0 && 0<move && move<=3) {
                heap -= move;
                turn = (turn == 1 ? -1 : 1);
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
        public int winner() {
            return turn == 1 ? 1 : -1;
        }

        @Override
        public List<Integer> getMoves() {
            List<Integer> list = new List<>();
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
    
    
    private static Game nim;
    
    @Before
    public void setUp() {
        nim = new Nim();
    }
    
    @Test
    public void minMaxWithoutLimitWorks() {
        int result = MinMax.minmax(nim, 0, Integer.MAX_VALUE);
        assertEquals(Integer.MIN_VALUE, result);
    }
    
 
    
}
