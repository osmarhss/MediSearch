package org.example.medisearch;

import DAOs.LaboratorioDao;
import Models.Laboratorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AtualizarLabController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cnpjField;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField telefoneField;

    @FXML
    private Label attSucesso;

    public void voltarTelaAnterior() {

        try {
            // Carregar a nova tela (dashboard.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("farmacia.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) nomeField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Farmácia - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizarLaboratorio() {

        LaboratorioDao laboratorioDao = new LaboratorioDao();

        Laboratorio laboratorio = new Laboratorio(
                Integer.parseInt(idField.getText()),
                nomeField.getText(),
                cnpjField.getText(),
                enderecoField.getText(),
                telefoneField.getText()
        );

        var laboratorioAtt = laboratorioDao.atualizar(laboratorio);

        if (laboratorioAtt != null)
            attSucesso.setVisible(true);
    }
}
