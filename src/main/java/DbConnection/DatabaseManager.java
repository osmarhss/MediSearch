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
                " precoSugerido DECIMAL NOT NULL," +
                ");";

        // Tabela de associação para o relacionamento N-N
        String sqlMedicamentoLaboratorio = "CREATE TABLE IF NOT EXISTS medicamento_laboratorio (" +
                " medicamento_id INTEGER," +
                " laboratorio_id INTEGER," +
                " PRIMARY KEY (medicamento_id, laboratorio_id)," +
                " FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id)," +
                " FOREIGN KEY (laboratorio_id) REFERENCES laboratorios(id)" +
                ");";

        String sqlFarmacia = "CREATE TABLE IF NOT EXISTS farmacias (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL," +
                " cnpj TEXT NOT NULL," +
                " endereco TEXT NOT NULL," +
                " telefone TEXT NOT NULL" +
                ");";

        String sqlMedicamentoFarmacia = "CREATE TABLE IF NOT EXISTS medicamento_farmacia (" +
                " medicamento_id INTEGER," +
                " farmacia_id INTEGER," +
                " PRIMARY KEY (medicamento_id, farmacia_id)," +
                " FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id)," +
                " FOREIGN KEY (farmacia_id) REFERENCES farmacia(id)" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlLaboratorio);
            stmt.execute(sqlMedicamento);
            stmt.execute(sqlMedicamentoLaboratorio);
            stmt.execute(sqlFarmacia);
            stmt.execute(sqlMedicamentoFarmacia);
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
