package org.example.medisearch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class laboratorioController {
    @FXML
    private Label lblMenu;
    @FXML
    private void onMouseClickedMenu(){
        try {
            // Carregar a nova tela (dashboard.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) lblMenu.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("MediSearch - Menu");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
