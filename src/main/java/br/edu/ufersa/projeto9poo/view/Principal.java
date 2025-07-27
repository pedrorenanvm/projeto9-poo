package br.edu.ufersa.projeto9poo.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label message = new Label();
        message.setText("Meu primeiro programa usando o javaFX no projeto");
        Scene scene = new Scene(message, 300, 150);
        stage.setTitle("JavaFx teste");
        stage.setScene(scene);
        stage.show();
    }
}
