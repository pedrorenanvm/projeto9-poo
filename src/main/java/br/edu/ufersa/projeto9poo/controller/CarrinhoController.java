package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.*;
import br.edu.ufersa.projeto9poo.models.services.CarrinhoService;
import br.edu.ufersa.projeto9poo.models.services.CarrinhoServiceImpl;

import br.edu.ufersa.projeto9poo.models.services.ClienteService;
import br.edu.ufersa.projeto9poo.models.services.ClienteServiceImpl;
import br.edu.ufersa.projeto9poo.models.services.ProdutoService;
import br.edu.ufersa.projeto9poo.models.services.ProdutoServiceImpl;
import br.edu.ufersa.projeto9poo.models.services.AdicionalService;
import br.edu.ufersa.projeto9poo.models.services.AdicionalServiceImpl;

import br.edu.ufersa.projeto9poo.models.utils.AppError;
import br.edu.ufersa.projeto9poo.util.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CarrinhoController {

    @FXML private DatePicker filtroData;
    @FXML private TextField filtroProduto;
    @FXML private TextField filtroCliente;
    @FXML private Button btnBuscar;
    @FXML private Button btnNota;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;

    @FXML private TableView<Carrinho> tabelaCarrinhos;
    @FXML private TableColumn<Carrinho, Integer> colId;
    @FXML private TableColumn<Carrinho, String> colCliente;
    @FXML private TableColumn<Carrinho, LocalDate> colData;
    @FXML private TableColumn<Carrinho, Integer> colItens;
    @FXML private TableColumn<Carrinho, TipoPagamento> colPagamento;
    @FXML private TableColumn<Carrinho, TipoEstado> colEstado;
    @FXML private TableColumn<Carrinho, String> colValorTotal;

    @FXML private ComboBox<Cliente> comboCliente;
    @FXML private ComboBox<Produto> comboProduto;
    @FXML private ComboBox<TipoPagamento> comboPagamento;
    @FXML private ComboBox<TipoEstado> comboEstado;
    @FXML private ListView<Adicional> listaAdicionais;
    @FXML private Spinner<Integer> spinnerQuantidade;
    @FXML private TextField txtPrecoUnidade;
    @FXML private TableView<ItemCarrinho> tabelaItens;
    @FXML private TableColumn<ItemCarrinho, String> colProdutoItem;
    @FXML private TableColumn<ItemCarrinho, String> colAdicionaisItem;
    @FXML private TableColumn<ItemCarrinho, Integer> colQuantidadeItem;
    @FXML private TableColumn<ItemCarrinho, String> colPrecoUnitarioItem;
    @FXML private TableColumn<ItemCarrinho, String> colPrecoTotalItem;
    @FXML private Button btnRemoverItem;
    @FXML private Button btnSalvarCarrinho;
    @FXML private Button btnCancelarCarrinho;

    @FXML private Label appError;


    private final CarrinhoService carrinhoService = new CarrinhoServiceImpl();
    private final ClienteService clienteService = new ClienteServiceImpl();
    private final ProdutoService produtoService = new ProdutoServiceImpl();
    private final AdicionalService adicionalService = new AdicionalServiceImpl();

    private Carrinho carrinhoSelecionado = null; // referência para edição
    private final ObservableList<ItemCarrinho> itensCarrinho = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        AppError.setLabel(appError);

        configurarColunas();
        configurarSpinnerQuantidade();
        configurarListasECombos();
        configurarRenderizacaoCombosELista();
        configurarEventos();

        carregarCarrinhos();
        novoCarrinho();

        filtroCliente.textProperty().addListener((obs, o, n) -> buscarCarrinhos());
        filtroProduto.textProperty().addListener((obs, o, n) -> buscarCarrinhos());
        filtroData.valueProperty().addListener((obs, o, n) -> buscarCarrinhos());

        if (btnNota != null) {
            btnNota.setDisable(true);
        }
        if (tabelaCarrinhos != null) {
            tabelaCarrinhos.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((obs, oldSel, newSel) -> {
                        if (btnNota != null) btnNota.setDisable(newSel == null);
                    });
        }

        tabelaCarrinhos.setPlaceholder(new Label("Nenhum carrinho encontrado."));
        tabelaItens.setPlaceholder(new Label("Nenhum item no carrinho."));
        listaAdicionais.setPlaceholder(new Label("Nenhum adicional disponível."));

        spinnerQuantidade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1)
        );

        spinnerQuantidade.getEditor().textProperty().addListener((obs, old, val) -> {
            if (!val.matches("\\d*")) {
                spinnerQuantidade.getEditor().setText(val.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void configurarColunas() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colCliente.setCellValueFactory(data -> {
            Cliente cli = data.getValue().getCliente();
            String nome = (cli != null) ? cli.getNome() : "";
            return javafx.beans.binding.Bindings.createStringBinding(() -> nome);
        });

        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colData.setCellFactory(col -> new TableCell<>() {
            private final java.time.format.DateTimeFormatter br = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : br.format(item));
            }
        });

        colItens.setCellValueFactory(data ->
                javafx.beans.binding.Bindings.createObjectBinding(() -> {
                    List<ItemCarrinho> itens = data.getValue().getItens();
                    return itens == null ? 0 : itens.size();
                })
        );

        colPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        colValorTotal.setCellValueFactory(data ->
                javafx.beans.binding.Bindings.createStringBinding(() ->
                        formatarReais(data.getValue().precoTotal()))
        );

        // Tabela de itens (direita)
        colProdutoItem.setCellValueFactory(cell -> javafx.beans.binding.Bindings.createStringBinding(
                () -> cell.getValue().getProduto() != null ? cell.getValue().getProduto().getNome() : ""
        ));

        colAdicionaisItem.setCellValueFactory(cell -> javafx.beans.binding.Bindings.createStringBinding(
                () -> {
                    List<Adicional> adics = cell.getValue().getAdicionais();
                    if (adics == null || adics.isEmpty()) return "-";
                    return adics.stream().filter(Objects::nonNull).map(Adicional::getNome).collect(Collectors.joining(", "));
                }
        ));

        colQuantidadeItem.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        colPrecoUnitarioItem.setCellValueFactory(cell ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> formatarReais(cell.getValue().getPrecoUnidade())
                )
        );

        colPrecoTotalItem.setCellValueFactory(cell ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> formatarReais(cell.getValue().precoTotal())
                )
        );

        tabelaItens.setItems(itensCarrinho);
    }

    private void configurarSpinnerQuantidade() {
        spinnerQuantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        spinnerQuantidade.setEditable(true);

        spinnerQuantidade.getEditor().textProperty().addListener((obs, old, val) -> {
            if (!val.matches("\\d*")) {
                spinnerQuantidade.getEditor().setText(val.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void configurarListasECombos() {
        comboPagamento.setItems(FXCollections.observableArrayList(TipoPagamento.values()));
        comboEstado.setItems(FXCollections.observableArrayList(TipoEstado.values()));
        comboEstado.getSelectionModel().select(TipoEstado.AGUARDANDO_PAGAMENTO);

        try {
            List<Cliente> clientes = clienteService.buscarTodos();
            comboCliente.setItems(FXCollections.observableArrayList(clientes));
        } catch (Exception e) {
            AppError.erro("Falha ao carregar clientes.");
        }

        try {
            List<Produto> produtos = produtoService.buscarTodos();
            comboProduto.setItems(FXCollections.observableArrayList(produtos));
        } catch (Exception e) {
            AppError.erro("Falha ao carregar produtos.");
        }

        try {
            List<Adicional> adicionais = adicionalService.buscarTodos();
            listaAdicionais.setItems(FXCollections.observableArrayList(adicionais));
            listaAdicionais.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            AppError.erro("Falha ao carregar adicionais.");
        }

        aplicarMascaraMonetaria(txtPrecoUnidade);
    }

    private void configurarEventos() {

        tabelaCarrinhos.setOnMouseClicked(e -> selecionarCarrinhoNaTabela());

        btnBuscar.setOnAction(e -> buscarCarrinhos());
        btnEditar.setOnAction(e -> preencherFormularioParaEdicao());
        btnExcluir.setOnAction(e -> excluirCarrinho());
        btnRemoverItem.setOnAction(e -> removerItemSelecionado());
        btnSalvarCarrinho.setOnAction(e -> salvarCarrinho());
        btnCancelarCarrinho.setOnAction(e -> cancelarEdicao());

    }
    private void carregarCarrinhos() {
        try {
            List<Carrinho> lista = carrinhoService.buscarTodos();

            lista = lista.stream()
                    .sorted(Comparator.comparing(Carrinho::getData).reversed()
                            .thenComparing(Carrinho::getId).reversed())
                    .toList();
            tabelaCarrinhos.setItems(FXCollections.observableArrayList(lista));
            AppError.limpar();
        } catch (Exception e) {
            AppError.erro("Falha ao carregar carrinhos.");
        }
    }

    @FXML
    private void buscarCarrinhos() {
        try {
            LocalDate data = filtroData.getValue();
            String clienteTexto = safeLower(filtroCliente.getText());
            String produtoTexto = safeLower(filtroProduto.getText());

            List<Carrinho> base = carrinhoService.buscarTodos();

            List<Carrinho> filtrados = base.stream()
                    .filter(c -> data == null || data.equals(c.getData()))
                    .filter(c -> clienteTexto.isBlank()
                            || (c.getCliente() != null
                            && safeLower(c.getCliente().getNome()).contains(clienteTexto)))
                    .filter(c -> produtoTexto.isBlank()
                            || (c.getItens() != null && c.getItens().stream()
                            .map(ItemCarrinho::getProduto)
                            .filter(Objects::nonNull)
                            .map(Produto::getNome)
                            .map(this::safeLower)
                            .anyMatch(nome -> nome.contains(produtoTexto))))
                    .sorted(Comparator.comparing(Carrinho::getData).reversed()
                            .thenComparing(Carrinho::getId).reversed())
                    .toList();

            tabelaCarrinhos.setItems(FXCollections.observableArrayList(filtrados));
            AppError.limpar();
        } catch (Exception e) {
            AppError.erro("Erro ao buscar carrinhos.");
        }
    }

    private void selecionarCarrinhoNaTabela() {
        carrinhoSelecionado = tabelaCarrinhos.getSelectionModel().getSelectedItem();
        if (carrinhoSelecionado != null) {
            AppError.limpar();
        }
    }
    @FXML
    private void abrirNota() {
        Carrinho selecionado = tabelaCarrinhos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Nenhum carrinho selecionado");
            alert.setContentText("Selecione um carrinho na tabela para visualizar a nota.");
            alert.showAndWait();
            return;
        }

        Estado.pegarInstancia().setCarrinhoNota(selecionado);

        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/br/edu/ufersa/projeto9poo/view/Nota.fxml")
            );

            Stage stage = new Stage();
            stage.setTitle("Nota do Carrinho #" + selecionado.getId());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnNota.getScene().getWindow());

            stage.setOnHidden(e -> Estado.pegarInstancia().setCarrinhoNota(null));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erro ao abrir a Nota");
            alert.setContentText("Não foi possível carregar a tela de Nota.\n" + e.getMessage());
            alert.showAndWait();
        }
    }
    private void preencherFormularioParaEdicao() {
        if (carrinhoSelecionado == null) {
            AppError.erro("Selecione um carrinho na tabela para editar.");
            return;
        }

        Optional<Carrinho> carrinhoDoBanco = carrinhoService.buscarPorId(carrinhoSelecionado.getId());
        if (carrinhoDoBanco.isEmpty()) {
            AppError.erro("Carrinho selecionado não encontrado no banco de dados.");
            return;
        }
        carrinhoSelecionado = carrinhoDoBanco.get();

        comboCliente.getSelectionModel().select(carrinhoSelecionado.getCliente());
        comboPagamento.getSelectionModel().select(carrinhoSelecionado.getPagamento());
        comboEstado.getSelectionModel().select(carrinhoSelecionado.getEstado());

        if (carrinhoSelecionado.getItens() != null) {
            itensCarrinho.setAll(new ArrayList<>(carrinhoSelecionado.getItens()));
        } else {
            itensCarrinho.clear();
        }
        AppError.limpar();
    }

    private void excluirCarrinho() {
        if (carrinhoSelecionado == null) {
            AppError.erro("Selecione um carrinho para excluir.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar exclusão");
        alert.setHeaderText("Deseja realmente excluir o carrinho?");
        alert.setContentText("ID: " + carrinhoSelecionado.getId());

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    carrinhoService.deletar(carrinhoSelecionado);
                    AppError.sucesso("Carrinho deletado com sucesso!");
                    carrinhoSelecionado = null;
                    carregarCarrinhos();
                    novoCarrinho();
                } catch (Exception e) {
                    AppError.erro("Erro ao deletar carrinho.");
                }
            }
        });
    }

    @FXML
    private void adicionarItemCarrinho() {
        Produto produto = comboProduto.getValue();
        if (produto == null) { AppError.erro("Selecione um produto."); return; }

        Integer qtd = spinnerQuantidade.getValue();
        if (qtd == null || qtd <= 0) { AppError.erro("Quantidade deve ser maior que zero."); return; }

        Long precoUnidade = parsePreco(txtPrecoUnidade.getText());
        if (precoUnidade == null || precoUnidade <= 0) { AppError.erro("Informe um preço unitário válido."); return; }

        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);

        List<Adicional> selecionados = List.copyOf(listaAdicionais.getSelectionModel().getSelectedItems());
        item.setAdicionais(selecionados);

        item.setQuantidade(qtd);
        item.setPrecoUnidade(precoUnidade);

        itensCarrinho.add(item);

        spinnerQuantidade.getValueFactory().setValue(1);
        txtPrecoUnidade.clear();
        listaAdicionais.getSelectionModel().clearSelection();

        AppError.sucesso("Item adicionado.");
    }

    @FXML
    private void removerItemSelecionado() {
        ItemCarrinho selecionado = tabelaItens.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            AppError.erro("Selecione um item para remover.");
            return;
        }
        itensCarrinho.remove(selecionado);
        AppError.sucesso("Item removido.");
    }

    @FXML
    private void salvarCarrinho() {
        Cliente cliente = comboCliente.getValue();
        if (cliente == null) {
            AppError.erro("Selecione um cliente.");
            return;
        }

        TipoPagamento pagamento = comboPagamento.getValue();
        if (pagamento == null) {
            AppError.erro("Selecione o tipo de pagamento.");
            return;
        }

        TipoEstado estado = comboEstado.getValue();
        if (estado == null) {
            AppError.erro("Selecione o estado do carrinho.");
            return;
        }

        if (itensCarrinho.isEmpty()) {
            AppError.erro("Adicione pelo menos um item ao carrinho.");
            return;
        }

        try {
            Carrinho carrinhoParaSalvar;

            boolean isEdicao = (carrinhoSelecionado != null && carrinhoSelecionado.getId() != 0);

            if (isEdicao) {
                Optional<Carrinho> optCarrinhoExistente = carrinhoService.buscarPorId(carrinhoSelecionado.getId());
                if (optCarrinhoExistente.isEmpty()) {
                    throw new IllegalArgumentException("Carrinho selecionado para edição não encontrado no banco de dados.");
                }
                carrinhoParaSalvar = optCarrinhoExistente.get();

            } else {
                carrinhoParaSalvar = new Carrinho();
                carrinhoParaSalvar.setData(LocalDate.now());
            }

            carrinhoParaSalvar.setCliente(cliente);
            carrinhoParaSalvar.setPagamento(pagamento);
            carrinhoParaSalvar.setEstado(estado);

            carrinhoParaSalvar.setItens(new ArrayList<>(itensCarrinho));

            if (isEdicao) {
                carrinhoService.editar(carrinhoParaSalvar);
                AppError.sucesso("Carrinho atualizado com sucesso!");
                System.out.println("DEBUG: Carrinho editado");
            } else {
                carrinhoService.cadastrar(carrinhoParaSalvar);
                AppError.sucesso("Carrinho cadastrado com sucesso!");
                System.out.println("DEBUG: Carrinho cadastrado");
            }

            carregarCarrinhos();
            novoCarrinho();

        } catch (IllegalArgumentException ex) {
            System.err.println("ERRO: Argumento inválido - " + ex.getMessage());
            AppError.erro("Dados inválidos: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("ERRO: Falha ao salvar carrinho - " + ex.getMessage());
            ex.printStackTrace();
            AppError.erro("Erro ao salvar carrinho. Verifique os dados e tente novamente.\n" + ex.getMessage());
        }
    }

    private void novoCarrinho() {
        carrinhoSelecionado = null;
        comboCliente.getSelectionModel().clearSelection();
        comboPagamento.getSelectionModel().clearSelection();
        comboEstado.getSelectionModel().select(TipoEstado.AGUARDANDO_PAGAMENTO); // Estado inicial
        itensCarrinho.clear();
        spinnerQuantidade.getValueFactory().setValue(1);
        txtPrecoUnidade.clear();
        listaAdicionais.getSelectionModel().clearSelection();
        AppError.limpar();
    }

    @FXML
    private void cancelarEdicao() {
        novoCarrinho();
    }

    private void aplicarMascaraMonetaria(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                return;
            }

            String apenasNumeros = newValue.replaceAll("[^\\d]", "");

            if (apenasNumeros.isEmpty()) {
                textField.setText("");
                return;
            }

            long centavos;
            try {
                centavos = Long.parseLong(apenasNumeros);
            } catch (NumberFormatException e) {

                textField.setText(oldValue);
                return;
            }

            DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
            symbols.setDecimalSeparator(',');
            symbols.setGroupingSeparator('.');
            DecimalFormat df = new DecimalFormat("#,##0.00", symbols);

            String valorFormatado = "R$ " + df.format(centavos / 100.0);

            if (!valorFormatado.equals(newValue)) {
                textField.setText(valorFormatado);

                textField.positionCaret(valorFormatado.length());
            }
        });

        textField.setPromptText("R$ 0,00");
    }

    private String formatarReais(long valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return "R$ " + df.format(new BigDecimal(valor).divide(new BigDecimal(100)));
    }

    private Long parsePreco(String texto) {
        if (texto == null || texto.trim().isEmpty()) return null;
        try {

            String limpo = texto.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".").trim();

            return (long) (Double.parseDouble(limpo) * 100);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String safeLower(String s) {
        return s == null ? "" : s.toLowerCase();
    }

    private void configurarRenderizacaoCombosELista() {

        comboCliente.setCellFactory(lv -> new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNome());
            }
        });
        comboCliente.setButtonCell(new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNome());
            }
        });

        comboProduto.setCellFactory(lv -> new ListCell<Produto>() {
            @Override
            protected void updateItem(Produto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNome());
            }
        });
        comboProduto.setButtonCell(new ListCell<Produto>() {
            @Override
            protected void updateItem(Produto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNome());
            }
        });

        listaAdicionais.setCellFactory(lv -> new ListCell<Adicional>() {
            @Override
            protected void updateItem(Adicional item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNome());
            }
        });
    }
}


