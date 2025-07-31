package br.edu.ufersa.projeto9poo.view;

import br.edu.ufersa.projeto9poo.controller.NotaController;
import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.services.CarrinhoServiceImpl;
import br.edu.ufersa.projeto9poo.util.Estado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NotaView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Carrinho carrinho = (new CarrinhoServiceImpl()).buscarTodos().getFirst();
        System.out.println(carrinho.getId());
        Estado.pegarInstancia().setCarrinhoNota(carrinho);
        trocar(stage);
    }

    public static void trocar(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(NotaView.class.getResource("/br/edu/ufersa/projeto9poo/view/Nota.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1158, 768);
            NotaController controller = fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Nota");
        stage.setScene(scene);
        stage.show();
    }
}
