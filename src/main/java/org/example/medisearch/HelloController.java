package org.example.medisearch;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private ListView labelconfirmacao;
    @FXML
    private void onLoginButtonClick() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        
    }
}