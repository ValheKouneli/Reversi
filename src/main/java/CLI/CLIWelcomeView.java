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
public class CLIWelcomeView implements View {

    public CLIWelcomeView() {
        
    }
    
    @Override
    public String toString() {
        return "WELCOME TO PLAY REVERSI\n" +
               "=========================================\n" +
               "Press [enter] to continue\n";
    }

    @Override
    public String name() {
        return "welcome";
    }

    @Override
    public String processInput(String input) {
        return "show players";
    }
    
}
