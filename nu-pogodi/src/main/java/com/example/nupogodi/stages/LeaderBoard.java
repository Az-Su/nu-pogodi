package com.example.nupogodi.stages;

import com.example.nupogodi.models.HighScore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LeaderBoard {
    public static void display() {
        Stage stage = new Stage();
        stage.setResizable(false);


        int width = 669, height = 450;
        stage.initModality(Modality.APPLICATION_MODAL);
        String title = "The Leaderboard | Nu Pogodi!";
        stage.setTitle(title);

        ArrayList<HighScore> highScores = HighScore.load();

        VBox layout = new VBox();
        Scene scene = new Scene(layout, width, height);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE);
        });

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:assets/images/leaderboard.png", width, height, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT
        );
        layout.setPadding(new Insets(0, 64, 0, 0));
        layout.setAlignment(Pos.BASELINE_RIGHT);
        layout.setBackground(new Background(backgroundImage));
        Font font = Font.font("Futura", FontWeight.BOLD, 28);

        for (HighScore highScore : highScores) {
            Label label = new Label(highScore.getPerson() + ": " + highScore.getScore());
            label.setFont(font);
            layout.getChildren().add(label);
        }

        stage.setScene(scene);
        stage.showAndWait();
    }

    private static Button buildMenuButton(String text) {
        Button button = new Button();
        button.setText(text);
        button.setMinWidth(180);
        button.setFont(new Font("Futura", 20));
        return button;
    }
}
