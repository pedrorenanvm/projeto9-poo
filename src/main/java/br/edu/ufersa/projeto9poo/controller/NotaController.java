package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.entities.ItemCarrinho;
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

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class NotaController {
    @FXML
    private Label labelId;
    @FXML
    private Label labelCliente;
    @FXML
    private Label labelEstado;
    @FXML
    private Label labelTipoPagamento;
    @FXML
    private Label labelData;
    @FXML
    private Label labelTotal;
    @FXML
    private TableView<ItemCarrinho> table;
    @FXML
    private TableColumn<ItemCarrinho, Long> columnId;
    @FXML
    private TableColumn<ItemCarrinho, String> columnProduto;
    @FXML
    private TableColumn<ItemCarrinho, Integer> columnQuantidade;
    @FXML
    private TableColumn<ItemCarrinho, Long> columnPreco;
    @FXML
    private TableColumn<ItemCarrinho, Long> columnTotal;

    private final DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

    @FXML
    private void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProduto.setCellValueFactory(param -> {
            ItemCarrinho item = param.getValue();
            String nome = item.getProduto().getNome();
            String adicionais = String.join(", ", item.getAdicionais().stream().map(Adicional::getNome).toList());
            if (!adicionais.isEmpty()) {
                nome += " (" + adicionais + ")";
            }
            return new ReadOnlyStringWrapper(nome);
        });
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnPreco.setCellValueFactory(new PropertyValueFactory<>("precoUnidade"));
        columnTotal.setCellValueFactory(param -> {
            ItemCarrinho item = param.getValue();
            return new ReadOnlyObjectWrapper<Long>(item.precoTotal());
        });

        carregar();
    }

    private void carregar() {
        Carrinho carrinho = Estado.pegarInstancia().getCarrinhoNota().orElseThrow();

        List<ItemCarrinho> itens = carrinho.getItensCarrinho();
        ObservableList<ItemCarrinho> observableItemCarrinho = FXCollections.observableList(itens);
        table.setItems(observableItemCarrinho);

        labelId.setText(String.valueOf(carrinho.getId()));
        labelCliente.setText(carrinho.getCliente().getNome());
        labelData.setText(carrinho.getData().format(fmtData));
        labelEstado.setText(carrinho.getEstado().toString());
        labelTipoPagamento.setText(carrinho.getPagamento().toString());
        labelTotal.setText(fmtMoeda.format(carrinho.precoTotal()));
    }

}
