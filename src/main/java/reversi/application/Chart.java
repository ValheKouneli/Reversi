package reversi.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import reversi.data_structures.IntPair;
 
 
public class Chart extends Application {
    
    public static final CountDownLatch LATCH = new CountDownLatch(1);
    public static Chart chart = null;
 
    @Override public void start(Stage stage) throws Exception {

        //set title
        stage.setTitle("MCTS Bot vs. Minimax Ai");

        //defining the axises
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Minimax AI search depth");
        yAxis.setLabel("MCTS Bot win %");

        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("MCTS Bot vs. Minimax Ai");

        //initialize data series
        XYChart.Series series = new XYChart.Series();
        series.setName("MCTS Bot win percentage against "
                + "Minimax AI related to minimax search depth");
        
        HashMap<Integer, IntPair> collectedData;
        collectedData = new HashMap<>();
        
        try {
            File results = new File("raw_data_legacy_results.txt");
            
            try {
                Scanner input = new Scanner(results);
                input.useDelimiter("\\s+");

                /*
                * Read actual data from file. Chart data
                */

                int weight;
                int depth;
                int winPercentage;

                try {
                    while(input.hasNextInt()) {
                        //System.out.println("Reading data...");
                        weight = input.nextInt();
                        depth = input.nextInt();
                        winPercentage = input.nextInt();
                        //System.out.println("Read weight " + weight + ", " +
                        //        depth + ", " + winPercentage + "%");
                        if (!collectedData.keySet().contains(depth)) {
                            //System.out.println("Inserting new depth");
                            IntPair data = 
                                    new IntPair((Integer) weight, weight*winPercentage);
                            collectedData.put(depth, data);
                        } else {
                            //System.out.println("Updating new depth");
                            IntPair data = collectedData.get(depth);
                            collectedData.put((Integer) depth, 
                                    new IntPair((int)data.getX()+weight, 
                                            data.getY()+weight*winPercentage));
                        }
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Result file formatting is corrupted.\n"
                            + "No chart is being drawn.");
                    System.exit(1);
                }


            } catch (FileNotFoundException e) {
                System.out.println("Result file was not found.\n" + 
                        e.toString());
                System.exit(1);
            }
        
        } catch (Exception e) {
            System.out.println("Could not create a file.\\n"
                    + e.toString());
            System.exit(1);
        }
        
        int avgPercentage;
       
        for (Integer key : collectedData.keySet()) {
            avgPercentage = collectedData.get(key).getY() /
                    collectedData.get(key).getX();
            //System.out.println("Charting value pair x: " + key +
            //       ", y: " + avgPercentage);
            series.getData().add(new XYChart.Data(key, avgPercentage));
        }


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }
}
