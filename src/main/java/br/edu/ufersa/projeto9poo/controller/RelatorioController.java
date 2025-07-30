package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.entities.ItemCarrinho;
import br.edu.ufersa.projeto9poo.models.services.CarrinhoService;
import br.edu.ufersa.projeto9poo.models.services.CarrinhoServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioController {
    @FXML
    private CategoryAxis produtoAxis;
    @FXML
    private Label labelPedidos;
    @FXML
    private BarChart<String, Integer> barPedidos;
    @FXML
    private NumberAxis quantidadeAxis;

    private CarrinhoService carrinhoService = new CarrinhoServiceImpl();

    @FXML
    private void initialize() {

    }

    private void configurar() {
        quantidadeAxis.setTickUnit(1);
        quantidadeAxis.setMinorTickCount(0);
        quantidadeAxis.setForceZeroInRange(true);
        quantidadeAxis.setAutoRanging(false);
    }

    @FXML
    private void periodoDia(ActionEvent actionEvent) {
        LocalDate hoje = LocalDate.now();
        List<Carrinho> carrinhos = carrinhoService.buscarTodos();
        List<Carrinho> carrinhosHoje = carrinhos.stream()
                .filter(c -> c.getData().equals(hoje))
                .toList();

        Map<String, Integer> produtosQuantidade = new HashMap<>();

        for (Carrinho carrinho : carrinhosHoje) {
            for (ItemCarrinho item : carrinho.getItensCarrinho()) {
                String chave = item.getProduto().getNome();
                produtosQuantidade.put(chave, produtosQuantidade.getOrDefault(chave, 0) + item.getQuantidade());
            }
        }

        Map<String, Integer> dados = produtosQuantidade.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(30)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        serie.setName("Produtos");
        for (var dado : dados.entrySet()) {
            serie.getData().add(new XYChart.Data<>(dado.getKey(), dado.getValue()));
        }

        labelPedidos.setText(String.valueOf(carrinhosHoje.size()));
        barPedidos.getData().clear();
        barPedidos.getData().add(serie);
        configurar();
    }

    @FXML
    private void periodoSemana(ActionEvent actionEvent) {
        LocalDate hoje = LocalDate.now();
        List<Carrinho> carrinhos = carrinhoService.buscarTodos();
        List<Carrinho> carrinhosSemana = carrinhos.stream()
                .filter(c -> c.getData().getYear() == hoje.getYear() && c.getData().get(WeekFields.ISO.weekOfYear()) == hoje.get(WeekFields.ISO.weekOfYear()))
                .toList();

        Map<String, Integer> produtosQuantidade = new HashMap<>();

        for (Carrinho carrinho : carrinhosSemana) {
            for (ItemCarrinho item : carrinho.getItensCarrinho()) {
                String chave = item.getProduto().getNome();
                produtosQuantidade.put(chave, produtosQuantidade.getOrDefault(chave, 0) + item.getQuantidade());
            }
        }

        Map<String, Integer> dados = produtosQuantidade.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(30)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        serie.setName("Produtos");
        for (var dado : dados.entrySet()) {
            serie.getData().add(new XYChart.Data<>(dado.getKey(), dado.getValue()));
        }

        labelPedidos.setText(String.valueOf(carrinhosSemana.size()));
        barPedidos.getData().clear();
        barPedidos.getData().add(serie);
        configurar();
    }

    @FXML
    private void periodoMes(ActionEvent actionEvent) {
        LocalDate hoje = LocalDate.now();
        List<Carrinho> carrinhos = carrinhoService.buscarTodos();
        List<Carrinho> carrinhosMes = carrinhos.stream()
                .filter(c -> c.getData().getYear() == hoje.getYear() && c.getData().getMonth() == hoje.getMonth())
                .toList();

        Map<String, Integer> produtosQuantidade = new HashMap<>();

        for (Carrinho carrinho : carrinhosMes) {
            for (ItemCarrinho item : carrinho.getItensCarrinho()) {
                String chave = item.getProduto().getNome();
                produtosQuantidade.put(chave, produtosQuantidade.getOrDefault(chave, 0) + item.getQuantidade());
            }
        }

        Map<String, Integer> dados = produtosQuantidade.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(30)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        serie.setName("Produtos");

        for (var dado : dados.entrySet()) {
            serie.getData().add(new XYChart.Data<>(dado.getKey(), dado.getValue()));
        }

        labelPedidos.setText(String.valueOf(carrinhosMes.size()));
        barPedidos.getData().clear();
        barPedidos.getData().add(serie);
        configurar();
    }
}
