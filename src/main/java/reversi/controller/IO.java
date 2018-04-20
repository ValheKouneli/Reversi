/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.controller;

import java.util.Scanner;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class IO {
    
    private final Model model;
    private final Controller controller;
    private final Scanner reader;
    
    public IO() {
        this.model = new Model();
        this.controller = new Controller(model);
        this.reader = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("Welcome. Write a command.");

        while (true) {
            if (!model.gameIsInProgress()) {
                printCommandOptions();
                String nextCommand = reader.nextLine();
                if (nextCommand.matches("quit")) {
                    break;
                }
                int functionNbr = getFunctionNbr(nextCommand);
                controller.implementFunction(functionNbr);
            } else {
                controller.implementFunction(2);
            }
            System.out.println(model.toString());
        }
        
        System.out.println("Quitting.");
    }
    
    private int getFunctionNbr(String command) {
        try {
            return Integer.parseInt(command);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void printCommandOptions() {
        List<String> commands = controller.getCommands();
        for (int i=0; i<commands.size(); i++) {
            System.out.println("[" + i + "] " + commands.get(i));
        }
    }
    
    
    
}
