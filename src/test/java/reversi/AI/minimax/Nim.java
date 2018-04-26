package reversi.AI.minimax;

import reversi.data_structures.List;
import reversi.game.Game;

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
    public Nim(int heap) {
        if (heap < 0) {
            throw new java.lang.IllegalArgumentException(
                    "heap can not be neagative");
        }
        this.heap = heap;
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
        if (!gameIsOver()) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
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