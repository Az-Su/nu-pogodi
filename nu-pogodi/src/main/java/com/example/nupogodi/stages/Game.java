package com.example.nupogodi.stages;

import com.example.nupogodi.Main;
import com.example.nupogodi.exceptions.GameOverException;
import com.example.nupogodi.frames.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Game {
    private static Stage stage;
    private static StackPane layout;
    private static boolean gameOver = false;
    private static int updateInterval = 300;
    private static int addEggStartInterval = 2000;
    private static int addEggMinInterval = 0;
    private static final int width = 720, height = 441;
    private static final String title = "Collect Eggs | Nu Pogodi!";
    private static WolfFrame wolf = null;
    private static LivesFrame lives = null;
    private static ScoreFrame score = null;
    private static ArrayList<EggFrame> eggs = null;

    private static MediaPlayer mediaPlayer;


    public static void start(Integer level) {
        System.out.println("GAME STARTED!");
        lives = new LivesFrame();
        score = new ScoreFrame();
        eggs = new ArrayList<>();
        wolf = new WolfFrame();
        addEggMinInterval = 0;
        addEggStartInterval = 2000;
        updateInterval = 300;
        gameOver = false;

        switch (level) {
            case 1:
                addEggMinInterval = 1500;
                break;
            case 2:
                addEggMinInterval = 1000;
                break;
            case 3:
                addEggMinInterval = 700;
                break;
        }

        //Basic Stage settings
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setResizable(false);

        //Set layout programmatically
        layout = new StackPane();
        layout.setAlignment(Pos.TOP_LEFT);
        Scene scene = new Scene(layout, width, height);

        //Setup background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:assets/frames/background.png", width, height, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT
        );
        layout.setBackground(new Background(backgroundImage));

        //Add children
        layout.getChildren().add(wolf.getImageView());
        layout.getChildren().add(lives.getImageView());
        layout.getChildren().add(score.getLabel());


        //Wolf control KeyCodes
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) wolf.lookUp();
            if (e.getCode() == KeyCode.DOWN) wolf.lookDown();
            if (e.getCode() == KeyCode.LEFT) wolf.lookLeft();
            if (e.getCode() == KeyCode.RIGHT) wolf.lookRight();
        });

        // Add New Eggs
        new Thread(Game::addNewEggs).start();
        new Thread(Game::updateFrames).start();

        stage.setScene(scene);
        stage.showAndWait();
    }

    private static void addNewEggs() {
        int interval = addEggStartInterval;
        while (!gameOver) {
            if (interval > addEggMinInterval) interval -= 60;
            EggFrame egg = new EggFrame(Direction.random());
            Platform.runLater(() -> layout.getChildren().add(egg.getImageView()));
            eggs.add(egg);

            System.out.println(interval);

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private static void updateFrames() {
        while(!gameOver) {
            ArrayList<EggFrame> toDelete = new ArrayList<>();

            for (EggFrame egg : eggs) {
                if (egg.hasNextFrame()) {
                    egg.nextFrame();
                    continue;
                }

                if (wolf.getDirection() == egg.getDirection()) {
                    score.incrementScore();
                    String path = Main.class.getResource ("score.mp3").getPath ();
                    Media media = new Media (new File(path).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                } else {
                    try {
                        lives.decrementLive();
                    } catch (GameOverException e){
                        gameOver = true;
                        stopGame();
                        return;
                    }
                }

                toDelete.add(egg);
                Platform.runLater(() -> layout.getChildren().remove(egg.getImageView()));
            }

            eggs.removeAll(toDelete);

            try {
                Thread.sleep(updateInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void stopGame() {
        Platform.runLater(() -> {
            String path = Main.class.getResource ("zastavka.mp3").getPath ();
            Media media = new Media (new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            GameOver.display(score.getScore());
            mediaPlayer.stop();
            stage.close();
        });
    }
}
