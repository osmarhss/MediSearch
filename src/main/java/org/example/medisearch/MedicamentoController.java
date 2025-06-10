package org.example.medisearch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MedicamentoController {
    @FXML private Label lblMenu;

    @FXML
    private void onMouseClickedMenu(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSalvarButton(){
        // Salvar o medicamento
    }
    @FXML
    private void handleEditarButton(){

    }
    @FXML
    private void handleExcluirButton(){}
    @FXML
    private void handlePesquisarButton(){

    }
}