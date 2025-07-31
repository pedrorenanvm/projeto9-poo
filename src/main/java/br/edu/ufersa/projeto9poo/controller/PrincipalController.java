package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.util.Estado;
import br.edu.ufersa.projeto9poo.view.PrincipalView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PrincipalController {
    @FXML
    private Button botaoFuncionario;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        botaoFuncionario.setDisable(!Estado.pegarInstancia().isAdmin());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/home.fxml"));
            anchorPane.getChildren().setAll(root);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (IOException e) {
            exibirErro(e.getMessage());
        }
    }

    public void handleMenuItens() throws IOException {
        PrincipalView.itens(anchorPane);
    }

    public void handleMenuPedido() throws IOException {
        PrincipalView.carrinho(anchorPane);
    }

    public void handleMenuCliente() throws IOException {
        PrincipalView.clientes(anchorPane);
    }

    public void handleMenuFuncionario() throws IOException {
        PrincipalView.funcionario(anchorPane);
    }

    public void handleMenuHome() throws IOException {
        PrincipalView.home(anchorPane);
    }

    public void handleMenuRelatorio() throws IOException {
        PrincipalView.relatorio(anchorPane);
    }

    private void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro Home");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void handleSair(ActionEvent actionEvent) throws IOException {
        PrincipalView.sair();
    }

    public void handleTrocarSenha(ActionEvent actionEvent) throws IOException {
        PrincipalView.trocarSenha(anchorPane);
    }
}
