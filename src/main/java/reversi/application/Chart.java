package reversi.application;

import java.io.File;
import java.util.Arrays;
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
 
 
public class Chart extends Application {
    
    public static final CountDownLatch LATCH = new CountDownLatch(1);
    public static Chart chart = null;
    
    public static Chart waitForChart() {
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            System.out.println("Something unextected happened: \n" + e.toString());
        }
        return chart;
    }
 
    @Override public void start(Stage stage) throws Exception {
        
        try {
            File results = new File("raw_data/results.txt");
            
            try {
                Scanner input = new Scanner(results);
                input.useDelimiter("\\s+");

                /*
                * Reads min x vale from file. Value is not used atm.
                */
                int minX = 0;
                if (input.hasNextInt()) {
                    minX = input.nextInt();
                }

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

                /*
                * Read actual data from file. Chart data
                */
                int x;
                int y;
                int next;

                while(input.hasNextInt()) {
                    next = input.nextInt();
                    x = next;
                    if (input.hasNextInt()) {
                        next = input.nextInt();
                        y = next;
                        series.getData().add(new XYChart.Data(x, y));
                    } else {
                        //should never happen
                        throw new java.lang.Exception("There was an odd "
                                + "number of entries in results.txt!");
                    }
                }

                Scene scene  = new Scene(lineChart,800,600);
                lineChart.getData().add(series);

                stage.setScene(scene);
                stage.setOnCloseRequest((WindowEvent t) -> {
                    Platform.exit();
                    System.exit(0);
                });
                stage.show();

            } catch (Exception e) {
                System.out.println("Could not open the result file: " + e + "\n" +
                        Arrays.toString(e.getStackTrace()));
            }
        
        } catch (Exception e) {
            System.out.println("Could not create a file.\\n"
                    + e.toString());
        }
    }
}
