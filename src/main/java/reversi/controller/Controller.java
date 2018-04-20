/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.controller;
import reversi.data_structures.List;
import reversi.data_structures.Pair;


/**
 *
 * @author Valhe Kouneli
 */
public class Controller {
    
    private final Model model;
    
    private List<Pair<String, Function>> functions;
    
    public Controller(Model model) {
        this.model = model;
        populateFunctions();
    }
    
    public void implementFunction(int i) {
        if (0<=i && i<functions.size()) {
            functions.get(i).getSecond().implement();
        } else {
            //model says erronous command
        }   
    }

    public List<String> getCommands() {
        List<String> commandList = new List<>();
        for (int i=0; i<functions.size(); i++) {
            commandList.add(functions.get(i).getFirst());
        }
        return commandList;
    }
   
    
    private void populateFunctions() {
        functions = new List<>();
        functions.add(new Pair("Play", (Function) () -> {playMatch();}));
        functions.add(new Pair("continue", (Function) () -> {nextTurn();}));
        functions.add(new Pair("new game", (Function) () -> {newGame();}));
    }
    
    private void newGame() {
        model.newGame();
    }
    
    private void setAI() {
        if (model.gameIsInProgress()) {
            
        } else {
            
        }
    }
   
    private void nextTurn() {
        model.nextTurn();
    }
    
    
    private void playMatch() {
        if (!model.gameIsInProgress()) {
            model.toggleGameInProgress();
        }
    }
    
    private void pause() {
        
    }
}
