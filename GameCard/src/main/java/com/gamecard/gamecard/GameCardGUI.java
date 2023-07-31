package com.gamecard.gamecard;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * Create by mnadz
 * On        28/7/2023
 */
public class GameCardGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("game-card.fxml"));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Card Game GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
