package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.services.ProdutoService;
import br.edu.ufersa.projeto9poo.models.services.ProdutoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ProdutoController {
    @FXML
    private TableView<Produto> tableViewProduto;
    @FXML
    private TableColumn<Produto, Integer> tableColumnProdutoId;
    @FXML
    private TableColumn<Produto,String> tableColumnProdutoNome;
    @FXML
    private Label labelProdutoId;
    @FXML
    private TextField textFieldProdutoNome;
    @FXML
    private TextField textFieldProdutoPreco;
    @FXML
    private TextField textFieldProdutoEstoque;
    @FXML
    private TextField inputProduto;
    private ObservableList<Produto> observableListProdutos;

    private ProdutoService produtoServicer = new ProdutoServiceImpl();

    @FXML
    private void initialize(){
        tableColumnProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        carregarLista();
        tableViewProduto.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, produtoAntigo, produtoNovo) -> selecionarTableViewProduto(produtoNovo)));
        inputProduto.setOnAction(actionEvent -> {
            carregarLista();
        });
    }
    private void carregarLista(){
        List<Produto> produtos = produtoServicer.buscarTodos(inputProduto.getText());
        observableListProdutos = FXCollections.observableList(produtos);
        tableViewProduto.setItems(observableListProdutos);
    }
    private void selecionarTableViewProduto(Produto produto) {
        if (produto != null) {
            labelProdutoId.setText(String.valueOf(produto.getId()));
            textFieldProdutoNome.setText(produto.getNome());
            textFieldProdutoPreco.setText(String.valueOf(produto.getPreco()));
            textFieldProdutoEstoque.setText(String.valueOf(produto.isEstoque()));
        }
    }

    private void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro Produto");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void criarProduto() {
        Produto novoProduto = new Produto();
        try {
            novoProduto.setNome(textFieldProdutoNome.getText());
            novoProduto.setEstoque(true);
            novoProduto.setPreco(Long.parseLong(textFieldProdutoPreco.getText()));
            produtoServicer.cadastrar(novoProduto);

        } catch (RuntimeException e) {
            exibirErro(e.getMessage());
        }
        carregarLista();
    }

    public void deletarProduto(){
        Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
        if (produto != null){
            try {
                produtoServicer.deletar(produto);
            } catch (RuntimeException e){
                exibirErro(e.getMessage());
            }
        }else {
            exibirErro("Nenhum produto selecionado.");
        }
        carregarLista();
    }

    public void alterarProduto(){
        Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
        if (produto != null){
            try {
                produto.setNome(textFieldProdutoNome.getText());
                produto.setEstoque(Boolean.parseBoolean(textFieldProdutoEstoque.getText()));
                produto.setPreco(Long.parseLong(textFieldProdutoPreco.getText()));
                produtoServicer.editar(produto);
                carregarLista();
            } catch (NumberFormatException e) {
                exibirErro("Preço inválido: informe um número.");
            } catch (RuntimeException e){
                exibirErro(e.getMessage());
            }
        }else {
            exibirErro("Nenhum adicional selecionado.");
        }

    }

}
