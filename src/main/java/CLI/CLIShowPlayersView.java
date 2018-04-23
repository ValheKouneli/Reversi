/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.controller;

/**
 *
 * @author Valhe Kouneli
 */
public class CLIShowPlayersView implements View {
    
    private final Model model;
    private final Controller controller;
    
    
    public CLIShowPlayersView(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }
    
    @Override
    public void show() {
        System.out.println("MENU\n" +
                           "=========================================\n" +
                           "WHITE: " + model.getAi1Name() + "\n" +
                           "BLACK: " + model.getAi2Name() + "\n" +
                           "[1] Switch colors\n" +
                           "[2] Choose Player\n" +
                           "[3] Play");    }

    @Override
    public String name() {
        return "show players";
    }

    @Override
    public String processInput(String input) {
        switch (input) {
            case "1"    : return "switch colors";
            case "2"    : return "choose player";
            case "3"    : return "play";
            default     : return "";
        }
    }
}
