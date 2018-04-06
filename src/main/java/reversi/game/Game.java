/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import reversi.AI.Point;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class Game {
    
 
    
    private Board board;
    private int turn; //black = 1, white = -1
    private int turnNumber;
    private List<Point> availableMoves;
    private int lastTurnNumberToAskAvailableMoves;
    
    public Game() {
        board = new Board();
        turn = 1;
        turnNumber = 0;
        lastTurnNumberToAskAvailableMoves = 100;

        
    }
    
    public void setBoard(Board board) {
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
    
    public Game getCopy() {
        Game copy = new Game();
        Board boardCopy = board.getCopy();
        copy.setBoard(boardCopy);
        copy.setTurn(turn);
        copy.setTurnNumber(turnNumber);
        return copy;
    }
    
    public List<Point> getAvailableMoves() {
        if (lastTurnNumberToAskAvailableMoves == turnNumber) {
            return availableMoves;
        }
        lastTurnNumberToAskAvailableMoves = turnNumber;
        availableMoves = new List<>();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (moveIsLegal(board, turn, i, j)) {
                    availableMoves.add(new Point(i, j));
                }
            }
        }
        return availableMoves;
    }
    
    public boolean moveIsLegal(Board board, int turn, int x, int y) {
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
    
    public Board getBoardCopy() {
        return board.getCopy();
    }
    
 
}
