/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

/**
 *
 * @author aepiiroi
 */
public class BoardFactory {
    
    private BoardFactory() {}
    
    public static ReversiBoard makeBoard(String boardRepresentation) {
        
        ReversiBoard board = new ReversiBoard();
        String boardString = board.toString();
        int k = 20;
        int c;

        for (int i=0; i<8; i++) {
            k += 3;
            for (int j=0; j<8; j++) {
               c = boardRepresentation.charAt(k);
                switch (c) {
                    case ' ':
                        board.setBoardXY(i, j, 0);
                        break;
                    case '\u25CF':
                        board.setBoardXY(i, j, -1);
                        break;
                    case '\u25CB':
                        board.setBoardXY(i, j, 1);
                        break;
                    default:
                        //throw something
                        break;
                }
                boardString = board.toString();
                k += 2; 
            }
            k += 1;
        }
        return board;
    }
    
}
