package org.example.medisearch;

import DAOs.FarmaciaDao;
import DAOs.LaboratorioDao;
import Models.Farmacia;
import Models.Laboratorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListarFarController {

    @FXML
    private TableView<Farmacia> tabelaLab;
    @FXML
    private TableColumn<Farmacia, Integer> id_coluna;

    @FXML
    private TableColumn<Farmacia, String> cnpj_coluna;

    @FXML
    private TableColumn<Farmacia, String> nome_coluna;

    @FXML
    public void initialize() {
        id_coluna.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnpj_coluna.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        nome_coluna.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    public void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("farmacia.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) tabelaLab.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Farmácia - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_buscarTodos(){
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        var listaFarmacias = farmaciaDao.obterTodos();
        for (Farmacia far : listaFarmacias) {
            tabelaLab.getItems().add(far);
        }
    }
}
