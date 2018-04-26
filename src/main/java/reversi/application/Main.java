/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.application;

import CLI.CLI;
import CLI.Controller;
import CLI.Model;
import CLI.UI;


/**
 *
 * @author Valhe Kouneli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Model model = new Model();
        Controller controller = new Controller(model);
        UI ui = (UI) new CLI(controller);
        controller.setUI(ui);
        ui.start();

    }
    
}
