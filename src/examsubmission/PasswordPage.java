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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author mac
 */
public class PasswordPage {

    PasswordField pass;
    Label passError;
    Button passBtn;
    VBox welPage;

    public PasswordPage() {

    }

    public VBox Page() {
        Label welMessage = new Label("EXAM SUBMISSION PORTAL");
        pass = new PasswordField();
        passError = new Label();
        HBox hb = new HBox(5, new Label("Password: "), pass);
        pass.setPromptText("Enter password");
        passBtn = new Button("ENTER");
        passBtn.setPadding(new Insets(10));
        passBtn.setPrefWidth(200);
        passBtn.setMaxHeight(40);

        welPage = new VBox(10, welMessage, hb, passError, passBtn);

        hb.setAlignment(Pos.CENTER);
        VBox.setMargin(hb, new Insets(20));
        welPage.setAlignment(Pos.CENTER);
        welMessage.setStyle("-fx-font-size: 48px; -fx-text-fill: blue;");
        welPage.setStyle("-fx-font-size: 26px;");
        hb.setSpacing(20);
        welPage.setSpacing(20);
        passError.setStyle("-fx-text-fill: red;");

        return welPage;
    }

    private void verifyPassword() {
        String password = pass.getText();
        if ("start exam".equals(password)) {

        } else {
            passError.setText("Incorrect Password Please Try again!!");
            pass.setText("");
        }
    }
}
