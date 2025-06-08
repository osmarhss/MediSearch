package DAOs;

import DbConnection.DatabaseManager;
import Models.Laboratorio;
import Models.Medicamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDao extends DaoBase<Medicamento> {

    // private static Connection conn = DatabaseManager.getConnection();

    @Override
    public List<Medicamento> obterTodos() throws SQLException {
        String sql = "SELECT m.id as med_id, m.substancia as med_sub, m.classeTerapeutica as med_classT," +
                "m.produto as med_produto, m.apresentacao as med_apresentacao, m.precoSugerido as med_PrSugerido," +
                "l.id as lab_id, l.nome as lab_nome, l.cnpj as lab_cnpj, l.endereco as lab_endereco," +
                "l.telefone as lab_telefone " +
                "FROM medicamentos m " +
                "LEFT JOIN medicamento_laboratorio ml ON m.id = ml.medicamento_id " +
                "LEFT JOIN laboratorios l ON ml.laboratorio_id = l.id";

        Connection conn = DatabaseManager.getConnection();
        List<Medicamento> medicamentos = new ArrayList<>();
        Medicamento medicamentoAtual = null;

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                int medId = rs.getInt("med_id");

                // Se o medicamento atual é nulo ou mudou, cria um novo
                if(medicamentoAtual == null || medicamentoAtual.getId() != medId){
                    medicamentoAtual = new Medicamento(
                            medId, rs.getString("med_sub"),
                            rs.getString("med_classT"),
                            rs.getString("med_produto"),
                            rs.getString("med_apresentacao"),
                            rs.getDouble("med_PrSugerido"));

                    medicamentos.add(medicamentoAtual);
                }

                // Adiciona o laboratório ao medicamento atual (se existir)
                if(rs.getInt("lab_id") != 0){
                    Laboratorio lab = new Laboratorio(
                            rs.getInt("lab_id"),
                            rs.getString("lab_nome"),
                            rs.getString("lab_cnpj"),
                            rs.getString("lab_endereco"),
                            rs.getString("lab_telefone")
                    );

                    medicamentoAtual.adicionarLaboratorio(lab);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return medicamentos;
    }

    @Override
    public Medicamento obterPorId(int id) throws SQLException {
        String sql = "SELECT * FROM medicamentos WHERE id = ?";
        Medicamento medicamento = null;
        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                medicamento = new Medicamento(
                        rs.getInt("id"),
                        rs.getString("med_sub"),
                        rs.getString("med_classT"),
                        rs.getString("med_produto"),
                        rs.getString("med_apresentacao"),
                        rs.getDouble("med_PrSugerido"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return medicamento;
    }

    @Override
    public Medicamento adicionar(Medicamento medicamento) throws SQLException {
        String sqlMedicamento = "INSERT INTO medicamentos(substancia, classeTerapeutica, produto, apresentacao, precoSugerido) VALUES(?, ?, ?, ?, ?)";
        String sqlAssociacao = "INSERT INTO medicamento_laboratorio(medicamento_id, laboratorio_id) VALUES(?, ?)";
        Connection conn = DatabaseManager.getConnection();

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtMed = conn.prepareStatement(sqlMedicamento, Statement.RETURN_GENERATED_KEYS)) {
                pstmtMed.setString(1, medicamento.getSubstancia());
                pstmtMed.setString(2, medicamento.getClasseTerapeutica());
                pstmtMed.setString(3, medicamento.getProduto());
                pstmtMed.setString(4, medicamento.getApresentacao());
                pstmtMed.setDouble(5, medicamento.getPrecoSugerido());
                pstmtMed.executeUpdate();

                ResultSet rs = pstmtMed.getGeneratedKeys();
                if (rs.next()) {
                    medicamento.setId(rs.getInt(1));
                }
            }

            if(medicamento.getLaboratorios() != null && !medicamento.getLaboratorios().isEmpty()) {
                try (PreparedStatement pstmtAssoc = conn.prepareStatement(sqlAssociacao)) {

                    for (Laboratorio lab : medicamento.getLaboratorios()) {
                        // Id's para tabela intermediária
                        pstmtAssoc.setInt(1, medicamento.getId());
                        pstmtAssoc.setInt(2, lab.getId());
                        pstmtAssoc.executeUpdate();
                    }
                }
            }

            conn.commit(); // Efetiva a transação

        } catch (SQLException e) {
            conn.rollback(); // Desfaz em caso de erro
            throw e;
        } finally {
            conn.setAutoCommit(true); // Reabilita o auto-commit
        }

        return medicamento;
    }

    @Override
    public Medicamento atualizar(Medicamento medicamento) throws SQLException {

        String sqlAtualizarMed = "UPDATE medicamentos SET substancia = ?, classeTerapeutica = ?," +
                "produto = ?, apresentacao = ?, precoSugerido = ? WHERE id = ?";
        String sqlExcluirAssociacoes = "DELETE FROM medicamento_laboratorio WHERE medicamento_id = ?";
        String sqlInserirAssociacao = "INSERT INTO medicamento_laboratorio(medicamento_id, laboratorio_id) VALUES(?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try {
            // Inicia a transação
            conn.setAutoCommit(false);

            // 1. Atualiza os dados principais do medicamento
            try (PreparedStatement pstmt = conn.prepareStatement(sqlAtualizarMed)) {
                pstmt.setString(1, medicamento.getSubstancia());
                pstmt.setString(2, medicamento.getClasseTerapeutica());
                pstmt.setString(3, medicamento.getProduto());
                pstmt.setString(4, medicamento.getApresentacao());
                pstmt.setDouble(5, medicamento.getPrecoSugerido());
                pstmt.setInt(6, medicamento.getId());
                pstmt.executeUpdate();
            }

            if(medicamento.getLaboratorios() != null && !medicamento.getLaboratorios().isEmpty()) {
                // 2. Exclui todas as associações antigas com laboratórios
                try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirAssociacoes)) {
                    pstmt.setInt(1, medicamento.getId());
                    pstmt.executeUpdate();
                }

                // 3. Insere as novas associações com laboratórios
                try (PreparedStatement pstmt = conn.prepareStatement(sqlInserirAssociacao)) {
                    for (Laboratorio lab : medicamento.getLaboratorios()) {
                        pstmt.setInt(1, medicamento.getId());
                        pstmt.setInt(2, lab.getId());
                        pstmt.executeUpdate();
                    }
                }
            }
            // Se tudo deu certo, efetiva a transação
            conn.commit();

        } catch (SQLException e) {
            // Em caso de erro, desfaz todas as operações
            conn.rollback();
            throw e; // Propaga a exceção para a camada que chamou o método
        } finally {
            // Garante que o autocommit será reativado
            conn.setAutoCommit(true);
        }
        return medicamento;
    }

    @Override
    public boolean deletar(int id) throws SQLException{
        String sqlExcluirMedicamento = "DELETE FROM medicamentos WHERE id = ?";
        String sqlExcluirAssociacoes = "DELETE FROM medicamento_laboratorio WHERE medicamento_id = ?";

        Medicamento medicamento = null;
        Connection conn = DatabaseManager.getConnection();

        try {
            // Inicia a transação
            conn.setAutoCommit(false);

            // 1. Exclui as associações primeiro para evitar erro de chave estrangeira
            try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirAssociacoes)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

            // 2. Exclui o medicamento da tabela principal
            try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirMedicamento)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

            // Efetiva a transação se ambas as exclusões funcionarem
            conn.commit();

        } catch (SQLException e) {
            // Desfaz a transação em caso de erro
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

        return true;
    }
}
