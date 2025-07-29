package br.edu.ufersa.projeto9poo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Principal {
    @FXML
    private AnchorPane anchorPane;
    public void handleMenuItens() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/item.fxml"));
        anchorPane.getChildren().setAll(root); // ✅ Substitui o conteúdo da tela
    }
    public void handleMenuPedido() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/pedido.fxml"));
        anchorPane.getChildren().setAll(root); // ✅ Substitui o conteúdo da tela
    }
    public void handleMenuCliente() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/cliente.fxml"));
        anchorPane.getChildren().setAll(root); // ✅ Substitui o conteúdo da tela
    }
    public void handleMenuFuncionario() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/funcionario.fxml"));
        anchorPane.getChildren().setAll(root); // ✅ Substitui o conteúdo da tela
    }
    public void handleMenuTrocarSenha() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/projeto9poo/view/trocarsenha.fxml"));
        anchorPane.getChildren().setAll(root); // ✅ Substitui o conteúdo da tela
    }
}
