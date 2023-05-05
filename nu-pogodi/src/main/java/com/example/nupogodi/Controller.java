package com.example.nupogodi;

import com.example.nupogodi.stages.Game;
import com.example.nupogodi.stages.LeaderBoard;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    private MediaPlayer mediaPlayer;
    private boolean isMusicOn = false;
    String path = getClass().getResource ("main.mp3").getPath ();


    @FXML
    private Button playButton;

    @FXML
    private Button leaderboardButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button easyButton;

    @FXML
    private Button normalButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button musicButton;

    @FXML
    private void playButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-mode.fxml"));
        Parent root = loader.load();

        // получаем ссылку на текущий Stage
        Stage stage = (Stage) playButton.getScene().getWindow();

        // устанавливаем новый корневой узел для текущей Scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void leaderboardButtonPressed(ActionEvent event) {
        LeaderBoard.display();
    }

    @FXML
    private void exitButtonPressed(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        // получаем ссылку на текущий Stage
        Stage stage = (Stage) easyButton.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }


    //Game Modes
    @FXML
    private void easyButtonPressed(ActionEvent event) {
        Game.start(1);
    }

    @FXML
    private void normalButtonPressed(ActionEvent event) {
        Game.start(2);
    }

    @FXML
    private void hardButtonPressed(ActionEvent event) {
        Game.start(3);
    }

    @FXML
    private void musicButtonPressed(ActionEvent event) {
        if (isMusicOn) {
            mediaPlayer.pause();
            musicButton.setText("Music On");
        } else {
            Media media = new Media (new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            musicButton.setText("Music Off");
        }
        isMusicOn = !isMusicOn;
    }

    public void music() {
        String path = getClass().getResource ("main.mp3").getPath ();
        System.out.println(new File(path).toURI().toString());
        Media media = new Media (new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }


}