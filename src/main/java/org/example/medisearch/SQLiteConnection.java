package org.example.medisearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:meu_banco.db"; // Caminho para o seu banco
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
