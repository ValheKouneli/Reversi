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
public class ChoosePlayerView implements View {

    
    private int playerNbr;
    private String player;
    private final Model model;
    
    public ChoosePlayerView(Model model) {
        this.model = model;
    }
    
    @Override
    public String toString() {
        return "MENU: Choose " + player + " Player\n" +
                           "=========================================\n" +
                           "[1] Monte Carlo Tree Search Bot\n" +
                           "[2] MinimaxAI\n" +
                           "[3] Human\n" +
                           "[4] go back\n";
    }

    @Override
    public String name() {
        return "choose player";
    }
    
    public void setPlayer(int playerNbr) {
        this.playerNbr = playerNbr;
        player = playerNbr == 1 ? "WHITE" : "BLACK";
    }

    @Override
    public String processInput(String input) {
        switch (input) {
            case "1"    :   model.setAI(playerNbr, "MCTSbot");
                            return "show players";
            case "2"    :   model.setAI(playerNbr, "MinimaxAI");
                            return "show players";
            case "3"    :   return "choose " + player + " player's name";
            case "4"    :   return "show players";
            default     :   return "";
        }
    }
    
}
