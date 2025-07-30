package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioService;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnLogin;

    private final FuncionarioService funcionarioService = new FuncionarioServiceImpl();


    @FXML
    private void handleLogin() {

        String senha = txtSenha.getText();
        String usuario = txtUsuario.getText();

        try{
            Funcionario funcionario = new Funcionario(usuario, senha);
            Funcionario logado = funcionarioService.logar(funcionario);

            // Carregar a próxima tela aqui
        } catch(IllegalArgumentException e){
            mostrarAlertaErro("Login inválido", e.getMessage());

        }catch (Exception e){
            mostrarAlertaErro("Error inesperado", "Ocorreu um erro ao efetuar login");
            e.printStackTrace();
        }

    }


    private void mostrarAlertaErro(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

}

