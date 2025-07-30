package br.edu.ufersa.projeto9poo.view;

import br.edu.ufersa.projeto9poo.models.entities.Administrador;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioService;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioServiceImpl;
import br.edu.ufersa.projeto9poo.util.Estado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalView extends Application {

    private static Stage stage;

    public static void main(String[] args) {

        FuncionarioService funcionarioService = new FuncionarioServiceImpl();

        Administrador admin = new Administrador();
        admin.setUsuario("admin");

        try {
            funcionarioService.cadastrar(admin);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        PrincipalView.stage = stage;
        try {
            login();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void principal() {
        FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioView.class.getResource("/br/edu/ufersa/projeto9poo/view/MenuPrincipal.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1366, 768);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public static void login() throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Açaí da Ju - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void itens(AnchorPane anchorPane) throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/item.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    public static void carrinho(AnchorPane anchorPane) throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/carrinho.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    public static void clientes(AnchorPane anchorPane) throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/cliente.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    public static void funcionario(AnchorPane anchorPane) throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/funcionario.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    public static void home(AnchorPane anchorPane) throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/Home.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    public static void relatorio(AnchorPane anchorPane) throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/Relatorio.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    public static void sair() throws IOException {
        Estado.pegarInstancia().setFuncionarioLogado(null);
        PrincipalView.login();
    }

    public static void trocarSenha(AnchorPane anchorPane) throws IOException {
        Parent root = FXMLLoader.load(PrincipalView.class.getResource("/br/edu/ufersa/projeto9poo/view/trocarSenha.fxml"));
        anchorPane.getChildren().setAll(root);
    }
}