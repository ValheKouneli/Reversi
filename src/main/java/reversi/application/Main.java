package reversi.application;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        final int DEFAULT_MAX_DEPTH = 5;
        final int DEFAULT_MIN_DEPTH = 4;
        final int DEFAULT_MATCH_NUMBER = 10;
        int maxDepth = DEFAULT_MAX_DEPTH;
        int minDepth = DEFAULT_MIN_DEPTH;
        int matchNumber = DEFAULT_MATCH_NUMBER;
        
        /*
        * Handle args
        */
        
        switch (args.length) {
        //currently set minDepth, maxDepth and matchNumber are ok
            case 0:
                break;
            case 1:
                try {
                    maxDepth = Integer.parseInt(args[0]);
                    if (maxDepth<DEFAULT_MIN_DEPTH) {
                        throw new IllegalArgumentException("Max depth (given "
                                + "argument) must be at least " +
                                DEFAULT_MIN_DEPTH + ".");
                    }
                } catch (NumberFormatException e) {
                    respondToBadArguments();
                }   break;
            case 2:
                try {
                    minDepth = Integer.parseInt(args[0]);
                    if (minDepth<1 ) {
                        throw new IllegalArgumentException("Min depth (1st "
                                + "argument) must be at least 1.");
                    }
                    maxDepth = Integer.parseInt(args[1]);
                    if (maxDepth<minDepth) {
                        throw new IllegalArgumentException("Max depth (2rd "
                                + "argument) must be greater than min depth "
                                + "(1st argument).");
                    }
                } catch (NumberFormatException e) {
                    respondToBadArguments();
                }   break;
            case 3:
                try {
                    minDepth = Integer.parseInt(args[0]);
                    if (minDepth<1 ) {
                        throw new IllegalArgumentException("Min depth (1st "
                                + "argument) must be at least 1.");
                    }
                    maxDepth = Integer.parseInt(args[1]);
                    if (maxDepth<minDepth) {
                        throw new IllegalArgumentException("Max depth (2rd "
                                + "argument) must be greater than min depth "
                                + "(1st argument).");
                    }
                    matchNumber = Integer.parseInt(args[2]);
                    if (matchNumber < 2 || matchNumber%2 == 1) {
                        throw new IllegalArgumentException("Match number (3rd "
                                + "argument) must be even and at least 2.");
                    }
                } catch (NumberFormatException e) {
                    respondToBadArguments();
                }   break;
            default:
                respondToBadArguments();
                break;
        }
        
        playBotsAgainstEachOtherAndRecord(minDepth, maxDepth, matchNumber);
            
        printLatestResults();
        
        javafx.application.Application.launch(Chart.class);

    }
    
    /*
    * Go through all different depths and make Minimax AI and MCTS Bot
    * play against each other 'matchNumber' amount of times.
    */
    private static void playBotsAgainstEachOtherAndRecord(int minDepth, 
            int maxDepth, int matchNumber) {

        try {
            PrintWriter writerNew = new PrintWriter(new BufferedWriter(
                new FileWriter("raw_data/latest_results.txt", false)));
            PrintWriter writerAppend = new PrintWriter(new BufferedWriter(
                new FileWriter("raw_data/legacy_results.txt", true)));
            
            for (int depth = minDepth; depth<=maxDepth; depth++) {
                //create MinimaxBot
                MinimaxAI minimax = new MinimaxAI(new ReversiEvaluator(), depth);
                //create 'timing' to record MinimaxBot's average move time
                RecordMoveTimeMinimax timing = new RecordMoveTimeMinimax(minimax);

                int avgTime = timing.record(true);
                //print Minimax Bot's avg move time
                System.out.println(timing.toString());

                //create MCTS Bot
                MCTSBot mcts = new MCTSBot(avgTime);

                //create 'score' for keeping score on
                //how the bots perform against each other
                ScoreKeeper score = new ScoreKeeper(minimax, mcts);

                //play 'matchNumber' of matches
                for (int i=0; i<matchNumber/2; i++) {
                    score.playSwitchingColors(true);
                }

                //prints the results for this depth
                System.out.println(score.toString());
                
                //writes the results in file
                int winPercentage = score.getMctsBotWins() * 100 / 
                        score.getTotalMatches();
                
                record(writerNew, matchNumber, depth, winPercentage);
                record(writerAppend, matchNumber, depth, winPercentage);
            }
            
            writerNew.close();
            writerAppend.close();
        } catch (IOException e) {
            System.out.println("Writing to file did not succeed.\n +"
                    + e.toString());
            System.exit(1);
        } 
    }
    
    private static void printLatestResults() {
        System.out.println("MCTS Bot win percentages\n" +
                "against Minimax Bot with\n");
        
        File results = new File("raw_data/latest_results.txt");
        if(results.isFile()) { 
            try {
                Scanner input = new Scanner(results);
                input.useDelimiter("\\s+");

                try {
                    while(input.hasNextInt()) {
                        input.nextInt();
                        System.out.println("depth " + input.nextInt() + ": " +
                        "win percentage " + input.nextInt() + "%");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Result file formatting is corrupted.");
                }
 
            } catch (FileNotFoundException e) {
                System.out.println("Could not find the result file.\n" +
                        e.toString());
            }
        } else {
            throw new java.lang.IllegalStateException("There should be a "
                    + "result file to read.");
        }
    }
    
    private static void respondToBadArguments() {
        String instructions = "Program takes 0-3 arguments. "
                        + "Arguments must be integers.";
        throw new IllegalArgumentException(instructions);
    }

    /*
    * writes results to file with given writer
    */
    private static void record(PrintWriter writer, 
            int matchNumber, int depth, int winPercentage) {
        writer.print(Integer.toString(matchNumber));
        writer.print(" ");
        writer.print(Integer.toString((depth)));
        writer.print(" ");
        writer.print(Integer.toString(winPercentage));
        writer.print("\n");
    }
    
}
