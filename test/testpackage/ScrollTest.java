/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author mac
 */
public class ScrollTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        Label li = new Label("First"), fs = new Label("Second");
        
        
        li.setPrefSize(300, 300);
                fs.setPrefSize(300, 300);

        VBox vb = new VBox(20, li, fs);
        
    
        
        ScrollPane sp = new ScrollPane();
        sp.setContent(vb);
         
        VBox box = new VBox(sp);
        box.setPrefSize(800, 600);

       
                 
        Scene sc = new Scene(box, 900, 700);
        stage.setScene(sc);
        stage.show();
        stage.setTitle("Scroll Test");
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
