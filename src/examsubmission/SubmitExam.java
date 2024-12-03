/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examsubmission;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.StageStyle;

/**
 *
 * @author mac
 */
public class SubmitExam {

    FormDetails user;
    int questionSet;

    List<TextArea> answerBoxes = new ArrayList<>();

    public SubmitExam(FormDetails user, List<TextArea> answerBoxes, int questionSet) {
        this.user = user;
        this.answerBoxes = answerBoxes;
        this.questionSet = questionSet;
    }

    public void submitAction() {
        for (int i = 0; i < answerBoxes.size(); i++) {
            TextArea answerBox = answerBoxes.get(i);
            String fileName = createFileName(i);
            File file = createFile(fileName);

            if (file == null) {
                System.err.println("Failed to create file for question: " + (i + 1));
                continue;
            }

            writeFile(file, answerBox.getText());
        }
    }

    private String createFileName(int index) {
        String extension = user.extension.getSelectionModel().getSelectedItem().toLowerCase();
        String matno = user.getTfMatno().getText().replace("/", "");
        return user.getTfFirst().getText() + "_"
                + user.getTfSurname().getText() + "_"
                + matno + "_"
                + (index + 1) + "." + extension;
    }

    private File createFile(String fileName) {
        try {
            File file = new File("C:/Submitted/" + user.extension.getSelectionModel().getSelectedItem()
                    + "/" + questionSet + "/" + fileName);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            return file;

        } catch (IOException e) {
            System.err.println("Error creating file: " + fileName + " - " + e.getMessage());
            return null;
        }
    }

    private void writeFile(File file, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + file.getName() + " - " + e.getMessage());
        }
    }

}
