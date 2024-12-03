/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

/**
 *
 * @author mac
 */


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WorldClock extends Application {

    private static final int START_TIME = 60; // Starting countdown time in seconds
    private int timeSeconds = START_TIME;
    private Label timeLabel;
    private Timeline timeline; // Define timeline as a class-level variable

    @Override
    public void start(Stage primaryStage) {
        timeLabel = new Label();
        timeLabel.setStyle("-fx-font-size: 48px;");

        // Set up the layout
        StackPane root = new StackPane(timeLabel);
        Scene scene = new Scene(root, 300, 200);

        // Update the label text with initial countdown value
        timeLabel.setText(String.valueOf(timeSeconds));

        // Initialize the timeline here
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            timeLabel.setText(String.valueOf(timeSeconds));
            if (timeSeconds <= 0) {
                timeline.stop();
                timeLabel.setText("Time's up!");
            }
        }));
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

