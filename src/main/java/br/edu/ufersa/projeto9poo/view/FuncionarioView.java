package br.edu.ufersa.projeto9poo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FuncionarioView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        trocar(stage);
    }

    public static void trocar(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioView.class.getResource("/br/edu/ufersa/projeto9poo/view/Funcionario.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1158, 768);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Funcionario");
        stage.setScene(scene);
        stage.show();
    }
}
