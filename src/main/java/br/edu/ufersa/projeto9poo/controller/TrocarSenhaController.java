package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioService;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioServiceImpl;
import br.edu.ufersa.projeto9poo.models.services.UsuarioSenhaIncorretoException;
import br.edu.ufersa.projeto9poo.util.Estado;
import br.edu.ufersa.projeto9poo.view.PrincipalView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TrocarSenhaController {
    public AnchorPane anchorPane;
    @FXML
    private PasswordField inputSenhaAntiga;
    @FXML
    private PasswordField inputSenhaNova;
    @FXML
    private PasswordField inputVerificar;

    private final FuncionarioService funcionarioService = new FuncionarioServiceImpl();

    private void exibirError(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no trocar senha");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void trocarSenha(ActionEvent actionEvent) {
        try {
            Funcionario funcionario = Estado.pegarInstancia().getFuncionarioLogado().orElseThrow();
            funcionario.setSenha(inputSenhaAntiga.getText());
            funcionarioService.logar(funcionario);
            if (!inputSenhaNova.getText().equals(inputVerificar.getText())) {
                throw new IllegalArgumentException("A senha nova tem que ser igual ao verificar");
            }
            funcionario.setSenha(inputSenhaNova.getText());
            funcionarioService.trocarSenha(funcionario);

            PrincipalView.home(anchorPane);
        } catch (RuntimeException e) {
            exibirError(e.getMessage());
        } catch (UsuarioSenhaIncorretoException e) {
            exibirError("Senha antiga incorreto");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
