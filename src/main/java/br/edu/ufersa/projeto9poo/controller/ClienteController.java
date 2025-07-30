//package br.edu.ufersa.projeto9poo.controller;
//
//import br.edu.ufersa.projeto9poo.view.ClienteCadastroView;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//
//import java.awt.event.ActionEvent;
//
//public class ClienteMenuController {
//
//    @FXML
//    private Button btnVoltar;
//
//    @FXML
//    private Button btnCadastrar;
//
//    @FXML
//    private Button btnBuscar;
//
//    @FXML
//    void initialize() {
//        // Aqui você pode configurar listeners se quiser
//        btnVoltar.setOnAction(this::voltarMenuPrincipal);
//    }
//
//    @FXML
//    void voltarMenuPrincipal(ActionEvent event) {
//        // Fecha a janela atual
//        Stage stage = (Stage) btnVoltar.getScene().getWindow();
//        stage.close();
//        // Aqui você pode chamar o MenuPrincipal se desejar
//    }
//
//    @FXML
//    void abrirCadastroCliente(ActionEvent event) {
//        ClienteCadastroView.abrirTelaCadastroCliente();
//    }
//
//    @FXML
//    void abrirBuscaCliente(ActionEvent event) {
//        BuscaClienteView.abrirTelaBuscarCliente();
//    }
//}
