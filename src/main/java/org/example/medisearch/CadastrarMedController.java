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

public class CadastrarMedController {

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
    @FXML
    public Label cadSucesso;

    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("medicamento.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) produtoField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Medicamento - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_cadastrarMed() {
        MedicamentoDao medicamentoDao = new MedicamentoDao();
        Medicamento medicamento = new Medicamento(
                subsField.getText(),
                classeTField.getText(),
                produtoField.getText(),
                apresentacaoField.getText(),
                Double.parseDouble(precoSField.getText()));

        var res = medicamentoDao.adicionar(medicamento);

        if (res != null) {
            cadSucesso.setVisible(true);
            subsField.clear();
            classeTField.clear();
            produtoField.clear();
            apresentacaoField.clear();
            precoSField.clear();
        }
    }
}
