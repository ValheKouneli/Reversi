/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.controller;

import reversi.AI.AI;
import reversi.AI.Game;
import reversi.AI.MCTS.MCTSbot;
import reversi.AI.MinimaxAI;
import reversi.AI.ReversiEvaluator2;
import reversi.game.Reversi;

/**
 *
 * @author Valhe Kouneli
 */
public class Model {
    
    private Game game;
    private boolean gameInProgress;
    private AI ai1;
    private AI ai2;
    private String ai1name;
    private String ai2name;
    
    public Model() {
        game = new Reversi();
        ai1 = new MCTSbot(1000);
        ai2 = new MinimaxAI(new ReversiEvaluator2());
        gameInProgress = false;
    }
    
    public void toggleGameInProgress() {
        gameInProgress = !gameInProgress;
    }

    public boolean gameIsInProgress() {
        return gameInProgress;
    }
    
    public String getAi1Name() {
        return ai1name;
    }
    
    public String getAi2Name() {
        return ai2name;
    }
    
    public void newGame() {
        game = new Reversi();
        gameInProgress = false;
    }
    
    public void nextTurn() {
        if (!game.gameIsOver()) {
            if (game.getTurn() == 1) {
                game.move(ai1.getNextMove(game));
            } else {
                game.move(ai2.getNextMove(game));
            }          
        } else {
            gameInProgress = false;
        }   
    }
    
    public Game getGame() {
        return game;
    }
    
}
