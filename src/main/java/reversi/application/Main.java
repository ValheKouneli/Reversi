package reversi.application;

import reversi.AI.MCTS.MCTSbot;
import reversi.AI.minimax.MinimaxAI;
import reversi.game.Game;
import reversi.game.Match;
import reversi.game.Player;
import reversi.game.reversi.Reversi;
import reversi.game.reversi.ReversiEvaluator2;



/**
 *
 * @author Valhe Kouneli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Player minimaxAI = new MinimaxAI(new ReversiEvaluator2(), 5);
        Game reversi = new Reversi();
        Match timingMatch = new Match(reversi, minimaxAI, minimaxAI);
        timingMatch.playMatch();
        long avgMoveTime = timingMatch.averageTimePerMove();
        System.out.println("Minimaxai käytti syvyydellä 5 keskimäärin aikaa " +
                avgMoveTime + " ms per siirto.");
        
        Player mctsbot = new MCTSbot((int) avgMoveTime);
        
        int minimaxaiWinsAsWhite = 0;
        int mctsbotWinsAsBlack = 0;
        int ties = 0;
        int nbrOfMatches = 5;
        
        for (int i=0; i<nbrOfMatches; i++) {
            Game newGame = new Reversi();
            Match match = new Match(newGame, minimaxAI, mctsbot);
            match.playMatch();
            switch (match.returnWinner()) {
                case 1:
                    minimaxaiWinsAsWhite++;
                    System.out.println("Match #" + (i+1) + ", minimaxai won.");
                    break;
                case -1:
                    mctsbotWinsAsBlack++;
                    System.out.println("Match #" + (i+1) + ", mctsbot won.");
                    break;
                default:
                    ties++;
                    System.out.println("Match #" + (i+1) + ", tied.");
                    break;
            }
        }
        
        System.out.println("Minimaxai won as white " + minimaxaiWinsAsWhite +
                " time(s) out of " + nbrOfMatches + " and mctsbot won as black " + mctsbotWinsAsBlack +
                " time(s) ouf of " + nbrOfMatches + ". There were " + ties + " ties.");

    }
    
}
