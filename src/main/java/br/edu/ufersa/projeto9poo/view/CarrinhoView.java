package br.edu.ufersa.projeto9poo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CarrinhoView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        abrirTelaCarrinho(stage);
    }

    public static void abrirTelaCarrinho(Stage stage) {
        FXMLLoader loader = new FXMLLoader(ClienteView.class.getResource(
                "/br/edu/ufersa/projeto9poo/view/Carrinho.fxml"
        ));

        try {
            Scene scene = new Scene(loader.load(), 1158, 768);
            stage.setTitle("Carrinho");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar FXML: " + e.getMessage());
        }
    }
}

