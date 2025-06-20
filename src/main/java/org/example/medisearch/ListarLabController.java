package org.example.medisearch;

import DAOs.LaboratorioDao;
import Models.Laboratorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListarLabController {

    @FXML
    private TableView<Laboratorio> tabelaLab;
    @FXML
    private TableColumn<Laboratorio, Integer> id_coluna;
    @FXML
    private TableColumn<Laboratorio, String> cnpj_coluna;
    @FXML
    private TableColumn<Laboratorio, String> nome_coluna;

    @FXML
    public void initialize() {
        id_coluna.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnpj_coluna.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        nome_coluna.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("laboratorio.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) tabelaLab.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Laboratorio - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_buscarTodos(){
        LaboratorioDao laboratorioDao = new LaboratorioDao();
        var listaLaboratorios = laboratorioDao.obterTodos();
        for (Laboratorio lab : listaLaboratorios) {
            tabelaLab.getItems().add(lab);
        }
    }
}
