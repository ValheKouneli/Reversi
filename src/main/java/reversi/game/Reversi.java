/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import reversi.AI.Game;
import reversi.data_structures.IntPair;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class Reversi implements Game <IntPair> {
    
 
    
    private ReversiBoard board;
    private int turn; //black = 1, white = -1
    private int turnNumber;
    
    public Reversi() {
        board = new ReversiBoard();
        turn = 1;
        turnNumber = 0;   
    }
    
    public void setBoard(ReversiBoard board) {
        this.board = board;
    }
    
    @Override
    public int getTurn() {
        return turn;
    }
    
    @Override
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
    
    public int getWinner() {
        if (getScore() > 0) {
            return 1;
        } else if (getScore() < 0 ){
            return -1;
        } else {
            return 0;
        }
    }
    
    @Override
    public boolean gameIsOver() {
        boolean currentPlayerCanNotMove = getMoves().isEmpty();
        turn *= -1; //change turn temporarily
        boolean nextPlayerCanNotMove = getMoves().isEmpty();
        turn *= -1; //change turn back
        return currentPlayerCanNotMove && nextPlayerCanNotMove;
    }
    
    @Override
    public Reversi getCopy() {
        Reversi copy = new Reversi();
        ReversiBoard boardCopy = board.getCopy();
        copy.setBoard(boardCopy);
        copy.setTurn(turn);
        copy.setTurnNumber(turnNumber);
        return copy;
    }
    
    @Override
    public List<IntPair> getMoves() {
        List<IntPair> availableMoves = new List<>();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (moveIsLegal(board, turn, i, j)) {
                    availableMoves.add(new IntPair(i, j));
                }
            }
        }

        return availableMoves;
    }
    
    protected boolean moveIsLegal(ReversiBoard board, int turn, int x, int y) {
        if (board.getBoardXY(x, y) != 0) {
            return false;
        }
        return ReverseHelper.reverse(board, turn, x, y, true);
    }
    
    @Override
    public String toString() {
        String temp = board.toString();
        if (!gameIsOver()) {
            temp += "It's " + getPlayer(getTurn()) + "'s turn.\n";
        } else {
            int score = getScore();
            int points = score < 0 ? score*-1 : score;
            temp += "Winner is " + getPlayer(score) + " with " + points + " points.";
        }
        return temp;
    }
    
    private static String getPlayer(int i) {
        if (i<0) {
            return "black";
        } else if (i>0) {
            return "white";
        } else {
            return "no one";
        }
    }
    
    /**
     * Makes the suggested move and changes turn if it is legal;
     * returns whether the move is legal.
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

        boolean moveFlips = ReverseHelper.reverse(board, turn, x, y, false);
         
        if (moveFlips) {
            board.setBoardXY(x, y, turn);
            turnNumber++;
            turn *= -1;
            return true;
        } else {
            return false;
        }   
    }
    
    public ReversiBoard getBoardCopy() {
        return board.getCopy();
    }

    @Override
    public boolean move(IntPair move) {
        if (move == null) {
            turnNumber++;
            turn *= -1;
            return true;
        }
        return move(move.getX(), move.getY());
    }

    @Override
    public int winner() {
        return getScore() > 0 ? 1 : -1;
    }
    
 
}
