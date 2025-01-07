/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examsubmission;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author mac
 */
public class ExamPanel {

    private FormDetails userDetail;
    private Button btn;

    public ExamPanel(FormDetails userDetail, Button btn) {
        this.userDetail = userDetail;
        this.btn = btn;
    }

    public BorderPane Panel() {
        // Create an instance of QuestionsPanel
        QuestionsPanel questionsPanel = new QuestionsPanel(userDetail, btn);

        SubmitExam submitExam = new SubmitExam(userDetail, questionsPanel.getAnswerBoxes(), questionsPanel.getRandomIndex());
        NavBar nb = new NavBar(btn, submitExam);
        String fullName = userDetail.getTfFirst().getText() + " " + userDetail.getTfSurname().getText();
        String matricNo = userDetail.getTfMatno().getText();
        String course = userDetail.extension.getSelectionModel().getSelectedItem();

        BorderPane bp = new BorderPane();
        bp.setTop(nb.Nav(fullName, matricNo, course));

        // Initialize questions
        questionsPanel.initializeQuestions();
        bp.setCenter(questionsPanel.getUI());
        BorderPane.setAlignment(questionsPanel.getUI(), Pos.TOP_CENTER);

        return bp;
    }
}
