package org.example.medisearch;

import DAOs.LaboratorioDao;
import DAOs.MedicamentoDao;
import Models.Laboratorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuscarLaboratorioController {

    @FXML
    private TextField idField;

    @FXML
    private Label nomeLab;

    @FXML
    private Label cnpjLab;

    @FXML
    private Label enderecoLab;

    @FXML
    private Label telefoneLab;

    @FXML
    private Label notFound;


    @FXML
    private void voltarTelaAnterior(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("laboratorio.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) idField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Laboratorio - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void btn_buscarPorId(){
        int id = Integer.parseInt(idField.getText());
        LaboratorioDao laboratorioDao = new LaboratorioDao();
        var laboratorio = laboratorioDao.obterPorId(id);

        if(laboratorio != null){
            nomeLab.setText(laboratorio.getNome());
            cnpjLab.setText(laboratorio.getCnpj());
            enderecoLab.setText(laboratorio.getEndereco());
            telefoneLab.setText(laboratorio.getTelefone());
        } else {
            notFound.setText("Laboratório não encontrado");
            nomeLab.setVisible(false);
            cnpjLab.setVisible(false);
            enderecoLab.setVisible(false);
            telefoneLab.setVisible(false);
        }
    }
}
