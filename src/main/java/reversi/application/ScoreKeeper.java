package reversi.application;

import reversi.AI.MCTS.MCTSbot;
import reversi.AI.minimax.MinimaxAI;
import reversi.game.Game;
import reversi.game.Match;
import reversi.game.reversi.Reversi;
/**
 *
 * @author Valhe Kouneli
 */
public class ScoreKeeper {
    
    private int minimaxWins;
    private int mctsBotWins;
    private int totalMatches;
    private long timePerMoveTotalMinimax;
    private long timePerMoveTotalMCTS;
    private final MinimaxAI minimaxAI;
    private final MCTSbot mctsBot;
    private final String minimaxAIName;
    private final String mctsBotName;
    
    public ScoreKeeper(MinimaxAI minimaxAI, MCTSbot mctsBot) {
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
    
    public void playSwitchingColors(boolean print) {
        Match matchA = new Match((Game) new Reversi(), minimaxAI, mctsBot);
        matchA.playMatch(print);
        timePerMoveTotalMinimax += matchA.averageTimePerMovePlayer1();
        timePerMoveTotalMCTS += matchA.averageTimePerMovePlayer2();
        totalMatches++;
        keepScore(matchA.returnWinnerName(), print);
                
        Match matchB = new Match((Game) new Reversi(), mctsBot, minimaxAI);
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
                    System.out.println("Match #" + totalMatches + ", minimaxai won.");
                    break;
                case "mcts":
                    mctsBotWins++;
                    System.out.println("Match #" + totalMatches + ", mctsbot won.");
                    break;
                default:
                    System.out.println("Match #" + totalMatches + ", tied.");
                    break;
            }
    }
    
}
