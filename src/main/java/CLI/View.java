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
public interface View {
    public void show();
    public String name();
    /**
     * Responds to user input and applies changes on the Model.
     * @param input user's input
     * @return the next view
     */
    public String processInput(String input);
}
