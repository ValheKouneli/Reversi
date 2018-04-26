/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import java.util.Scanner;
import reversi.AI.Game;
import reversi.data_structures.IntPair;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class CLIin implements Runnable, UIin {

    private final Controller controller;
    private final Model model;
    private Scanner reader;
    private Thread thread;

    public CLIin(Controller controller) {
        this.model = controller.getModel();
        this.controller = controller;
        this.reader = new Scanner(System.in);
    }
    
    public void setScanner(Scanner reader) {
        this.reader = reader;
    }
    
    @Override
    public void run() {
        String input = reader.nextLine();
        while (input.equalsIgnoreCase("quit")) {
            controller.processInput(input);
            input = reader.nextLine();
        }
    }
    
    @Override
    public IntPair nextMove() {
        IntPair move = null;
        int y = -1;
        int x = -1;
        boolean properMoveGiven = false;
        
        while (!properMoveGiven) {
            boolean properRowGiven = false;
            while (!properRowGiven) {
                System.out.println("Give row:");
                String input = reader.nextLine();
                y = validateInput(input);
                if (y!=-1) {
                    properRowGiven = true;
                }
            }

            boolean properColumnGiven = false;
            while (!properColumnGiven) {
                System.out.println("Give column:");
                String input = reader.nextLine();
                x = validateInput(input);
                if (x!=-1) {
                    properColumnGiven = true;
                }
            }

            move = new IntPair(x, y);
            List<IntPair> legalMoves = model.getLegalMoves();
            if (legalMoves.contains(move)) {
                return move;
            } else {
                System.out.println("[r " + y + ", c " + x + "] is not a legal move.");
            }
        }
        return move; //should never come here  
    }
    
    private int validateInput(String input) {
        int k;
        try {
                k = Integer.parseInt(input);
            } catch (NumberFormatException a) {
                System.out.println("Not a valid number.");
                return -1;
            } catch (NullPointerException a) {
                System.out.println("You must choose a coordinate.");
                return -1;
            }
            if (0<=k && k<= 7) {
                return k;
            } else {
                System.out.println("Give a number in range 0–7.");
                return -1;
            }  
    }
    
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    

    
    
    
}
