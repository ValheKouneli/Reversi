/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

/**
 *
 * @author Valhe Kouneli
 */
public class Game {
    
    private Board board;
    private int turn; //black = 1, white = -1
    private int turnNumber;
    
    public Game() {
        board = new Board();
        turn = 1;
        turnNumber = 0;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public void setTurn(int turn) {
        this.turn = turn;
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
        if (turnNumber < 4 ||
                ReverseHelper.reverseUp(board, turn, x, y) ||
                ReverseHelper.reverseDown(board, turn, x, y) ||
                ReverseHelper.reverseLeft(board, turn, x, y) ||
                ReverseHelper.reverseRight(board, turn, x, y) ||
                ReverseHelper.reverseDownRight(board, turn, x, y) ||
                ReverseHelper.reverseUpRight(board, turn, x, y) ||
                ReverseHelper.reverseDownLeft(board, turn, x, y) ||
                ReverseHelper.reverseUpLeft(board, turn, x, y) ) {
            board.setBoardXY(x, y, turn);
            turnNumber++;
            turn = turn*(-1);
            return true;
        } else {
            return false;
        }
        
        
        
        
    }
    
 
}
