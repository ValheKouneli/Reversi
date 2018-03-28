/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import java.util.ArrayList;
import java.util.List;
import reversi.AI.Point;

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
        availableMoves = new ArrayList<>();
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
        return ReverseHelper.reverseUp(board, turn, x, y, true) ||
                ReverseHelper.reverseDown(board, turn, x, y, true) ||
                ReverseHelper.reverseLeft(board, turn, x, y, true) ||
                ReverseHelper.reverseRight(board, turn, x, y, true) ||
                ReverseHelper.reverseDownRight(board, turn, x, y, true) ||
                ReverseHelper.reverseUpRight(board, turn, x, y, true) ||
                ReverseHelper.reverseDownLeft(board, turn, x, y, true) ||
                ReverseHelper.reverseUpLeft(board, turn, x, y, true);
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
         * If the move does not reverse any pieces of the opposing color,
         * the move is not legal.
         */
        
        boolean moveFlips = false;
        if (ReverseHelper.reverseUp(board, turn, x, y, false)) {
            moveFlips = true;
        }
        if (ReverseHelper.reverseDown(board, turn, x, y, false)) {
            moveFlips = true;
        }
        if (ReverseHelper.reverseLeft(board, turn, x, y, false)) {
            moveFlips = true;
        }
        if (ReverseHelper.reverseRight(board, turn, x, y, false)) {
            moveFlips = true;
        }
        if (ReverseHelper.reverseDownRight(board, turn, x, y, false)) {
            moveFlips = true;
        }
        if (ReverseHelper.reverseUpRight(board, turn, x, y, false)) {
            moveFlips = true;
        }
        if (ReverseHelper.reverseDownLeft(board, turn, x, y, false)) {
            moveFlips = true;
        }
        if (ReverseHelper.reverseUpLeft(board, turn, x, y, false)) {
            moveFlips = true;
        }
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
