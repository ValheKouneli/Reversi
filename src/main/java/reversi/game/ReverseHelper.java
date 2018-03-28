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
    
    private static boolean generalReverse(Board board, int turn, int x, int y,
            int xDiff, int yDiff) {
        
        int limitOpp;
        int limitOwn;
        int orig;
        int iterator;
        int iteratorDiff;
        if (xDiff != 0) {
            limitOpp = x;
            limitOwn = x;
            orig = x;
            iterator = x+xDiff;
            iteratorDiff = xDiff;
        } else {
            limitOpp = y;
            limitOwn = y;
            orig = y;
            iterator = y+yDiff;
            iteratorDiff = yDiff;
        }
        
        int i = x+xDiff;
        int j = y+yDiff;
        
        while (0<=i && i<8 && 0<=j && j<8) {
            if (board.getBoardXY(i, j) == turn*(-1)) {
                if (xDiff != 0) {
                    limitOpp = i;
                } else {
                    limitOpp = j;
                }
                
            } else if (board.getBoardXY(i, j) == turn) {
                if (xDiff != 0) {
                    limitOwn = i;
                } else {
                    limitOwn = j;
                }
                break;
            } else {
                break;
            }
            i+=xDiff;
            j+=yDiff;
        }
        
        if (limitOpp!=orig && limitOwn!=orig) {
            i = x+xDiff;
            j = y+yDiff;
            while (iterator != limitOwn) {
                board.setBoardXY(i, j, turn);
                i+=xDiff;
                j+=yDiff;
                iterator+=iteratorDiff;
            }
            return true; 
        } else {
            return false;
        }

    }
    
    public static boolean reverseUp(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, -1, 0);
    }
    
    public static boolean reverseLeft(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, 0, -1);
    }
    
    public static boolean reverseDown(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, 1, 0);
    }
    
    public static boolean reverseRight(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, 0, 1);
    }
    
    public static boolean reverseDownRight(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, 1, 1);
    }
    
    public static boolean reverseUpLeft(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, -1, -1);
    }
    
    public static boolean reverseDownLeft(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, 1, -1);
    }
    
    public static boolean reverseUpRight(Board board, int turn, int x, int y) {
        return generalReverse(board, turn, x, y, -1, 1);
    }
    
}
