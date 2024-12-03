/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examsubmission;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author mac
 */
public class FormDetails {

    private Label examLabel, surname, firstN, matNo, extendLabel;

    public TextField getTfSurname() {
        return tfSurname;
    }

    public TextField getTfFirst() {
        return tfFirst;
    }

    public TextField getTfMatno() {
        return tfMatno;
    }
    
    private TextField tfSurname, tfFirst, tfMatno;
    ComboBox<String> extension;
    Button reset, submit;

    public BorderPane Details() {

        //LABEL
        examLabel = new Label("Exam Portal");
        surname = new Label("Surname: ");
        firstN = new Label("Firstname: ");
        matNo = new Label("Matriculation Number: ");
        extendLabel = new Label("SELECT COURSE EXTENSION: ");

        //TEXTFIELD
        tfSurname = new TextField();
        tfSurname.setPromptText("Enter surname");

        tfFirst = new TextField();
        tfFirst.setPromptText("Enter firstname");

        tfMatno = new TextField();
        tfMatno.setPromptText("Enter Matriculation number");

        //COMBOBOX
        extension = new ComboBox(FXCollections.observableArrayList("JAVA", "C", "PY"));
        extension.setPromptText("Select extension");

        //BUTTON
        reset = new Button("RESET");
        submit = new Button("START");

        HBox btnHb = new HBox(10, reset, submit);
        btnHb.setAlignment(Pos.CENTER);

        //LABEL FORMAT
        examLabel.setAlignment(Pos.CENTER);
        examLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        examLabel.setStyle("-fx-font:italic bold 40px monospace");

        VBox vs = new VBox(5, surname, tfSurname);
        VBox vf = new VBox(5, firstN, tfFirst);
        VBox vm = new VBox(5, matNo, tfMatno);
        VBox ve = new VBox(5, extendLabel, extension);

        VBox mainVb = new VBox(10, examLabel, vs, vf, vm, ve, btnHb);

        //TEXTFIELD FORMAT
        tfSurname.setPadding(new Insets(10));
        tfFirst.setPadding(new Insets(10));
        tfMatno.setPadding(new Insets(10));

        //COMBOBOX FORMAT
        extension.setPadding(new Insets(5));
        extension.setMaxWidth(Double.MAX_VALUE);

        //BUTTON FORMAT
        reset.setPrefSize(700, 50);
        submit.setPrefSize(700, 50);

        //FORM COMPNENT FORMAT
        mainVb.setAlignment(Pos.CENTER);
        mainVb.setMaxSize(400, 400);

        BorderPane pane = new BorderPane();

        pane.setPadding(new Insets(10));
        pane.setCenter(mainVb);
        
        return pane;
    }

    public void firstNRequestFocus() {
        tfSurname.requestFocus();
    }

    public void resetForm() {
        tfMatno.setText("");
        tfFirst.setText("");
        tfSurname.setText("");
        extension.getSelectionModel().clearSelection();
        tfSurname.requestFocus();
    }

    public boolean isValid() {
        String pattern = "UG/\\d{2}/\\d{4}";
        String input = tfMatno.getText();

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);

        boolean firstNStatus, surnameStatus, matNoStatus, extensionStatus;
        firstNStatus = !tfFirst.getText().isEmpty();

        surnameStatus = !tfSurname.getText().isEmpty();

        matNoStatus = !tfMatno.getText().isEmpty() && matcher.matches();

        extensionStatus = extension.getSelectionModel().getSelectedItem() != null;
        return firstNStatus && surnameStatus && matNoStatus && extensionStatus;

    }
}
