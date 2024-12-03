/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examsubmission;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author mac
 */
public class NavBar {
    private Button btn;
    private SubmitExam submit;
    private final Timer timer;
    public NavBar(Button btn, SubmitExam submit){
        this.btn = btn;
        this.submit = submit;
        this.timer = new Timer(submit);
    }

    public HBox Nav(String fullName, String matricNo, String course) {

        VBox user = new VBox(15, new Label(fullName), new Label(matricNo), new Label(course));
        user.setAlignment(Pos.CENTER);
        user.setPadding(new Insets(20));
        VBox.setMargin(user, new Insets(10));
        
//        Timer timer = new Timer(submit);
        
      
        btn.setAlignment(Pos.CENTER);
        btn.setPrefSize(200, 50);
        
        HBox nav = new HBox(350, user, timer.Timer(), btn);
        nav.setAlignment(Pos.CENTER);

        timer.timer();
        return nav;
    }
}
