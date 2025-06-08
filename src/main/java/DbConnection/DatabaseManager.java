package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:medicamentos.db";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                criarTabelas(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static void criarTabelas(Connection conn) {
        String sqlLaboratorio = "CREATE TABLE IF NOT EXISTS laboratorios (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL UNIQUE," +
                " cnpj TEXT NOT NULL," +
                " endereco TEXT NOT NULL," +
                " telefone TEXT NOT NULL" +
                ");";

        String sqlMedicamento = "CREATE TABLE IF NOT EXISTS medicamentos (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " substancia TEXT NOT NULL," +
                " classeTerapeutica TEXT NOT NULL," +
                " produto TEXT NOT NULL," +
                " apresentacao TEXT NOT NULL," +
                " precoSugerido DECIMAL NOT NULL" +
                ");";

        String sqlFarmacia = "CREATE TABLE IF NOT EXISTS farmacias (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL," +
                " cnpj TEXT NOT NULL," +
                " endereco TEXT NOT NULL," +
                " telefone TEXT NOT NULL" +
                ");";

        String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuarios (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " email TEXT NOT NULL UNIQUE," +
                " senha TEXT NOT NULL" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlLaboratorio);
            System.out.println("Laboratorios criados com sucesso!");
            stmt.execute(sqlMedicamento);
            System.out.println("Medicamentos criados com sucesso!");
            stmt.execute(sqlFarmacia);
            System.out.println("Farmacias criados com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
