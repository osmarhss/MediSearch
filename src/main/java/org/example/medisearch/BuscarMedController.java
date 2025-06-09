package org.example.medisearch;

import DAOs.MedicamentoDao;
import Models.Medicamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
