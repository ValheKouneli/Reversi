/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;


import reversi.data_structures.Pair;
import reversi.game.Reversi;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class AI {
    
    private static Pair move;
    
    private AI() {}
    
    public final static int MAX_DEPTH = 7;
    
    public static Pair makeNextMove(Reversi game) {
        move = null;
        minmax(0, game);
        return move;
    }
    
    private static int minmax(int depth, Reversi game) {
//        if (move != null) {
//            System.out.println("pointX: " + move.getX());
//        }
        List<Pair> availableMoves = game.getAvailableMoves();
        
        if (game.getTurn() == 8*8 || availableMoves.isEmpty()) {
            if (game.getScore() > 0) {
                return Integer.MAX_VALUE;
            } else if (game.getScore() < 0) {
                return Integer.MIN_VALUE;
            } else {
                return 0;
            }
        } else if (depth == MAX_DEPTH) {
            return evaluateSituation(game);
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Pair computerMove = null;
        Reversi gameCopy;
        int turn = game.getTurn();
        
        
        //käy kaikki mahdolliset siirrot läpi ja kysy niiden arvo
        //minmaxilta rekursiivisesti ja ota niistä maksimi, jos vuoro on 1
        //tai minimi, jos vuoro on -1
        for (int i=0; i<availableMoves.size(); i++) {
            Pair point = availableMoves.get(i);
            gameCopy = game.getCopy();
            gameCopy.move(point.getX(), point.getY());
            int value = minmax(depth+1, gameCopy);
            
            if (turn == 1) {
                if (value > max) {
                    max = value;
                    if (depth == 0) {
                        computerMove = point;
                    }
                }
            } else {
                if (value < min) {
                    min = value;
                    if (depth == 0) {
                        computerMove = point;
                    }
                }
            }
            if (depth == 0 && i==availableMoves.size()-1 && max < 0) {
                computerMove = point;
            }
        }
        
        if (depth == 0) {
            move = computerMove;
        }
        
        return turn == 1 ? max : min;
    }

    private static int evaluateSituation(Reversi game) {
        return game.getScore();
    }
    
}
