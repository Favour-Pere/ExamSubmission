/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examsubmission;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author mac
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        
        FormDetails userDetail = new FormDetails();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        StackPane sp = new StackPane();
        sp.getChildren().add(userDetail.Details());

        Button submitExam = new Button("SUBMIT");

        userDetail.reset.setOnAction(e -> userDetail.resetForm());
        userDetail.submit.setOnAction(e -> {
            if (userDetail.isValid()) {
                ExamPanel examPanel = new ExamPanel(userDetail, submitExam);
                sp.getChildren().clear();
                sp.getChildren().add(examPanel.Panel());
            }
        });
        Scene scene = new Scene(sp, screenBounds.getWidth(), screenBounds.getHeight());

        stage.setScene(scene);
        userDetail.firstNRequestFocus();
        stage.setTitle("SUBMIT CODE");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
