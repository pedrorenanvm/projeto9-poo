package br.edu.ufersa.projeto9poo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioView.class.getResource("/br/edu/ufersa/projeto9poo/view/MenuPrincipal.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1366, 768  );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
}