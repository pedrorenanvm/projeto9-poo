package br.edu.ufersa.projeto9poo.controller;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.services.AdicionalServicelmpl;
import br.edu.ufersa.projeto9poo.models.services.AdicionalServicer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AdicionalController {
    @FXML
    private TableView<Adicional> tableViewAdicional;
    @FXML
    private TableColumn<Adicional, Integer> tableColumnAdicionalId;
    @FXML
    private TableColumn<Adicional,String> tableColumnAdicionalNome;
    @FXML
    private Label labelAdicionalId;
    @FXML
    private TextField textFieldAdicionalNome;
    @FXML
    private TextField textFieldAdicionalPreco;
    @FXML
    private TextField textFieldAdicionalEstoque;
    @FXML
    private TextField inputAdicional;
    private List<Adicional> listAdicionais;
    private ObservableList<Adicional> observableListAdicionais;

    private AdicionalServicer adicionalServicer = new AdicionalServicelmpl();

    @FXML
    private void initialize(){
        tableColumnAdicionalId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnAdicionalNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        carregarLista();
        tableViewAdicional.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, adicionalAntigo, adicionalNovo) -> selecionarTableViewAdicional(adicionalNovo)));
    }
    private void carregarLista(){
        List<Adicional> adicionals = adicionalServicer.buscarTodos();
        observableListAdicionais = FXCollections.observableList(adicionals);
        tableViewAdicional.setItems(observableListAdicionais);
    }
    private void selecionarTableViewAdicional(Adicional adicional) {
        if (adicional != null) {
            labelAdicionalId.setText(String.valueOf(adicional.getId()));
            textFieldAdicionalNome.setText(adicional.getNome());
            textFieldAdicionalPreco.setText(String.valueOf(adicional.getPreco()));
            textFieldAdicionalEstoque.setText(String.valueOf(adicional.getEstoque()));
        }
    carregarLista();
    }

    private void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro Adicional");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

     public void criarAdicional() {
         Adicional novoAdicional = new Adicional();
         try {
             novoAdicional.setNome(textFieldAdicionalNome.getText());
             novoAdicional.setEstoque(true);
             novoAdicional.setPreco(Long.parseLong(textFieldAdicionalPreco.getText()));
             adicionalServicer.cadastrar(novoAdicional);

         } catch (RuntimeException e) {
             exibirErro(e.getMessage());
         }
         carregarLista();
     }

     public void deletarAdicional(){
        Adicional adicional = tableViewAdicional.getSelectionModel().getSelectedItem();
        if (adicional != null){
            try {
                adicionalServicer.deletar(adicional);
            } catch (RuntimeException e){
                exibirErro(e.getMessage());
            }
        }else {
            exibirErro("Nenhum adicional selecionado.");
        }
         carregarLista();
     }

     public void alterarAdicional(){
        Adicional adicional = tableViewAdicional.getSelectionModel().getSelectedItem();
         if (adicional != null){
             try {
                 adicional.setNome(textFieldAdicionalNome.getText());
                 adicional.setEstoque(Boolean.parseBoolean(textFieldAdicionalEstoque.getText()));
                 adicional.setPreco(Long.parseLong(textFieldAdicionalPreco.getText()));
                 adicionalServicer.editar(adicional);
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
