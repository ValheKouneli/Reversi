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
public class Controller {
    

    private final Scanner reader;
    private final View ShowPlayersView;
    private final View GameView;
    private final View ChoosePlayerView;
    private final View ChoosePlayersName;
    private final Model model;
    private boolean keepRunning;
    private final IO io;
    
    public Controller() {
        this.model = new Model();
        this.io = new CLI(model);
        this.reader = new Scanner(System.in);
        this.ShowPlayersView = new ShowPlayersView(model);
        this.GameView = new GameView(model);
        this.ChoosePlayerView = new ChoosePlayerView(model);
        this.ChoosePlayersName = new ChoosePlayersName(model, io);
        keepRunning = true;
    }
    
    public Controller(Model model) {
        this.model = model;
        this.io = new CLI(model);
        this.reader = new Scanner(System.in);
        this.ShowPlayersView = new ShowPlayersView(model);
        this.GameView = new GameView(model);
        this.ChoosePlayerView = new ChoosePlayerView(model);
        this.ChoosePlayersName = new ChoosePlayersName(model, io);
        keepRunning = true;
    }
    
    public void run() {

        while (keepRunning) {
            io.nextOutput();
            if (!model.gameIsInProgress()) {
                String input = io.nextInput();
                String nextView = model.getView().processInput(input);
                changeView(nextView);
            } else {
                model.nextTurn();
            }     
        } 
    }
    
    private void changeView(String control) {
        switch (control) {
            case "show players"
                    :   model.setView(ShowPlayersView);
                        break;
            case "choose white player"
                    :   ((ChoosePlayerView) ChoosePlayerView).setPlayer(1);
                        model.setView(ChoosePlayerView);
                        break;
            case "choose black player"
                    :   ((ChoosePlayerView) ChoosePlayerView).setPlayer(-1);
                        model.setView(ChoosePlayerView);
                        break;
            case "choose WHITE player's name"
                    :   ((ChoosePlayersName) ChoosePlayersName).setPlayerNbr(1);
                        model.setView(ChoosePlayersName);
                        break;
            case "choose BLACK players's name"
                    :   ((ChoosePlayersName) ChoosePlayersName).setPlayerNbr(-1);
                        model.setView(ChoosePlayersName);
                        break;
            case "play"
                    :   model.setView(GameView);
                        break;
            case "game view"
                    :   model.setView(GameView);
                        break;
            case "quit"
                    :   keepRunning = false;
                        break;
            default
                    :   break;
        }
    }
}
