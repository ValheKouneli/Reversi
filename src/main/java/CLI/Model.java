/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import reversi.AI.Game;
import reversi.AI.MCTS.MCTSbot;
import reversi.AI.MinimaxAI;
import reversi.AI.MinimaxAITimeLimited;
import reversi.AI.ReversiEvaluator2;
import reversi.game.Reversi;
import reversi.AI.Player;
import reversi.data_structures.IntPair;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class Model {
    
    private Game game;
    private boolean gameInProgress;
    private Player player1;
    private Player player2;
    private View currentView;
    private boolean changed;
    private boolean waitingForHumanMove;
    
    public Model() {
        game = new Reversi();
        player1 = new MCTSbot(1000);
        player2 = new MinimaxAITimeLimited(new ReversiEvaluator2());
        gameInProgress = false;
        currentView = new WelcomeView();
        changed = true;
    }
    
    public void setChangedToFalse() {
        changed = false;
    }
    
    public boolean getChanged() {
        return changed;
    }
    
    public void toggleGameInProgress() {
        gameInProgress = !gameInProgress;
        changed = true;
    }
    
    public void setView(View view) {
        currentView = view;
        changed = true;
    }
    
    public View getView() {
        return currentView;
    }
    
    public boolean gameHasEnded() {
        return game.gameIsOver();
    }
    
    public void gameOn() {
        gameInProgress = true;
        changed = true;
    }
    
    public void gameOff() {
        gameInProgress = false;
        changed = true;
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
    
    public List<IntPair> getLegalMoves() {
        return game.getMoves();
    }

    public void newGame() {
        game = new Reversi();
        gameInProgress = false;
        changed = true;
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
        changed = true;
    }
    
    public void setWaitingForHumanMove(boolean value) {
        waitingForHumanMove = value;
    }
    
    public boolean getWaitingForHumanMove() {
        return waitingForHumanMove;
    }
    
    public void switchPlayers() {
        Player temp = player1;
        player1 = player2;
        player2 = temp;
        changed = true;
    }
    
    public void setPlayer(int color, Player player) {
        if (color == 1) {
            player1 = player;
        } else {
            player2 = player;
        }
        changed = true;
    }
    
 
    public void setAI(int color, String type) {
        if (color == 1) {
            switch (type) {
                case "MCTSbot"      : player1 = new MCTSbot();
                                      break;
                case "MinimaxAI"    : player1 = new MinimaxAI(new ReversiEvaluator2());
                                      break;
                case "MinimaxTL"    : player1 = new MinimaxAITimeLimited(new ReversiEvaluator2());
                                      break;
                default             : break;
            }
        } else if (color == -1) {
            switch (type) {
                case "MCTSbot"      : player2 = new MCTSbot();
                                      break;
                case "MinimaxAI"    : player2 = new MinimaxAI(new ReversiEvaluator2());
                                      break;
                case "MinimaxTL"    : player1 = new MinimaxAITimeLimited(new ReversiEvaluator2());
                                      break;
                default             : break;
            }
        }
        changed = true;
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
