package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.entities.Item;
import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.services.ItemCoordinatorServiceImpl;
import br.edu.ufersa.projeto9poo.util.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ItemController {
    @FXML
    private Button botaoCriar;
    @FXML
    private TableView<Item> tableViewItem;
    @FXML
    private TableColumn<Item, Long> tableColumnItemId;
    @FXML
    private TableColumn<Item, String> tableColumnItemNome;
    @FXML
    private TableColumn<Item, Long> tableColumnItemPreco;
    @FXML
    private Label labelItemId;
    @FXML
    private TextField textFieldItemNome;
    @FXML
    private TextField textFieldItemPreco;
    @FXML
    private CheckBox checkBoxItemEstoque;
    @FXML
    private ComboBox<String> comboBoxItemTipo;
    @FXML
    private TextField inputItem;
    @FXML
    private CheckBox checkBoxProdutos;
    @FXML
    private CheckBox checkBoxAdicionais;
    @FXML
    private CheckBox checkBoxFalta;


    private ObservableList<Item> observableListItens;

    private final ItemCoordinatorServiceImpl coordinatorService = new ItemCoordinatorServiceImpl();

    @FXML
    private void initialize() {

        botaoCriar.setDisable(!Estado.pegarInstancia().isAdmin());

        comboBoxItemTipo.setItems(FXCollections.observableArrayList("PRODUTO", "ADICIONAL"));
        comboBoxItemTipo.setValue("PRODUTO"); //Valor padrão

        tableColumnItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnItemNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnItemPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        carregarLista();

        tableViewItem.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldItem, newItem) -> selecionarTableViewItem(newItem));
        inputItem.textProperty().addListener((observable, oldValue, newValue) -> {
            carregarLista();
        });
    }

    private void carregarLista() {
        String filtroTexto = inputItem.getText().trim();
        boolean filtrarAdicionais = checkBoxAdicionais.isSelected();
        boolean filtrarFalta = checkBoxFalta.isSelected();
        boolean filtrarProdutos = checkBoxProdutos.isSelected();

        List<Item> todosItens = coordinatorService.buscarTodos(filtroTexto);

        List<Item> itensFiltrados = todosItens.stream()
                .filter(item -> {
                    boolean tipoItem = ((filtrarAdicionais && item instanceof Adicional) ||
                            (filtrarProdutos && item instanceof Produto) ||
                            (!filtrarAdicionais && !filtrarProdutos));

                    boolean tipoFalta = (!filtrarFalta || !item.isEstoque());

                    return tipoItem && tipoFalta;
                })
                .toList();

        observableListItens = FXCollections.observableArrayList(itensFiltrados);
        tableViewItem.setItems(observableListItens);
    }

    private void selecionarTableViewItem(Item item) {
        if (item != null) {
            labelItemId.setText(String.valueOf(item.getId()));
            textFieldItemNome.setText(item.getNome());
            textFieldItemPreco.setText(String.valueOf(item.getPreco()));
            checkBoxItemEstoque.setSelected(item.isEstoque());
            comboBoxItemTipo.setValue(item.getClass().getSimpleName().toUpperCase());
        }
    }

    public void criarItem() {
        try {
            String tipo = comboBoxItemTipo.getValue();
            String nome = textFieldItemNome.getText();
            long preco = Long.parseLong(textFieldItemPreco.getText());
            boolean estoque = checkBoxItemEstoque.isSelected();

            coordinatorService.cadastrar(tipo, nome, preco, estoque);

            carregarLista();
        } catch (Exception e) {
            exibirErro(e.getMessage());
        }
    }

    public void alterarItem() {
        Item item = tableViewItem.getSelectionModel().getSelectedItem();
        if (item != null) {
            try {
                item.setNome(textFieldItemNome.getText());
                item.setPreco(Long.parseLong(textFieldItemPreco.getText()));
                item.setEstoque(checkBoxItemEstoque.isSelected());
                coordinatorService.editar(item);
                carregarLista();
            } catch (NumberFormatException e) {
                exibirErro("Preço inválido: informe um número.");
            } catch (RuntimeException e) {
                exibirErro(e.getMessage());
            }
        } else {
            exibirErro("Nenhum Item selecionado.");
        }

    }

    public void deletarItem() {
        Item item = tableViewItem.getSelectionModel().getSelectedItem();
        if (item != null) {
            try {
                coordinatorService.deletar(item);
            } catch (Exception e) {
                exibirErro(e.getMessage());
            }
        } else {
            exibirErro("Nenhum item selecionado.");
        }
        carregarLista();
    }

    private void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro Item");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}