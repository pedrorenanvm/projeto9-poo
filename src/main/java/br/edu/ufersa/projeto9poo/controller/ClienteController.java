package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.builders.ClienteBuilder;
import br.edu.ufersa.projeto9poo.models.entities.Cliente;
import br.edu.ufersa.projeto9poo.models.services.ClienteService;
import br.edu.ufersa.projeto9poo.models.services.ClienteServiceImpl;
import br.edu.ufersa.projeto9poo.models.utils.AppErrorException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ClienteController {
    @FXML private TextField campoBusca;
    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Long> colId;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, String> colEndereco;
    @FXML private TableColumn<Cliente, String> colTelefone;

    @FXML private TextField txtNome;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtTelefone;
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnDeletar;
    @FXML private Label appError;

    private final ClienteService clienteService = new ClienteServiceImpl();
    private Cliente clienteSelecionado = null;

    @FXML
    private void initialize() {
        AppErrorException.setLabel(appError);
        configurarColunas();
        carregarClientes();
        configurarEventos();

        campoBusca.textProperty().addListener((obs, oldText, newText) -> buscarClientePorNome(newText.trim()));
    }

    private void configurarColunas() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteService.buscarTodos();
        tabelaClientes.setItems(FXCollections.observableArrayList(clientes));
    }

    private void buscarClientePorNome(String nome) {
        if (nome.isEmpty()) {
            carregarClientes();
        } else {
            List<Cliente> encontrados = clienteService.buscarPorNome(nome);
            tabelaClientes.setItems(FXCollections.observableArrayList(encontrados));
        }
    }

    private void configurarEventos() {
        tabelaClientes.setOnMouseClicked(this::selecionarClienteNaTabela);

        btnSalvar.setOnAction(e -> salvarCliente());
        btnEditar.setOnAction(e -> editarCliente());
        btnDeletar.setOnAction(e -> deletarCliente());
    }

    private void selecionarClienteNaTabela(MouseEvent event) {
        clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            txtNome.setText(clienteSelecionado.getNome());
            txtEndereco.setText(clienteSelecionado.getEndereco());
            txtTelefone.setText(clienteSelecionado.getTelefone());
            AppErrorException.limpar();
        }
    }

    private void salvarCliente() {
        String nome = txtNome.getText();
        String endereco = txtEndereco.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
            AppErrorException.erro("Preencha todos os campos!");
            return;
        }

        if (!validarTelefone(telefone)) {
            AppErrorException.erro("Telefone inválido! Use o formato (99) 99999-9999 ou (99) 9999-9999.");
            return;
        }

        try {
            Cliente novoCliente = ClienteBuilder.builder()
                    .nome(nome)
                    .endereco(endereco)
                    .telefone(telefone)
                    .build();

            clienteService.cadastrar(novoCliente);
            AppErrorException.sucesso("Cliente cadastrado com sucesso!");
            limparCampos();
            carregarClientes();
        } catch (IllegalArgumentException ex) {
            AppErrorException.erro(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            AppErrorException.erro("Erro ao cadastrar cliente.");
        }
    }

    private void editarCliente() {
        if (clienteSelecionado == null) {
            AppErrorException.erro("Selecione um cliente para editar.");
            return;
        }

        String nome = txtNome.getText();
        String endereco = txtEndereco.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
            AppErrorException.erro("Preencha todos os campos para editar.");
            return;
        }
        if (!validarTelefone(telefone)) {
            AppErrorException.erro("Telefone inválido! Use o formato (99) 99999-9999 ou (99) 9999-9999.");
            return;
        }
        clienteSelecionado.setNome(nome);
        clienteSelecionado.setEndereco(endereco);
        clienteSelecionado.setTelefone(telefone);

        try {
            clienteService.editar(clienteSelecionado);
            AppErrorException.sucesso("Cliente atualizado com sucesso!");

            limparCampos();
            campoBusca.clear();
            carregarClientes();

            clienteSelecionado = null;
        } catch (IllegalArgumentException ex) {
            AppErrorException.erro(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            AppErrorException.erro("Erro ao atualizar cliente.");
        }
    }

    private void deletarCliente() {
        if (clienteSelecionado == null) {
            AppErrorException.erro("Selecione um cliente para deletar.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar deleção");
        alert.setHeaderText("Deseja realmente excluir o cliente?");
        alert.setContentText("Nome: " + clienteSelecionado.getNome());

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    clienteService.deletar(clienteSelecionado);
                    AppErrorException.sucesso("Cliente deletado com sucesso!");
                    limparCampos();
                    carregarClientes();
                    clienteSelecionado = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    AppErrorException.erro("Erro ao deletar cliente.");
                }
            }
        });
    }

    private void limparCampos() {
        txtNome.clear();
        txtEndereco.clear();
        txtTelefone.clear();
        AppErrorException.limpar();
    }

    private boolean validarTelefone(String telefone) {
        //formatos - (11) 91234-5678 ou (11) 1234-5678
        return telefone != null && telefone.matches("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$");
    }

}