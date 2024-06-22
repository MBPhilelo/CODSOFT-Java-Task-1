/**
 * 
 */

/**
 * @author HP
 *
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    private int randomNumber;
    private int attempts;
    private int maxAttempts = 10;
    private int score = 0;
    private Label feedbackLabel;
    private TextField guessField;
    private Button guessButton;
    private Button newGameButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Guessing Game");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        feedbackLabel = new Label("Guess a number between 1 and 100:");
        guessField = new TextField();
        guessButton = new Button("Guess");
        newGameButton = new Button("New Game");

        guessButton.setOnAction(e -> checkGuess());
        newGameButton.setOnAction(e -> startNewGame());

        layout.getChildren().addAll(feedbackLabel, guessField, guessButton, newGameButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        startNewGame();
    }

    private void startNewGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("Guess a number between 1 and 100:");
        guessField.clear();
        guessButton.setDisable(false);
        newGameButton.setDisable(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            if (guess < randomNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (guess > randomNumber) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                feedbackLabel.setText("Correct! You guessed the number in " + attempts + " attempts.");
                score++;
                endGame();
            }
            if (attempts >= maxAttempts) {
                feedbackLabel.setText("Out of attempts! The number was " + randomNumber + ".");
                endGame();
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number.");
        }
        guessField.clear();
    }

    private void endGame() {
        guessButton.setDisable(true);
        newGameButton.setDisable(false);
    }
}

