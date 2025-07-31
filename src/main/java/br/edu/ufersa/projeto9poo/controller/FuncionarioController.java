package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioService;
import br.edu.ufersa.projeto9poo.models.services.FuncionarioServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class FuncionarioController {
    @FXML
    private TextField inputBuscar;
    @FXML
    private TableView<Funcionario> table;
    @FXML
    private TableColumn<Funcionario, Integer> tableId;
    @FXML
    private TableColumn<Funcionario, String> tableUsuario;
    @FXML
    private Label labelId;
    @FXML
    private TextField inputUsuario;

    private FuncionarioService funcionarioService = new FuncionarioServiceImpl();

    @FXML
    private void initialize() {
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        recarregarLista();

        table.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, antido, novo) -> selecionarItem(novo)
        ));
        inputBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            recarregarLista();
        });
    }

    private void recarregarLista() {
        List<Funcionario> funcionarios = funcionarioService.buscarTodos(inputBuscar.getText());
        ObservableList<Funcionario> observableFuncionarios = FXCollections.observableList(funcionarios);
        table.setItems(observableFuncionarios);
    }

    private void selecionarItem(Funcionario funcionario) {
        if (funcionario == null) {
            return;
        }
        labelId.setText(String.valueOf(funcionario.getId()));
        inputUsuario.setText(funcionario.getUsuario());

    }

    private void exibirError(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no funcionario");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void criar(ActionEvent actionEvent) {
        Funcionario novoFuncionario = new Funcionario();
        try {
            novoFuncionario.setUsuario(inputUsuario.getText());
            funcionarioService.cadastrar(novoFuncionario);
        } catch (RuntimeException e) {
            exibirError(e.getMessage());
        }
        recarregarLista();
    }

    @FXML
    private void deletar(ActionEvent actionEvent) {
        try {
            Funcionario funcionario = table.getSelectionModel().getSelectedItem();
            if (funcionario == null) {
                throw new RuntimeException("Funcionario não selecionando");
            }
            funcionarioService.deletar(funcionario);
        } catch (RuntimeException e) {
            exibirError(e.getMessage());
        }
        recarregarLista();
    }

    @FXML
    private void resetarSenha(ActionEvent actionEvent) {
        try {
            Funcionario funcionario = table.getSelectionModel().getSelectedItem();
            if (funcionario == null) {
                throw new RuntimeException("Funcionario não selecionando");
            }
            funcionarioService.resetarSenha(funcionario);
        } catch (RuntimeException e) {
            exibirError(e.getMessage());
        }
    }
}
