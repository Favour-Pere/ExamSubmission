package examsubmission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class QuestionsPanel {

    private final List<TextArea> answerBoxes = new ArrayList<>();
    private final List<ImageView> imageQuestions = new ArrayList<>();

    private Button previousButton;
    private Button nextButton;
    private FormDetails user;
    private Button btn;

    private final BorderPane container = new BorderPane();
    private final GridPane questionPane = new GridPane();

    private int currentIndex = 0;
    private int randomIndex;

    public QuestionsPanel(FormDetails user, Button btn) {
        this.user = user;
        this.btn = btn;
        initializeUI();
    }

    public int getRandomIndex() {
        return randomIndex;
    }

    public List<TextArea> getAnswerBoxes() {
        return answerBoxes;
    }

    // Initialize questions
    public void initializeQuestions() {
        String filePath = "TASK";
        try {
            File file = new File(filePath);
            File[] directories = file.listFiles(File::isDirectory);

            if (directories == null || directories.length == 0) {
                throw new Exception("No directories found in the specified path.");
            }

            Random random = new Random();
            randomIndex = random.nextInt(directories.length);
            File selectedDirectory = directories[randomIndex];

            File[] questionFiles = selectedDirectory.listFiles(fileItem -> fileItem.getName().endsWith(".png") || fileItem.getName().endsWith(".jpg"));

            if (questionFiles == null || questionFiles.length == 0) {
                throw new Exception("No image files found in the selected directory.");
            }

            for (File questionFile : questionFiles) {
                String imagePath = questionFile.getPath();
                Image image = new Image("file:" + imagePath);
                ImageView imageView = new ImageView(image);

                imageView.setPreserveRatio(false);
                imageView.setFitWidth(1200);
                imageView.setFitHeight(300);
                imageQuestions.add(imageView);

                TextArea textArea = new TextArea();
                textArea.setPromptText("Enter your code here...");
                textArea.setPrefColumnCount(25);
                textArea.setPrefRowCount(20);
                textArea.setWrapText(true);
                answerBoxes.add(textArea);
            }

            updateQuestionPane();
            SubmitExam submit = new SubmitExam(user, answerBoxes, randomIndex);

            btn.setOnAction(event -> {
                boolean confirmed = showConfirmationDialog("Confirmation", "Do you want to proceed?");
                if (confirmed) {
                    submit.submitAction();
                } else {
                    return;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void initializeUI() {
        previousButton = new Button("Previous");
        nextButton = new Button("Next");

        previousButton.setPrefSize(150, 40);
        nextButton.setPrefSize(150, 40);

        previousButton.setOnAction(event -> navigateToPreviousQuestion());
        nextButton.setOnAction(event -> navigateToNextQuestion());

        HBox navigationButtons = new HBox(20, previousButton, nextButton);
        navigationButtons.setAlignment(Pos.CENTER);

        container.setCenter(questionPane);
        container.setBottom(navigationButtons);
        BorderPane.setMargin(navigationButtons, new Insets(0, 0, 30, 0));
        
        
//        container.setPadding(new Insets(20));
//        container.getChildren().addAll(questionPane, navigationButtons);
    }

    private void updateQuestionPane() {
        questionPane.getChildren().clear();
        Region spacer = new Region(); // Optional spacer for consistent alignment
        spacer.setMinHeight(20);
//        questionPane.getChildren().addAll(imageQuestions.get(currentIndex), answerBoxes.get(currentIndex));
        questionPane.addRow(0, imageQuestions.get(currentIndex));
        questionPane.addRow(1, answerBoxes.get(currentIndex));
        GridPane.setHalignment(imageQuestions.get(currentIndex), HPos.CENTER);
        GridPane.setValignment(imageQuestions.get(currentIndex), VPos.CENTER);
        GridPane.setHalignment(answerBoxes.get(currentIndex), HPos.CENTER);
        GridPane.setValignment(answerBoxes.get(currentIndex), VPos.CENTER);
        questionPane.maxHeight(500);
        questionPane.setAlignment(Pos.CENTER);
        questionPane.setHgap(10);
        questionPane.setVgap(10);
        questionPane.setPadding(new Insets(10));
        
 
        previousButton.setDisable(currentIndex == 0);
        nextButton.setDisable(currentIndex == imageQuestions.size() - 1);
        answerBoxes.get(currentIndex).requestFocus();
    }

    private void navigateToPreviousQuestion() {
        if (currentIndex > 0) {
            currentIndex--;
            updateQuestionPane();
        }
    }

    private void navigateToNextQuestion() {
        if (currentIndex < imageQuestions.size() - 1) {
            currentIndex++;
            updateQuestionPane();
        }
    }

    public BorderPane getUI() {
        return container;
    }

    public boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.initStyle(StageStyle.UTILITY); // Optional: Use a utility window style
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setResizable(false); // Prevent resizing

        // Show the dialog and wait for a response
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        return result == ButtonType.YES;
    }

}
