/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.application;

import static java.lang.Integer.parseInt;
import java.util.Scanner;
import reversi.AI.*;
import reversi.AI.MCTS.MonteCarloTreeSearch;
import reversi.data_structures.Pair;
import reversi.game.Reversi;

/**
 *
 * @author Valhe Kouneli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        Reversi game = new Reversi();
        MonteCarloTreeSearch ai1 = new MonteCarloTreeSearch(1, -1);
        AI ai2 = new AI(new ReversiEvaluator2());
 
        Scanner reader = new Scanner(System.in);
        int i = 0;
        Pair move;

        do {
            System.out.println(game.toString());
            System.out.println("It's " + getPlayer(game.getTurn()) + "'s turn.");
            if (game.getTurn() == 1) {
                ai1.makeNextMove(game);
            } else {
                ai2.makeNextMove(game);
            }
            i++;
        } while (!game.getMoves().isEmpty());
        
        System.out.println(game.toString());
        int score = game.getScore();
        int points = score < 0 ? score*-1 : score;
        System.out.println("Winner is " + getPlayer(score) + " with " + points + " points.");
    }
    
    private static void humanGetMove(Reversi game, Scanner reader) {
        String input = null;
        int x;
        int y;
        
        do {
            do {
                System.out.println("Give row.");
                input = reader.nextLine();
            } while (isCoordinate(input));
            x = parseInt(input);
            do {
                System.out.println("Give column.");
                input = reader.nextLine();
            } while (isCoordinate(input));
            y = parseInt(input);
        } while (!game.move(x, y));
    }
    
    private static String getPlayer(int i) {
        if (i<0) {
            return "black";
        } else if (i>0) {
            return "white";
        } else {
            return "no one";
        }
    }
    
    private static boolean isCoordinate(String s) {  
        return s != null && s.matches("[01234567]\n");  
    }
    
}
