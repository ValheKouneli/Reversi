/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

/**
 *
 * @author Valhe Kouneli
 */
public class ShowPlayersView implements View {
    
    private final Model model;
    
    
    public ShowPlayersView(Model model) {
        this.model = model;
    }
    
    @Override
    public String toString() {
        return "MENU\n" +
                           "=========================================\n" +
                           "WHITE: " + model.getPlayer1Name() + "\n" +
                           "BLACK: " + model.getPlayer2Name() + "\n" +
                           "[1] Switch colors\n" +
                           "[2] Choose white player\n" +
                           "[3] Choose black player\n" +
                           "[4] Play\n" +
                           "[5] Quit\n";
    }

    @Override
    public String name() {
        return "show players";
    }

    @Override
    public String processInput(String input) {
        switch (input) {
            case "1"    :   model.switchPlayers();
                            return "";
            case "2"    :   return "choose white player";
            case "3"    :   return "choose black player";
            case "4"    :   model.gameOn();
                            return "play";
            case "5"    :   return "quit";
            case "quit" :   return "quit";
            default     :   return "";
        }
    }
}
