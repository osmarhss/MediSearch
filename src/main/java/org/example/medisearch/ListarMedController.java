package org.example.medisearch;

import DAOs.LaboratorioDao;
import DAOs.MedicamentoDao;
import Models.Farmacia;
import Models.Laboratorio;
import Models.Medicamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListarMedController {

    @FXML
    private TableView<Medicamento> tabelaLab;
    @FXML
    private TableColumn<Medicamento, Integer> id_coluna;
    @FXML
    private TableColumn<Medicamento, String> sub_coluna;
    @FXML
    private TableColumn<Medicamento, Double> precoSug_coluna;

    @FXML
    public void initialize() {
        id_coluna.setCellValueFactory(new PropertyValueFactory<>("id"));
        sub_coluna.setCellValueFactory(new PropertyValueFactory<>("substancia"));
        precoSug_coluna.setCellValueFactory(new PropertyValueFactory<>("precoSugerido"));
    }

    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("medicamento.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) tabelaLab.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Medicamento - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_buscarTodos() {
        MedicamentoDao medicamentoDao = new MedicamentoDao();
        var listaMedicamentos = medicamentoDao.obterTodos();
        for (Medicamento med : listaMedicamentos) {
            tabelaLab.getItems().add(med);
        }
    }
}
