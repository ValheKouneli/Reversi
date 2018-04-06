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
        board[3][3] = 1;
        board[4][4] = 1;
        board[3][4] = -1;
        board[4][3] = -1;
    }
    
    public int getBoardXY(int x, int y) {
        return board[x][y];
    }
    
    public void setBoardXY(int x, int y, int value) {
        board[x][y] = value;
    }
    
    
    @Override
    public String toString() {
        String string = "   0 1 2 3 4 5 6 7 \n";
        String help;
        for(int i=0; i<8; i++) {
            string += i + " ";
            for(int j=0; j<8; j++) {
                string += "|";
                switch (board[i][j]) {
                    case -1:
                        help = "\u25CF"; //black circle
                        break;
                    case 1:
                        help = "\u25CB"; //white circle
                        break; 
                    default:
                        help = " ";
                        break;
                }
                string += help;
            }
            string += "|\n";
        }
        
        return string;
    }
    
    public Board getCopy() {
        Board copy = new Board();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                copy.setBoardXY(i, j, board[i][j]);
            }
        }
        return copy;
    }
    
}
