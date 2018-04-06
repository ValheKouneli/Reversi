/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.application;

import static java.lang.Integer.parseInt;
import java.util.Scanner;
import reversi.AI.AI;
import reversi.data_structures.Pair;
import reversi.game.Game;

/**
 *
 * @author Valhe Kouneli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        Game game = new Game();
 
        Scanner reader = new Scanner(System.in);
        int i = 0;
        Pair move;

        do {
            System.out.println(game.toString());
            System.out.println("It's " + getPlayer(game.getTurn()) + "'s turn.");
            if (game.getTurn() == 1) {
                move = AI.makeNextMove(game);
            } else {
                move = AI.makeNextMove(game);
            }
            if (move != null) {
                game.move(move.getX(), move.getY());
            } else {
                System.out.println("Apua, error");
                break;
            }
            i++;
        } while (!game.getAvailableMoves().isEmpty());
        
        System.out.println(game.toString());
        int score = game.getScore();
        System.out.println("Winner is " + getPlayer(score) + " with " + score + " points.");
    }
    
    private static void humanGetMove(Game game, Scanner reader) {
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
