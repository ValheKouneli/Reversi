package reversi.application;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import reversi.AI.MCTS.MCTSBot;
import reversi.AI.minimax.MinimaxAI;
import reversi.data_structures.IntPair;
import reversi.data_structures.List;
import reversi.game.reversi.ReversiEvaluator;




/**
 *
 * @author Valhe Kouneli
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        final int DEFAULT_MAX_DEPTH = 3;
        final int DEFAULT_MIN_DEPTH = 2;
        int maxDepth = DEFAULT_MAX_DEPTH;
        int minDepth = DEFAULT_MIN_DEPTH;
        
        if (args.length >= 2) {
            try {
                maxDepth = Integer.parseInt(args[1]);
                if (maxDepth<DEFAULT_MIN_DEPTH) {
                    maxDepth = DEFAULT_MIN_DEPTH;
                }
            } catch (NumberFormatException e) {
                
            }
        }
        List<IntPair> record = new List<>();
        
        for (int depth = DEFAULT_MIN_DEPTH; depth<=maxDepth; depth++) {
            MinimaxAI minimax = new MinimaxAI(new ReversiEvaluator(), depth);
            RecordMoveTimeMinimax timing = new RecordMoveTimeMinimax(minimax);
            int avgTime = timing.record(true);
            System.out.println(timing.toString());
            MCTSBot mcts = new MCTSBot(avgTime);
            ScoreKeeper score = new ScoreKeeper(minimax, mcts);
            for (int i=0; i<5; i++) {
                score.playSwitchingColors(true);
            }
            System.out.println(score.toString());
            record.add(new IntPair(depth, score.getMctsBotWins() * 100 / 
                    score.getTotalMatches()));

        }
        
        System.out.println("MCTS Bot win percentages\n" +
                "against Minimax Bot with\n");
        int depth;
        int winPercentage;
        for (int i=0; i<record.size(); i++) {
            depth = record.get(i).getX();
            winPercentage = record.get(i).getY();
            System.out.println("depth " + depth + ": " + winPercentage + "%");
        }
  
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("raw_data/results.txt", false)));) {
            writer.print(Integer.toString(minDepth));
            writer.println("\n");
            
            for (int i=0; i<record.size(); i++) {
                writer.print(Integer.toString(record.get(i).getX()));
                writer.print(" ");
                writer.print(Integer.toString(record.get(i).getY()));
                writer.print("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Writing to file did not succeed.");
        }

        
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(Chart.class);
            }
        }.start();
        Chart chart = Chart.waitForChart();
    }
    
}
