package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.services.CarrinhoService;
import br.edu.ufersa.projeto9poo.models.services.CarrinhoServiceImpl;
import br.edu.ufersa.projeto9poo.models.services.ItemCoodinatorService;
import br.edu.ufersa.projeto9poo.models.services.ItemCoordinatorServiceImpl;
import br.edu.ufersa.projeto9poo.util.Estado;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HomeController {
    @FXML
    private Label labelOla;
    @FXML
    private Label labelValor;
    @FXML
    private Label labelItem;
    @FXML
    private Label labelPedido;
    @FXML
    private TableView<Carrinho> table;
    @FXML
    private TableColumn<Carrinho, Long> columnId;
    @FXML
    private TableColumn<Carrinho, String> columnCliente;
    @FXML
    private TableColumn<Carrinho, Long> columnValor;
    @FXML
    private TableColumn<Carrinho, String> columnStatus;

    private CarrinhoService carrinhoService = new CarrinhoServiceImpl();
    private ItemCoodinatorService itemCoodinatorService = new ItemCoordinatorServiceImpl();

    @FXML
    private void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCliente.setCellValueFactory(p -> {
            Carrinho carrinho = p.getValue();
            return new ReadOnlyStringWrapper(carrinho.getCliente().getNome());
        });
        columnValor.setCellValueFactory(p -> {
            Carrinho carrinho = p.getValue();
            return new ReadOnlyObjectWrapper<Long>(carrinho.precoTotal());
        });
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("estado"));

        labelOla.setText("Olá " + Estado.pegarInstancia().getFuncionarioLogado().orElseThrow().getUsuario());
        carregarDados();
    }

    private void carregarDados() {
        List<Carrinho> carrinhosHoje = carrinhoService.buscarTodosHoje();

        labelValor.setText(String.valueOf(carrinhoService.somaPrecoHoje()));
        labelItem.setText(String.valueOf(itemCoodinatorService.QuantidadeSemEstoque()));
        labelPedido.setText(String.valueOf(carrinhoService.quantidadeAberto()));

        ObservableList<Carrinho> observableCarrinhosHoje = FXCollections.observableList(carrinhosHoje);
        table.setItems(observableCarrinhosHoje);
    }
}
