package br.edu.ufersa.projeto9poo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Principal {
    @FXML

    private AnchorPane anchorPane;
    @FXML
    public void initialize() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/home.fxml"));
            anchorPane.getChildren().setAll(root);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (IOException e){
            exibirErro(e.getMessage());
        }
    }
    public void handleMenuItens() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/item.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    public void handleMenuPedido() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/pedido.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    public void handleMenuCliente() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/cliente.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    public void handleMenuFuncionario() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/funcionario.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    public void handleMenuTrocarSenha() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/trocarsenha.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    public void handleMenuHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/Home.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    private void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro Home");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
