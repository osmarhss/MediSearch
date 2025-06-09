package org.example.medisearch;

import DAOs.FarmaciaDao;
import Models.Farmacia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AtualizarFarController {

    @FXML
    public Label attSucesso;

    @FXML
    public TextField idField;

    @FXML
    public TextField nomeField;

    @FXML
    public TextField cnpjField;

    @FXML
    public TextField enderecoField;

    @FXML
    public TextField telefoneField;

    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("farmacia.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) enderecoField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Farmácia - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_atualizarFar() {

        FarmaciaDao farmaciaDao = new FarmaciaDao();

        int id = Integer.parseInt(idField.getText());
        String nome = nomeField.getText();
        String cnpj = cnpjField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();

        Farmacia farmacia = new Farmacia(id, nome, cnpj, endereco, telefone);
        var farmaciaAtt = farmaciaDao.atualizar(farmacia);

        if (farmaciaAtt != null)
            attSucesso.setVisible(true);

    }
}
