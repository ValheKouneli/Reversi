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
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
 
 
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
        stage.setTitle("MCTS Bot vs. Minimax Ai");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Minimax AI search depth");
        yAxis.setLabel("MCTS Bot win %");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<>(xAxis,yAxis);
                
        lineChart.setTitle("MCTS Bot vs. Minimax Ai");

        XYChart.Series series = new XYChart.Series();
        series.setName("Test run results read from file");
        
        File results = new File("raw_data/results.txt");
        try (Scanner input = new Scanner(results)) {
            input.useDelimiter("\\s+");
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
                    throw new java.lang.Exception("There was an odd number of entries"
                            + " in results.txt!");
                }
            }
        } catch (Exception e) {
            System.out.println("Could not open the result file: " + e + "\n" +
                    Arrays.toString(e.getStackTrace()));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
        
//        lineChart.setAnimated(true);
//        WritableImage snapShot = scene.snapshot(null);
//        ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("test.png"));
        
    }
}
