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
public class CLI implements UI, Runnable {
    
    private final Controller controller;
    private final Model model;
    private final CLIin in;
    private final CLIout out;
    private Thread thread;

   
    public CLI(Controller controller) {
        this.controller = controller;
        this.model = controller.getModel();
        in = new CLIin(controller);
        out = new CLIout(controller);
    }
   
    @Override
    public UIin getUIin() {
        return (UIin) in;
    }

    @Override
    public void run() {
        in.start();
        out.start();
    }
    
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
  
    
}
