package reversi.application;

import reversi.AI.MCTS.MCTSBot;
import reversi.AI.minimax.MinimaxAI;
import reversi.game.Game;
import reversi.game.reversi.Reversi;
/**
 * Makes minimax AI an MCTS bot play matches against each other
 * and keeps record of how the matches went and how long they took per move
 * on average.
 * @author Valhe Kouneli
 */
public class ScoreKeeper {
    
    private int minimaxWins;
    private int mctsBotWins;
    private int totalMatches;
    private long timePerMoveTotalMinimax;
    private long timePerMoveTotalMCTS;
    private final MinimaxAI minimaxAI;
    private final MCTSBot mctsBot;
    private final String minimaxAIName;
    private final String mctsBotName;
    
    public ScoreKeeper(MinimaxAI minimaxAI, MCTSBot mctsBot) {
        minimaxWins = 0;
        mctsBotWins = 0;
        totalMatches = 0;
        this.minimaxAI = minimaxAI;
        this.mctsBot = mctsBot;
        minimaxAIName = minimaxAI.name();
        mctsBotName = mctsBot.name();
    }
    
    public int getMinimaxWins() {
        return minimaxWins;
    }
    
    public int getMctsBotWins() {
        return mctsBotWins;
    }
    
    public int getTotalMatches() {
        return totalMatches;
    }
    
    public String getMinimaxAIName() {
        return minimaxAIName;
    }
    
    public String getMctsBotName() {
        return mctsBotName;
    }
    
    public int getTimePerMoveMinimax() {
        if (totalMatches == 0) {
            throw new java.lang.IllegalStateException(
                    "Avg time/move asked before any matches are played.");
        }
        return (int) timePerMoveTotalMinimax / totalMatches;
    }
    
    public int getTimePerMoveMCTS() {
        if (totalMatches == 0) {
            throw new java.lang.IllegalStateException(
                    "Avg time/move asked before any matches are played.");
        }
        return (int) timePerMoveTotalMCTS / totalMatches;
    }
    
    /**
     * Plays two matches between the minimax AI and the MCTS bot;
     * one where minimax AI is white and one where MCTS bot is white;
     * saves results.
     * @param print the matches or not
     */
    public void playSwitchingColors(boolean print) {
        Match matchA = new Match("Match no " + (totalMatches + 1) +
                " comparing MCTS Bot and Minimax AI, depth " +
                + minimaxAI.getDepth(), (Game) new Reversi(), 
                minimaxAI, mctsBot);
        matchA.playMatch(print);
        timePerMoveTotalMinimax += matchA.averageTimePerMovePlayer1();
        timePerMoveTotalMCTS += matchA.averageTimePerMovePlayer2();
        totalMatches++;
        keepScore(matchA.returnWinnerName(), print);
                
        Match matchB = new Match("Match no " + (totalMatches + 1) +
                " comparing MCTS Bot and Minimax AI, depth " +
                + minimaxAI.getDepth(), (Game) new Reversi(), 
                mctsBot, minimaxAI);
        matchB.playMatch(print);
        timePerMoveTotalMinimax += matchA.averageTimePerMovePlayer2();
        timePerMoveTotalMCTS += matchA.averageTimePerMovePlayer1();
        totalMatches++;
        keepScore(matchB.returnWinnerName(), print);
    }
    
    private void keepScore(String winnerName, boolean print) {
        String winner;
        if (winnerName.equals(minimaxAIName)) {
            winner = "minimax";
        } else if (winnerName.equals(mctsBotName)) {
            winner = "mcts";
        } else {
            winner = "no winner";
        }
        
            switch (winner) {
                case "minimax" :
                    minimaxWins++;
                    if (print) {
                        System.out.println("Match #" + totalMatches + ", minimaxai won.");
                    }
                    break;
                case "mcts":
                    mctsBotWins++;
                    if (print) {
                        System.out.println("Match #" + totalMatches + ", mctsbot won.");
                    }
                    break;
                default:
                    if (print) {
                        System.out.println("Match #" + totalMatches + ", tied.");
                    }
                    break;
            }
    }
    
    /**
     * @return the statistics and results of the matches played between the bots, represented as a string
     */
    @Override
    public String toString() {
        if (totalMatches != 0) {
            return "Victories out of " + totalMatches + " matches:\n" +
                    mctsBot.name() + " : " + (int) (mctsBotWins * 100 / totalMatches) + 
                    "%\n" +
                    minimaxAI.name() + " : " + (int) (minimaxWins * 100 / totalMatches) + 
                    "%\n";
        } else {
            return "Bots " + mctsBot.name() + " and " + minimaxAI.name() +
                    " have not been played any matches " +
                    "against each other yet.\n" +
                    "No score has yet been calculated.";
        }
    }
    
}
