package org.example.medisearch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;


public class HelloController {
    @FXML
    private Button btnlogin;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label labelconfirmacao;

    @FXML
    private void onLoginButtonClick() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (!name.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
            try {
                // Carregar a nova tela (dashboard.fxml)
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
                Parent dashboardRoot = fxmlLoader.load();

                // Obter o stage atual a partir de um componente (por exemplo, o botão)
                Stage stage = (Stage) btnlogin.getScene().getWindow();

                // Substituir a cena
                Scene dashboardScene = new Scene(dashboardRoot, 600, 400); // ajuste o tamanho conforme necessário
                stage.setTitle("MeduSearch - Menu");
                stage.setScene(dashboardScene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                labelconfirmacao.setText("Erro ao carregar a próxima tela.");
            }
        } else {
            labelconfirmacao.setText("Preencha todos os campos.");
        }
    }
}
