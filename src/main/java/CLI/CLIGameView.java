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
public class CLIGameView implements View {
    
    private final Model model;
    
    public CLIGameView(Model model) {
        this.model = model;
    }
    
    @Override
    public void show() {
        System.out.println(model.toString());
    }

    @Override
    public String name() {
        return "game view";
    }

    @Override
    public String processInput(String input) {
        if (model.gameIsInProgress()) {
            return "";
        } else {
            return "show players";
        }
    }
    
}
