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
    
    private final Board board;
    private int turn; //black = 1, white = -1
    
    public Game() {
        board = new Board();
        turn = 1;
    }
    
    public boolean move(int x, int y){
        if (board.getBoardXY(x, y) != 0) {
            return false;
        }
        
        board.setBoardXY(x, y, turn);
       
        reverseUp(x, y);
        reverseDown(x, y);
        reverseLeft(x, y);
        reverseRight(x, y);
        reverseDownRight(x, y);
        reverseUpRight(x, y);
        reverseDownLeft(x, y);
        reverseUpLeft(x, y);
        
        turn = turn*(-1);
        return true;
    }
    
    private void reverseUp(int x, int y) {
        int limitOpp = x;
        int limitOwn = x;
        for (int i=x; x>=0; x--) {
            if (board.getBoardXY(i, y) == turn*(-1)) {
                limitOpp = i;
            } else if (board.getBoardXY(i, y) == turn) {
                limitOwn = i;
                break;
            } else {
                break;
            }
        }
        
        if (limitOwn < limitOpp) {
            for (int i=x-1; i>limitOwn; i--) {
                board.setBoardXY(i, y, turn);
            }
        }
    }
    
    private void reverseLeft(int x, int y) {
        int limitOpp = y;
        int limitOwn = y;
        for (int j=y; j>=0; j--) {
            if (board.getBoardXY(x, j) == turn*(-1)) {
                limitOpp = j;
            } else if (board.getBoardXY(x, j) == turn) {
                limitOwn = j;
                break;
            } else {
                break;
            }
        }
        
        if (limitOwn < limitOpp) {
            for (int j=y-1; j>limitOwn; j--) {
                board.setBoardXY(x, j, turn);
            }
        }
    }
    
    private void reverseDown(int x, int y) {
        int limitOpp = x;
        int limitOwn = x;
        for (int i=x; i<8; i++) {
            if (board.getBoardXY(i, y) == turn*(-1)) {
                limitOpp = i;
            } else if (board.getBoardXY(i, y) == turn) {
                limitOwn = i;
                break;
            } else {
                break;
            }
        }
        
        if (limitOwn > limitOpp) {
            for (int i=x; i<limitOwn; i++) {
                board.setBoardXY(i, y, turn);
            }
        }
    }
    
    private void reverseRight(int x, int y) {
        int limitOpp = y;
        int limitOwn = y;
        
        for (int j=y; j<8; j++) {
            if (board.getBoardXY(x, j) == turn*(-1)) {
                limitOpp = j;
            } else if (board.getBoardXY(x, j) == turn) {
                limitOwn = j;
                break;
            } else {
                break;
            }
        }
        
        if (limitOwn > limitOpp) {
            for (int j=y; j<limitOwn; j++) {
                board.setBoardXY(x, j, turn);
            }
        }
    }
    
    private void reverseDownRight(int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        int limitOwnY = y;
        int limitOppY = y;
        
        int i=x;
        int j=y;
        
        while (i<8 && j<8) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
                limitOppY = j;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                limitOwnY = j;
                break;
            } else {
                break;
            }
            i++;
            j++;
        }
        
        if (limitOwnX > limitOppX) {
            i=x;
            j=y;
            
            while (i<limitOwnX && j<limitOwnY) {
                board.setBoardXY(i, j, turn);
                i++;
                j++;
            }
        }
    }
    
    private void reverseUpLeft(int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        int limitOwnY = y;
        int limitOppY = y;
        
        int i=x;
        int j=y;
        
        while (i>=0 && j>=0) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
                limitOppY = j;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                limitOwnY = j;
                break;
            } else {
                break;
            }
            i--;
            j--;
        }
        
        if (limitOwnX < limitOppX) {
            i=x;
            j=y;
            
            while (i<limitOwnX && j<limitOwnY) {
                board.setBoardXY(i, j, turn);
                i--;
                j--;
            }
        }
    }
    
    private void reverseDownLeft(int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        int limitOwnY = y;
        int limitOppY = y;
        
        int i=x;
        int j=y;
        
        while (i<8 && j>=0) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
                limitOppY = j;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                limitOwnY = j;
                break;
            } else {
                break;
            }
            i++;
            j--;
        }
        
        if (limitOwnX > limitOppX) {
            i=x;
            j=y;
            
            while (i<limitOwnX && j>limitOwnY) {
                board.setBoardXY(i, j, turn);
                i++;
                j--;
            }
        }
    }
    
    private void reverseUpRight(int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        int limitOwnY = y;
        int limitOppY = y;
        
        int i=x;
        int j=y;
        
        while (i>=0 && j<8) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
                limitOppY = j;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                limitOwnY = j;
                break;
            } else {
                break;
            }
            i--;
            j++;
        }
        
        if (limitOwnX < limitOppX) {
            i=x;
            j=y;
            
            while (i>limitOwnX && j<limitOwnY) {
                board.setBoardXY(i, j, turn);
                i--;
                j++;
            }
        }
    }
}
