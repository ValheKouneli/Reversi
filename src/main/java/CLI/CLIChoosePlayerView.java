/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import java.util.Scanner;

/**
 *
 * @author Valhe Kouneli
 */
public class CLIChoosePlayerView implements View {

    
    private int playerNbr;
    private String player;
    private final Model model;
    private final Scanner reader;
    
    public CLIChoosePlayerView(Model model, Scanner reader) {
        this.reader = reader;
        this.model = model;
    }
    
    @Override
    public void show() {
        System.out.println("MENU: Choose " + player + " Player\n" +
                           "=========================================\n" +
                           "[1] Monte Carlo Tree Search Bot\n" +
                           "[2] MinimaxAI\n" +
                           "[3] Human\n" +
                           "[4] go back\n");
    }

    @Override
    public String name() {
        return "choose player";
    }
    
    public void setPlayer(int playerNbr) {
        this.playerNbr = playerNbr;
        player = playerNbr == 1 ? "WHITE" : "BLACK";
    }

    /**
     * Responds to user input and applies changes on the Model.
     * @param input user's input
     * @return the next view
     */
    @Override
    public String processInput(String input) {
        switch (input) {
            case "1"    :   model.setAI(playerNbr, "MCTSbot");
                            return "show players";
            case "2"    :   model.setAI(playerNbr, "MinimaxAI");
                            return "show players";
            case "3"    :   CLIHumanPlayer human = new CLIHumanPlayer(reader);
                            model.setPlayer(playerNbr, human);
                            System.out.println("Give player's name:");
                            human.setName(reader.nextLine().substring(0,10));
                            System.out.println();
                            return "show players";
            case "4"    :   return "show players";
            default     :   return "";
        }
    }
    
}
