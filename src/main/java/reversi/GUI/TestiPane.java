/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.GUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TestiPane extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane ruutu = new Pane();
        ruutu.setPrefSize(300, 200);
        stage.setTitle("TestiPane");
        Circle circle = new Circle(30, 50, 10);
        ruutu.getChildren().add(circle);
  
        Scene scene = new Scene(ruutu);
        stage.setScene(scene);
        stage.show();
        
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("mouse click detected! "+event.getSource());
            if (circle.contains(event.getX(), event.getY())) {
                System.out.println("inside circle!");
            }
        }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
