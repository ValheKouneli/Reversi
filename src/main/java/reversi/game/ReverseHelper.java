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
public class ReverseHelper {
    
    
    private ReverseHelper() {}
    
    public static boolean reverseUp(Board board, int turn, int x, int y) {
        int limitOpp = x;
        int limitOwn = x;
        for (int i=x-1; i>=0; i--) {
            if (board.getBoardXY(i, y) == turn*(-1)) {
                limitOpp = i;
            } else if (board.getBoardXY(i, y) == turn) {
                limitOwn = i;
                break;
            } else {
                break;
            }
        }
        
        if (limitOpp<x && limitOwn<x) {
            for (int i=x-1; i>limitOwn; i--) {
                board.setBoardXY(i, y, turn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean reverseLeft(Board board, int turn, int x, int y) {
        int limitOpp = y;
        int limitOwn = y;
        for (int j=y-1; j>=0; j--) {
            if (board.getBoardXY(x, j) == turn*(-1)) {
                limitOpp = j;
            } else if (board.getBoardXY(x, j) == turn) {
                limitOwn = j;
                break;
            } else {
                break;
            }
        }
        
        if (limitOwn<y && limitOpp<y) {
            for (int j=y-1; j>limitOwn; j--) {
                board.setBoardXY(x, j, turn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean reverseDown(Board board, int turn, int x, int y) {
        int limitOpp = x;
        int limitOwn = x;
        for (int i=x+1; i<8; i++) {
            if (board.getBoardXY(i, y) == turn*(-1)) {
                limitOpp = i;
            } else if (board.getBoardXY(i, y) == turn) {
                limitOwn = i;
                break;
            } else {
                break;
            }
        }
        
        if (limitOwn>x && limitOpp>x) {
            for (int i=x+1; i<limitOwn; i++) {
                board.setBoardXY(i, y, turn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean reverseRight(Board board, int turn, int x, int y) {
        int limitOpp = y;
        int limitOwn = y;
        
        for (int j=y+1; j<8; j++) {
            if (board.getBoardXY(x, j) == turn*(-1)) {
                limitOpp = j;
            } else if (board.getBoardXY(x, j) == turn) {
                limitOwn = j;
                break;
            } else {
                break;
            }
        }
        
        if (limitOwn>y && limitOpp>y) {
            for (int j=y+1; j<limitOwn; j++) {
                board.setBoardXY(x, j, turn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean reverseDownRight(Board board, int turn, int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        
        int i=x+1;
        int j=y+1;
        
        while (i<8 && j<8) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                break;
            } else {
                break;
            }
            i++;
            j++;
        }
        
        if (limitOwnX>x && limitOppX>x) {
            i=x+1;
            j=y+1;
            
            while (i<limitOwnX) {
                board.setBoardXY(i, j, turn);
                i++;
                j++;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean reverseUpLeft(Board board, int turn, int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        
        int i=x-1;
        int j=y-1;
        
        while (i>=0 && j>=0) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                break;
            } else {
                break;
            }
            i--;
            j--;
        }
        
        if (limitOwnX<x && limitOppX<x) {
            i=x-1;
            j=y-1;
            
            while (i>limitOwnX) {
                board.setBoardXY(i, j, turn);
                i--;
                j--;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean reverseDownLeft(Board board, int turn, int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        
        int i=x+1;
        int j=y-1;
        
        while (i<8 && j>=0) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                break;
            } else {
                break;
            }
            i++;
            j--;
        }
        
        if (limitOwnX>x && limitOppX>x) {
            i=x+1;
            j=y-1;
            
            while (i<limitOwnX) {
                board.setBoardXY(i, j, turn);
                i++;
                j--;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean reverseUpRight(Board board, int turn, int x, int y) {
        int limitOwnX = x;
        int limitOppX = x;
        
        int i=x-1;
        int j=y+1;
        
        while (i>=0 && j<8) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                limitOppX = i;
            } else if (board.getBoardXY(i, j) == turn) {
                limitOwnX = i;
                break;
            } else {
                break;
            }
            i--;
            j++;
        }
        
        if (limitOwnX<x && limitOppX<x) {
            i=x-1;
            j=y+1;
            
            while (i>limitOwnX) {
                board.setBoardXY(i, j, turn);
                i--;
                j++;
            }
            return true;
        } else {
            return false;
        }
    }
    
}
