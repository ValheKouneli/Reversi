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
public class CLIChoosePlayerView implements View {

    private final Controller controller;
    private String player;
    
    public CLIChoosePlayerView(Controller controller) {
        this.controller = controller;
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
    
    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String processInput(String input) {
        switch (input) {
            case "1"    : return "mcts";
            case "2"    : return "minimax";
            case "3"    : return "human";
            default     : return "";
        }
    }
    
}
