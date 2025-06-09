package org.example.medisearch;

import DAOs.FarmaciaDao;
import Models.Farmacia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarFarController {
    @FXML
    private TextField nomeField;

    @FXML
    private TextField cnpjField;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField telefoneField;

    @FXML
    private Label cadSucesso;

    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("farmacia.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) enderecoField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Farmacia - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_cadastrarFar() {
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        String nome = nomeField.getText();
        String cnpj = cnpjField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();

        Farmacia farmacia = new Farmacia(nome, cnpj, endereco, telefone);
        var farm = farmaciaDao.adicionar(farmacia);

        if (farm != null) {
            cadSucesso.setVisible(true);
            nomeField.clear();
            cnpjField.clear();
            enderecoField.clear();
            telefoneField.clear();
        }
    }
}
