/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game;

/**
 *
 * @author Valhe Kouneli
 */
public class Match {
    
    private final Player player1;
    private final Player player2;
    private final Game game;
    private int timeSpentPlayer1;
    private int timeSpentPlayer2;
    private int timeSpentTotal;
    private Player winner;
    
    public Match(Game game, Player player1, Player player2) {
        this.game = game;
        timeSpentTotal = 0;
        winner = null;
        this.player1 = player1;
        this.player2 = player2;
        timeSpentPlayer1 = 0;
        timeSpentPlayer2 = 0;
    }
    
    public void playMatch() {
        while (!game.gameIsOver()) {
            Player playerInTurn = game.getTurn() == 1 ? player1 : player2;
            game.move(playerInTurn.getNextMove(game));
        }
        switch (game.winner()) {
            case 1:
                winner = player1;
                break;
            case -1:
                winner = player2;
                break;
            default:
                winner = null;
                break;
        }
    }
    

    
}
