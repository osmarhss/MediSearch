package org.example.medisearch;

import DAOs.LaboratorioDao;
import Models.Laboratorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarLabController {
    @FXML
    private TextField nomeField;

    @FXML
    private TextField cnpjField;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField telefoneField;

    @FXML
    private void cadastrarLaboratorio() {
        String nome = nomeField.getText();
        String cnpj = cnpjField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();

        Laboratorio laboratorio = new Laboratorio(nome, cnpj, endereco, telefone);
        LaboratorioDao laboratorioDao = new LaboratorioDao();
        laboratorioDao.adicionar(laboratorio);

        System.out.println("Laboratório cadastrado:");
        System.out.println("Nome: " + nome);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);

        nomeField.clear();
        cnpjField.clear();
        enderecoField.clear();
        telefoneField.clear();
    }

    @FXML
    private void voltarTelaAnterior() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("laboratorio.fxml"));
            Parent dashboardRoot = fxmlLoader.load();

            // Obter o stage atual a partir de um componente (por exemplo, o botão)
            Stage stage = (Stage) enderecoField.getScene().getWindow();

            // Substituir a cena
            Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
            stage.setTitle("Laboratorio - MediSearch");
            stage.setScene(dashboardScene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
