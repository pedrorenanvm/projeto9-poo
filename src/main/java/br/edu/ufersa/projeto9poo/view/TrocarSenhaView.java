package br.edu.ufersa.projeto9poo.view;

import br.edu.ufersa.projeto9poo.controller.TrocarSenhaController;
import br.edu.ufersa.projeto9poo.models.entities.Funcionario;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrocarSenhaView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Funcionario funcionario = (new FuncionarioServiceImpl()).buscarTodos("").getFirst();
        System.out.println(funcionario.getUsuario());
        trocar(stage, funcionario);
    }

    public static void trocar(Stage stage, Funcionario funcionario) {
        FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioView.class.getResource("/br/edu/ufersa/projeto9poo/view/TrocarSenha.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
            TrocarSenhaController controller = fxmlLoader.getController();
            controller.setFuncionario(funcionario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Trocar senha");
        stage.setScene(scene);
        stage.show();
    }
}
