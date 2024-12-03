package examsubmission;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 *
 * @author mac
 */
public class Timer {

    private static final int START_SEC = 60; // Starting countdown time in seconds
    private static final int START_MIN = 0;
    private static final int START_HOUR = 0;
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

    boolean timesup;
    SubmitExam submit;
    
   
    public Timer(SubmitExam submit){
        this.submit = submit;
    }
    
    public HBox Timer() {
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

        return timeLabel;
    }

    public void timer() {

        // Update the label text with initial countdown value
        secLabel.setText(String.valueOf(timeSeconds));
        minLabel.setText(String.valueOf(timeMin));
        hourLabel.setText(String.valueOf(timeHour));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (timeSeconds > 0) {
                timeSeconds--;
            } else {
                if (timeMin > 0) {
                    timeMin--;
                    timeSeconds = 59;
                } else if (timeHour > 0) {
                    timeHour--;
                    timeMin = 59;
                    timeSeconds = 59;
                } else {
                    // Time's up
                    timeline.stop();
                    secLabel.setText("0");
                    minLabel.setText("0");
                    hourLabel.setText("0");
                    timesUp();
                    return;
                }
            }

            // Update label text with current time values
            secLabel.setText(String.valueOf(timeSeconds));
            minLabel.setText(String.valueOf(timeMin));
            hourLabel.setText(String.valueOf(timeHour));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void timesUp() {
        timesup = true;
        if (timesup == true) {
           submit.submitAction();
        } else {
            
        }
    }

}
