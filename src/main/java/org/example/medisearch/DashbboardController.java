package org.example.medisearch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashbboardController {
    @FXML
    private Label lblMedicamento;
    @FXML
    private Label lblLAbortorio;
    @FXML
    private Label lblFarmacia;
    @FXML
    private Label lblDados;
    @FXML private Label lblConta;
    @FXML
    private void onMouseClickedmedicamento() {
        try {
            // Carregar a nova tela (dashboard.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Med.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) lblMedicamento.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("MediSearch - Medicamentos");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onMOuseClickedLAboratorio() {
        try {
            // Carregar a nova tela (dashboard.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("laboratorio.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) lblLAbortorio.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("MediSearch - Medicamentos");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onMouseClickedFarmacia(){
        try {
            // Carregar a nova tela (dashboard.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("laboratorio.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) lblFarmacia.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("MediSearch - Medicamentos");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onMouseClickedConta(){
        try {
            // Carregar a nova tela (dashboard.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("laboratorio.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) lblConta.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("MediSearch - Medicamentos");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}