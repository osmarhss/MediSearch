package org.example.medisearch;

import DAOs.MedicamentoDao;
import Models.Medicamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AtualizarMedController {

    @FXML
    public Label attSucesso;
    @FXML
    public TextField idField;
    @FXML
    public TextField subsField;
    @FXML
    public TextField classeTField;
    @FXML
    public TextField produtoField;
    @FXML
    public TextField apresentacaoField;
    @FXML
    public TextField precoSField;


    public void btn_atualizarMed() {
        MedicamentoDao medicamentoDao = new MedicamentoDao();
        Medicamento medicamento = new Medicamento(
                        Integer.parseInt(idField.getText()),
                        subsField.getText(),
                        classeTField.getText(),
                        produtoField.getText(),
                        apresentacaoField.getText(),
                        Double.parseDouble(precoSField.getText()));

        var attMed = medicamentoDao.atualizar(medicamento);
        if (attMed != null) {
            attSucesso.setVisible(true);
        }

    }


    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("medicamento.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) attSucesso.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Medicamento - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
