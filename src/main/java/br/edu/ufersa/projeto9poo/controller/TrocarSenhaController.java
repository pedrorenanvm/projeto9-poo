package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioService;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioServiceImpl;
import br.edu.ufersa.projeto9poo.models.services.UsuarioSenhaIncorretoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class TrocarSenhaController {
    @FXML
    private PasswordField inputSenhaAntiga;
    @FXML
    private PasswordField inputSenhaNova;
    @FXML
    private PasswordField inputVerificar;

    private Funcionario funcionario;

    private FuncionarioService funcionarioService = new FuncionarioServiceImpl();

    private void exibirError(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no trocar senha");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void trocarSenha(ActionEvent actionEvent) {
        try {
            funcionario.setSenha(inputSenhaAntiga.getText());
            funcionarioService.logar(funcionario);
            if (!inputSenhaNova.getText().equals(inputVerificar.getText())) {
                throw new IllegalArgumentException("A senha nova tem que ser igual ao verificar");
            }
            funcionario.setSenha(inputSenhaNova.getText());
            funcionarioService.trocarSenha(funcionario);
        } catch (RuntimeException e) {
            exibirError(e.getMessage());
        } catch (UsuarioSenhaIncorretoException e) {
            exibirError("Senha antiga incorreto");
        }
    }

    public void setFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("O funcionario não pode ser nulo");
        }
        this.funcionario = funcionario;
    }
}
