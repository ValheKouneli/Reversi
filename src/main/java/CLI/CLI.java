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
public class CLI implements View {
    

    private final Scanner reader;
    private final View CLIShowPlayersView;
    private final View CLIGameView;
    private final View CLIChoosePlayerView;
    private View currentView;
    private final Model model;
    private boolean keepRunning;
    
    public CLI() {
        this.model = new Model();
        this.reader = new Scanner(System.in);
        this.CLIShowPlayersView = new CLIShowPlayersView(model);
        this.CLIGameView = new CLIGameView(model);
        this.CLIChoosePlayerView = new CLIChoosePlayerView(model, reader);
        currentView = new CLIWelcomeView();
        keepRunning = true;
    }
    
    public CLI(Model model) {
        this.model = model;
        this.reader = new Scanner(System.in);
        this.CLIShowPlayersView = new CLIShowPlayersView(model);
        this.CLIGameView = new CLIGameView(model);
        this.CLIChoosePlayerView = new CLIChoosePlayerView(model, reader);
        currentView = new CLIWelcomeView();
        keepRunning = true;
    }
    
    public void run() {

        while (keepRunning) {
            toString();
            if (!model.gameIsInProgress()) {
                String input = reader.nextLine();
                String nextView = currentView.processInput(input);
                changeView(nextView);
            } else {
                model.nextTurn();
            }
            
        }
        
        
    }
    
    private void changeView(String control) {
        switch (control) {
            case "show players"         : currentView = CLIShowPlayersView;
                                          break;
            case "choose white player"  : ((CLIChoosePlayerView) CLIChoosePlayerView).setPlayer(1);
                                          currentView = CLIChoosePlayerView;
                                          break;
            case "choose black player"  : ((CLIChoosePlayerView) CLIChoosePlayerView).setPlayer(-1);
                                          currentView = CLIChoosePlayerView;
                                          break;
            case "play"                 : currentView = CLIGameView;
                                          break;
            case "game view"            : currentView = CLIGameView;
                                          break;
            case "quit"                 : keepRunning = false;
                                          break;
            default                     : break;
        }
    }

    @Override
    public String toString() {
        return currentView.toString();
    }

    @Override
    public String name() {
        return "CLI";
    }

    @Override
    public String processInput(String input) {
        String nextView = currentView.processInput(input);
        changeView(nextView);
        return "";
    }
    
}
