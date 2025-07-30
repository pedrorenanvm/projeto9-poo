package br.edu.ufersa.projeto9poo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteCadastroView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        abrirTelaCadastroCliente(stage);
    }

    public static void abrirTelaCadastroCliente(Stage stage) {
        FXMLLoader loader = new FXMLLoader(ClienteCadastroView.class.getResource(
                "/br/edu/ufersa/projeto9poo/view/ClienteCadastro.fxml"
        ));

        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setTitle("Cadastrar Cliente");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar FXML: " + e.getMessage());
        }
    }
}
