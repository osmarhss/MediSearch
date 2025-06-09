package org.example.medisearch;

import DAOs.FarmaciaDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BuscarFarController {

    @FXML
    public TextField idField;

    @FXML
    public Label notFound;

    @FXML
    public Label nomeFar;

    @FXML
    public Label cnpjFar;

    @FXML
    public Label enderecoFar;

    @FXML
    public Label telefoneFar;

    public void voltarTelaAnterior() {
        try {
            // Carregar a nova tela (dashboard.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("farmacia.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) idField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Farmácia - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_buscarPorId() {
        int id = Integer.parseInt(idField.getText());
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        var farmacia = farmaciaDao.obterPorId(id);

        if(farmacia != null){
            nomeFar.setText(farmacia.getNome());
            cnpjFar.setText(farmacia.getCnpj());
            enderecoFar.setText(farmacia.getEndereco());
            telefoneFar.setText(farmacia.getTelefone());
        } else {
            notFound.setText("Farmácia não encontrada");
            nomeFar.setVisible(false);
            cnpjFar.setVisible(false);
            enderecoFar.setVisible(false);
            telefoneFar.setVisible(false);
        }
    }
}
