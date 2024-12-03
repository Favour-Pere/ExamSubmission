/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimer extends Application {

    private static final int START_SEC = 60; // Starting countdown time in seconds
    private static final int START_MIN = 60;
    private static final int START_HOUR = 3;
    private int timeSeconds = START_SEC;
    private int timeMin = START_MIN;
    private int timeHour = START_HOUR;

    private Label secLabel;
    private Label minLabel;
    private Label hourLabel;
    private Label colon;
     private Label colon2;
    private HBox timeLabel;
    
    private Timeline timeline; // Define timeline as a class-level variable

    @Override
    public void start(Stage primaryStage) {

        secLabel = new Label();
        minLabel = new Label();
        hourLabel = new Label();
        colon = new Label(":");
        colon2 = new Label(":");

        secLabel.setStyle("-fx-font-size: 48px;");
        minLabel.setStyle("-fx-font-size: 48px;");
        hourLabel.setStyle("-fx-font-size: 48px;");
        colon.setStyle("-fx-font-size: 48px;");
        colon2.setStyle("-fx-font-size: 48px;");
        timeLabel = new HBox(10, hourLabel, colon, minLabel, colon2, secLabel);
        // Set up the layout
        StackPane root = new StackPane(timeLabel);
        Scene scene = new Scene(root, 300, 200);

        // Update the label text with initial countdown value
        secLabel.setText(String.valueOf(timeSeconds));
        minLabel.setText(String.valueOf(timeMin));
        hourLabel.setText(String.valueOf(timeHour));

        // Initialize the timeline here
        timeline = new Timeline(new KeyFrame(Duration.hours(1), event -> {
            timeHour--;
            hourLabel.setText(String.valueOf(timeHour));
             
        }), new KeyFrame(Duration.minutes(1), event -> {
            timeMin--;
            minLabel.setText(String.valueOf(timeMin));
         
        }), new KeyFrame(Duration.seconds(2), event -> {
            timeSeconds--;
            secLabel.setText(String.valueOf(timeSeconds));
         
        })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        primaryStage.setTitle("Countdown Clock");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
