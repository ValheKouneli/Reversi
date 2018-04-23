/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import reversi.AI.Game;
import reversi.AI.MCTS.MCTSbot;
import reversi.AI.MinimaxAI;
import reversi.AI.ReversiEvaluator2;
import reversi.game.Reversi;
import reversi.AI.Player;

/**
 *
 * @author Valhe Kouneli
 */
public class Model {
    
    private Game game;
    private boolean gameInProgress;
    private Player player1;
    private Player player2;
    
    public Model() {
        game = new Reversi();
        player1 = new MCTSbot(1000);
        player2 = new MinimaxAI(new ReversiEvaluator2());
        gameInProgress = false;
    }
    
    public void toggleGameInProgress() {
        gameInProgress = !gameInProgress;
    }
    
    public void gameOn() {
        gameInProgress = true;
    }
    
    public void gameOff() {
        gameInProgress = false;
    }

    public boolean gameIsInProgress() {
        return gameInProgress;
    }
    
    public String getPlayer1Name() {
        return player1.name();
    }
    
    public String getPlayer2Name() {
        return player2.name();
    }
    
    public void newGame() {
        game = new Reversi();
        gameInProgress = false;
    }
    
    public void nextTurn() {
        if (!game.gameIsOver()) {
            if (game.getTurn() == 1) {
                game.move(player1.getNextMove(game));
            } else {
                game.move(player2.getNextMove(game));
            }          
        } else {
            gameInProgress = false;
        }   
    }
    
    public void switchPlayers() {
        Player temp = player1;
        player1 = player2;
        player2 = temp;
    }
    
    public void setPlayer(int color, Player player) {
        if (color == 1) {
            player1 = player;
        } else {
            player2 = player;
        }
    }
    
 
    public void setAI(int color, String type) {
        if (color == 1) {
            switch (type) {
                case "MCTSbot"      : player1 = new MCTSbot();
                                      break;
                case "MinimaxAI"    : player1 = new MinimaxAI(new ReversiEvaluator2());
                                      break;
                default             : break;
            }
        } else if (color == -1) {
            switch (type) {
                case "MCTSbot"      : player2 = new MCTSbot();
                                      break;
                case "MinimaxAI"    : player2 = new MinimaxAI(new ReversiEvaluator2());
                                      break;
                default             : break;
            }
        }
    }
    
    @Override
    public String toString() {
        String temp = "_________________________________________\n";
        if (!gameInProgress) {
            temp += "___________GAME IS ON PAUSE______________\n" +
                    "_________________________________________\n";
        }
        temp += "WHITE: " + player1.name() + "\n";
        temp += "BLACK: " + player2.name() + "\n\n";
        temp += game.toString();
        return temp;
    }
    
    
}
