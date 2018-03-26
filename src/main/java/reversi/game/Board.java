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
public class Board {
    
    private final int[][] board;
    
    public Board() {
        board = new int[8][8];
    }
    
    public int getBoardXY(int x, int y) {
        return board[x][y];
    }
    
    public void setBoardXY(int x, int y, int value) {
        board[x][y] = value;
    }
    
    @Override
    public String toString() {
        String temp = "";
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                temp += board[i][j];
            }
            temp += '\n';
        }
        
        return temp;
    }
    
}
