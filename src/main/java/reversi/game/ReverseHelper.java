/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

import reversi.data_structures.Pair;


/**
 *
 * @author Valhe Kouneli
 */
public class ReverseHelper {
    
    private static final Pair[] DIRECTIONS = {
        new Pair(1,1),     //down right
        new Pair(0,1),     //right
        new Pair(1,0),     //down
        new Pair(-1,-1),   //up left
        new Pair(-1,0),    //up
        new Pair(0,-1),    //left
        new Pair(-1,1),    //up right
        new Pair(1,-1)     //down left
    };
       
    private ReverseHelper() {}
    
    /**
     * Given a move, checks if there are any Reversi pieces to be flipped.
     * If onlyLegalityCheck is false, also flips them.
     * @param board board in question
     * @param turn  whose turn it is
     * @param x x coordinate of the move
     * @param y y coordinate of the move
     * @param onlyLegalityCheck if false, flips pieces on the board as if specified move was played
     * @return true if pieces are to be flipped after specified move, else false
     */
    public static boolean reverse(Board board, int turn, int x, int y, boolean onlyLegalityCheck) {
        boolean oneIsTrue = false;
        
        for (Pair p : DIRECTIONS) {
            if (generalReverse(board, turn, x, y, p.getX(), p.getY(), onlyLegalityCheck)) {
                oneIsTrue = true;
            }
        }
        
        return oneIsTrue;
    }
    
    
    private static boolean generalReverse(Board board, int turn, int x, int y,
            int xDiff, int yDiff, boolean onlyLegalityCheck) {
        
        /*
        * A coordinate of the furthest to-be-flipped opposing piece
        * x or y, depending on which coordinate is changing when we go
        * through the pieces in a certain direction.
        */
        int limitOpp; 
        /*
        * A coordinate of the own piece that is on the other side of
        * the to-be-flipped opposing pieces
        * x or y, depending on which coordinate is changing when we go
        * through the pieces in a certain direction.
        */
        int limitOwn;
        /*
        * A coordinate of the move just played.
        * x or y, depending on which coordinate is changing when we go
        * through the pieces in a certain direction.
        */
        int orig;
        /*
        * A coordinate of the first piece to-be-flipped.
        * x or y depending on which coordinate is changing when we go
        * trough the pieces in a certain direction.
        */
        int iterator;
        /*
        * How much the coordinate that changes, changes, when we go through
        * the pieces in a certain direction. Either -1 or 1.
        */
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
        
        /*
        * Go trough all the places the the direction determined by
        * xDiff and yDiff until reaching the board's end or finding a piece of
        * your own color or an empty place. Save the coordinate when finding
        * opposing pieces or your own piece.
        */
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
        
        /*
        * When going through the chosen direction, if there were first opposing
        * pieces and then your own and no empty between, there were pieces to be
        * flipped. If onlyLegalityCheck is chosen, return true, else false.
        * If onlyLegalityCheck is false, flip the pieces and return true.
        */
        if (limitOpp!=orig && limitOwn!=orig) {
            if (onlyLegalityCheck) {
                return true;
            }
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
}
