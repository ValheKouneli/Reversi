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
    
    Game game;
    private boolean gameInProgress;
    private boolean displayBoard;
    private AI ai1;
    private AI ai2;
    private String ai1name;
    private String ai2name;
    private String textOnDisplay;
    
    public Model() {
        game = new Reversi();
        ai1 = new MCTSbot(1000);
        ai2 = new MinimaxAI(new ReversiEvaluator2());
        textOnDisplay = "";
        gameInProgress = false;
        displayBoard = false;
    }
    
    public void toggleGameInProgress() {
        gameInProgress = !gameInProgress;
        textOnDisplay = "";
    }
    
    public void toggleDisplayBoard() {
        displayBoard = !displayBoard;
    }
    
    public String getTextOnDisplay() {
        return textOnDisplay;
    }
    
    public boolean gameIsInProgress() {
        return gameInProgress;
    }
    
    
    public void newGame() {
        game = new Reversi();
        gameInProgress = false;
        textOnDisplay = "";
        displayBoard = true;
    }
    
    public void nextTurn() {
        if (!game.gameIsOver()) {
            if (game.getTurn() == 1) {
                game.move(ai1.getNextMove(game));
            } else {
                game.move(ai2.getNextMove(game));
            }          
        } else {
            textOnDisplay = "Game finished.";
            gameInProgress = false;
            displayBoard = false;
        }   
    }
    
    @Override
    public String toString() {
        String temp = "";
        if (displayBoard) {
            temp += textOnDisplay + "\n" + game.toString();
        } else {
            temp += textOnDisplay;
        }
        return temp;
    }
    
    
}
