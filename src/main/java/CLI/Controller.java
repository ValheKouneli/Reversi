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
public class Controller {
    

    private final View ShowPlayersView;
    private final View GameView;
    private final View ChoosePlayerView;
    private View ChoosePlayersName;
    private final Model model;
    private UI ui;
    
    public Controller() {
        this.model = new Model();
        this.ShowPlayersView = new ShowPlayersView(model);
        this.GameView = new GameView(model);
        this.ChoosePlayerView = new ChoosePlayerView(model);
        
    }
    
    public Controller(Model model) {
        this.model = model;
        this.ShowPlayersView = new ShowPlayersView(model);
        this.GameView = new GameView(model);
        this.ChoosePlayerView = new ChoosePlayerView(model);
    }
    
    public void setUI(UI ui) {
        this.ui = ui;
        this.ChoosePlayersName = new ChoosePlayersName(model, ui.getUIin());
    }
    
    public Model getModel() {
        return model;
    }
    
    public void processInput(String input) {
        changeView(model.getView().processInput(input));
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
            default
                    :   break;
        }
    }
}
