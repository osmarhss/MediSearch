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

public class BuscarMedController {
    @FXML
    public TextField idField;
    @FXML
    public Label subMed;
    @FXML
    public Label classeTMed;
    @FXML
    public Label prodMed;
    @FXML
    public Label aprMed;
    @FXML
    public Label prSugeridoMed;
    @FXML
    public Label notFound;

    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("medicamento.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) idField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Medicamento - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_buscarPorId() {
        MedicamentoDao medicamentoDao = new MedicamentoDao();
        var medicamento = medicamentoDao.obterPorId(Integer.parseInt(idField.getText()));

        if (medicamento != null) {
            subMed.setText(medicamento.getSubstancia());
            classeTMed.setText(medicamento.getClasseTerapeutica());
            prodMed.setText(medicamento.getProduto());
            aprMed.setText(medicamento.getApresentacao());
            prSugeridoMed.setText(Double.toString(medicamento.getPrecoSugerido()));
        } else {
            notFound.setVisible(true);
        }
    }
}
