package com.example.nupogodi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    final String title = "Nu Pogodi!";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {


        //Size of main background image
        int width = 512, height = 303;
//        music();
        FXMLLoader fxmlTest = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlTest.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);

        stage.show();

    }

//    MediaPlayer mediaPlayer;
//    public void music() {
//        String path = getClass().getResource ("main.mp3").getPath ();
//        Media media = new Media (new File(path).toURI().toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayer.play();
//
//    }
}
