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
public class WaitingForHumanMove implements View {

    private final Controller controller;
    
    public WaitingForHumanMove(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public String name() {
        return "waiting for human move";
    }

    @Override
    public String processInput(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
