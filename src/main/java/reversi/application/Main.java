package reversi.application;

import reversi.AI.MCTS.MCTSBot;
import reversi.AI.minimax.MinimaxAI;
import reversi.game.reversi.ReversiEvaluator;




/**
 *
 * @author Valhe Kouneli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for (int depth = 3; depth<10; depth++) {
            MinimaxAI minimax = new MinimaxAI(new ReversiEvaluator(), depth);
            RecordMoveTimeMinimax timing = new RecordMoveTimeMinimax(minimax);
            int avgTime = timing.record();
            System.out.println(timing.toString());
            MCTSBot mcts = new MCTSBot(avgTime);
            ScoreKeeper score = new ScoreKeeper(minimax, mcts);
            for (int i=0; i<5; i++) {
                score.playSwitchingColors(false);
            }
            System.out.println(score.toString());
        }

    }
    
}
