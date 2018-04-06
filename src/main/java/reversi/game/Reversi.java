/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import reversi.data_structures.Pair;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class Reversi {
    
 
    
    private ReversiBoard board;
    private int turn; //black = 1, white = -1
    private int turnNumber;
    private List<Pair> availableMoves;
    private int lastTurnNumberToAskAvailableMoves;
    
    public Reversi() {
        board = new ReversiBoard();
        turn = 1;
        turnNumber = 0;
        lastTurnNumberToAskAvailableMoves = 100;

        
    }
    
    public void setBoard(ReversiBoard board) {
        this.board = board;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public int getTurnNumber() {
        return turnNumber;
    }
    
    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }
    
    public void setLastTurnNumberToAskAvailableMoves(int turnNumber) {
        this.lastTurnNumberToAskAvailableMoves = turnNumber;
    }
    
    public int getLastTurnNumberToAskAvailableMoves() {
        return this.lastTurnNumberToAskAvailableMoves;
    }
    
    /**
     * Returns a negative number if black is winning
     * and positive if white is winning.
     * @return (-1)*(number of black pieces)+(number of white pieces)
     */
    public int getScore() {
        int score = 0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                score += board.getBoardXY(i, j);
            }
        }
        return score;
    }
    
    public int getWinner() {
        if (getScore() > 0) {
            return 1;
        } else if (getScore() < 0 ){
            return -1;
        } else {
            return 0;
        }
    }
    
    public boolean gameIsOver() {
        return getMoves().isEmpty();
    }
    
    public Reversi getCopy() {
        Reversi copy = new Reversi();
        ReversiBoard boardCopy = board.getCopy();
        copy.setBoard(boardCopy);
        copy.setTurn(turn);
        copy.setTurnNumber(turnNumber);
        return copy;
    }
    
    public List<Pair> getMoves() {
        if (lastTurnNumberToAskAvailableMoves == turnNumber) {
            return availableMoves;
        }
        lastTurnNumberToAskAvailableMoves = turnNumber;
        availableMoves = new List<>();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (moveIsLegal(board, turn, i, j)) {
                    availableMoves.add(new Pair(i, j));
                }
            }
        }
        return availableMoves;
    }
    
    public boolean moveIsLegal(ReversiBoard board, int turn, int x, int y) {
        if (board.getBoardXY(x, y) != 0) {
            return false;
        }
        return ReverseHelper.reverse(board, turn, x, y, true);
    }
    
    @Override
    public String toString() {
        return board.toString();
    }
    
    /**
     * Makes the suggested move and changes turn if it is legal;
     * returns wheter the move is legal.
     * @param x row number of the move
     * @param y column number of the move
     * @return true if move is legal, false if not
     */
    public boolean move(int x, int y){
        /**
         * It's not allowed to play on an occupied space.
         */
        if (board.getBoardXY(x, y) != 0) {
            return false;
        }
        
        /**
         * If the move does not reverse any pieces of the opposing colour,
         * the move is not legal.
         */

        boolean moveFlips = ReverseHelper.reverse(board, turn, x, y, false);
         
        if (moveFlips) {
            board.setBoardXY(x, y, turn);
            turnNumber++;
            turn = turn*(-1);
            return true;
        } else {
            return false;
        }   
    }
    
    public ReversiBoard getBoardCopy() {
        return board.getCopy();
    }
    
 
}
