/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI;

import java.util.ArrayList;
import java.util.List;
import reversi.game.Board;
import reversi.game.Game;
import reversi.game.ReverseHelper;

/**
 *
 * @author Valhe Kouneli
 */
public class AI {
    
    public final int MAX_DEPTH = 5;
    
    public void makeNextMove(Game game) {
        minmax(0, game);
    }
    
    private int minmax(int depth, Game game) {
        List<Point> availableMoves = game.getAvailableMoves();
        
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
        Point computerMove = null;
        Game gameCopy;
        int turn = game.getTurn();
        
        
        //käy kaikki mahdolliset siirrot läpi ja kysy niiden arvo
        //minmaxilta rekursiivisesti ja ota niistä maksimi, jos vuoro on 1
        //tai minimi, jos vuoro on -1
        for (int i=0; i<availableMoves.size(); i++) {
            Point point = availableMoves.get(i);
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
            game.move(computerMove.getX(), computerMove.getY());
        }
        
        return turn == 1 ? max : min;
    }

    private int evaluateSituation(Game game) {
        return game.getScore();
    }
    
}
