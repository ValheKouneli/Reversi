/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.application;

import reversi.AI.minimax.MinimaxAI;
import reversi.game.Match;
import reversi.game.reversi.Reversi;

/**
 *
 * @author Valhe Kouneli
 */
public class RecordMoveTimeMinimax {
    
    private final MinimaxAI minimax;
    private boolean recordingDone;
    private int avgTime;
            
    public RecordMoveTimeMinimax(MinimaxAI minimax) {
        this.minimax = minimax;
        recordingDone = false;
        avgTime = 0;
    }
    
    public int record() {
        return record(false);
    }
    
    public int record(boolean print) {
        return record(print, false);
    }
    
    public int record(boolean print, boolean printProgress) {
        if (print) {
            System.out.println("Minimax is playing against itself. This might take a while.");
        }
        Reversi game = new Reversi();
        Match match = new Match("Minimax with depth " +
                minimax.getDepth() + " against itself", game, minimax, minimax);
        match.playMatch(print, printProgress);
        avgTime = (int) match.averageTimePerMove();
        recordingDone = true;
        return avgTime;
    }
    
    public int avgTime() {
        if (!recordingDone) {
            throw new java.lang.IllegalStateException(
                    "Recording has not been done yet.");
        }
        return avgTime;
    }
    
    public String getMinimaxName() {
        return minimax.name();
    }
    
    public int getMinimaxDepth() {
        return minimax.getDepth();
    }
    
    @Override
    public String toString() {
        if (recordingDone) {
            return minimax.name() + " has an average move time of " + 
                    avgTime + " ms.";
        } else {
            return "Recording average move time has not yet been done to " + 
                    minimax.name() + ".";
        }
    }
}
