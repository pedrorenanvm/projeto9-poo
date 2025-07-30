package br.edu.ufersa.projeto9poo.models.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class AppError {
    private static Label label;

    public static void setLabel(Label lbl) {
        label = lbl;
    }

    public static void sucesso(String mensagem) {
        if (label != null) {
            label.setText(mensagem);
            label.setStyle("-fx-text-fill: green;");
        } else {
            alert("Sucesso", mensagem, Alert.AlertType.INFORMATION);
        }
    }

    public static void erro(String mensagem) {
        if (label != null) {
            label.setText(mensagem);
            label.setStyle("-fx-text-fill: red;");
        } else {
            alert("Erro", mensagem, Alert.AlertType.ERROR);
        }
    }

    public static void limpar() {
        if (label != null) {
            label.setText("");
        }
    }

    public static void alert(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
