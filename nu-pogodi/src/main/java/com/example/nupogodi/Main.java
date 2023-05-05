package com.example.nupogodi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
    final String title = "Nu Pogodi!";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        //Size of main background image
        int width = 512, height = 303;

        FXMLLoader fxmlTest = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlTest.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);

        stage.show();

    }
}
