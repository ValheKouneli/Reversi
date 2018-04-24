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
public class ChoosePlayersName implements View {
    
    private final Model model;
    private int playerNbr;
    private String playerColor;
    private final IO io;
    
    public ChoosePlayersName(Model model, IO io) {
        this.model = model;
        this.playerColor = null;
        this.io = io;
    }
    
    public void setPlayerNbr(int playerNbr) {
        this.playerNbr = playerNbr;
        playerColor = playerNbr == 1 ? "WHITE" : "BLACK";
    }
    
    @Override
    public String toString() {
        return "_________________________________________\n" +
                "Choose " + playerColor +" player name:";
    }
    

    @Override
    public String name() {
        return "choose " + playerColor + " player name";
    }

    @Override
    public String processInput(String input) {
        HumanPlayer player = new HumanPlayer(io);
        if (input.length() > 9) {
            input = input.substring(0,10);
        }
        player.setName(input);
        model.setPlayer(playerNbr, player);
        return "show players";
    }
    
}
