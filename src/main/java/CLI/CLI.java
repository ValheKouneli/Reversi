/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.controller;

import java.util.Scanner;

/**
 *
 * @author Valhe Kouneli
 */
public class CLI {
    
    private final Model model;
    private final Controller controller;
    private final Scanner reader;
    private final View CLIShowPlayersView;
    private final View CLIGameView;
    private final View CLIChoosePlayerView;
    private View currentView;
    
    public CLI() {
        this.model = new Model();
        this.controller = new Controller(model);
        this.reader = new Scanner(System.in);
        this.CLIShowPlayersView = new CLIShowPlayersView(model, controller);
        this.CLIGameView = new CLIGameView(model);
        this.CLIChoosePlayerView = new CLIChoosePlayerView(controller);
        currentView = new CLIWelcomeView();
    }
    
    public void run() {

        while (true) {
            currentView.show();
            String input = reader.nextLine();
            String nextView = currentView.processInput(input);
            changeView(nextView);
        }
        
        
    }
    
    private void changeView(String control) {
        switch (control) {
            case "show players"         : currentView = CLIShowPlayersView;
                                          break;
            case "choose white player"  : ((CLIChoosePlayerView) CLIChoosePlayerView).setPlayer("WHITE");
                                          currentView = CLIChoosePlayerView;
                                          break;
            case "choose black player"  : ((CLIChoosePlayerView) CLIChoosePlayerView).setPlayer("BLACK");
                                          currentView = CLIChoosePlayerView;
                                          break;
            case "game view"            : currentView = CLIGameView;
                                          break;
            default                     : break;
        }
    }
    
}
