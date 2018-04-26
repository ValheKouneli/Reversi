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
public class CLIout implements Runnable {
    private final Model model;
    private Thread thread;
    
    public CLIout(Controller controller) {
        this.model = controller.getModel();
    }
    
    @Override
    public void run() {
        while (true) {
            if (model.getChanged()) {
                System.out.println(model.getView().toString());
                //model.setChangedToFalse();
                if (model.gameIsInProgress()) {
                    model.nextTurn();
                }
            }
        }
    }
    
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
