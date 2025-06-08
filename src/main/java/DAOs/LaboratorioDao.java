package DAOs;

import DbConnection.DatabaseManager;
import Models.Laboratorio;
import Models.Medicamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaboratorioDao extends DaoBase<Laboratorio> {

    @Override
    public List<Laboratorio> obterTodos() {
        String sql = "SELECT * FROM laboratorios";
        List<Laboratorio> laboratorios = new ArrayList<>();
        Connection conn = DatabaseManager.getConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Laboratorio lab = new Laboratorio(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("endereco"),
                        rs.getString("telefone")
                );
                laboratorios.add(lab);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return laboratorios;
    }

    @Override
    public Laboratorio obterPorId(int id) throws SQLException {
        String sql = "SELECT * FROM laboratorios WHERE id = ?";
        Laboratorio laboratorio = null;
        Connection conn = DatabaseManager.getConnection();

        try(PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                laboratorio = new Laboratorio(
                  rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("endereco"),
                        rs.getString("telefone")
                );
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return laboratorio;
    }

    @Override
    public Laboratorio adicionar(Laboratorio laboratorio) throws SQLException {
        String sqlLaboratorio = "INSERT INTO laboratorios(nome, cnpj, endereco, telefone) VALUES(?, ?, ?, ?)";
        String sqlAssociacao = "INSERT INTO medicamento_laboratorio(medicamento_id, laboratorio_id) VALUES(?, ?)";
        Connection conn = DatabaseManager.getConnection();

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtLab = conn.prepareStatement(sqlLaboratorio, Statement.RETURN_GENERATED_KEYS)) {
                pstmtLab.setString(1, laboratorio.getNome());
                pstmtLab.setString(2, laboratorio.getCnpj());
                pstmtLab.setString(3, laboratorio.getEndereco());
                pstmtLab.setString(4, laboratorio.getTelefone());
                pstmtLab.executeUpdate();

                ResultSet rs = pstmtLab.getGeneratedKeys();
                if (rs.next()) {
                    laboratorio.setId(rs.getInt(1));
                }
            }

            if(laboratorio.getMedicamentoList() != null || !laboratorio.getMedicamentoList().isEmpty()) {
                try (PreparedStatement pstmtAssoc = conn.prepareStatement(sqlAssociacao)) {

                    for (Medicamento med : laboratorio.getMedicamentoList()) {
                        // Id's para tabela intermediária
                        pstmtAssoc.setInt(1, med.getId());
                        pstmtAssoc.setInt(2, laboratorio.getId());
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

        return laboratorio;
    }

    @Override
    public Laboratorio atualizar(Laboratorio laboratorio) throws SQLException {
        String sqlAtualizarLab = "UPDATE laboratorios SET nome = ?, cnpj = ?, endereco = ?, telefone = ? WHERE id = ?";
        String sqlExcluirAssociacoes = "DELETE FROM medicamento_laboratorio WHERE laboratorio_id = ?";
        String sqlInserirAssociacao = "INSERT INTO medicamento_laboratorio(medicamento_id, laboratorio_id) VALUES(?, ?)";
        Connection conn = DatabaseManager.getConnection();

        try {
            // Inicia a transação
            conn.setAutoCommit(false);

            // 1. Atualiza os dados principais do medicamento
            try (PreparedStatement pstmt = conn.prepareStatement(sqlAtualizarLab)) {
                pstmt.setString(1, laboratorio.getNome());
                pstmt.setString(2, laboratorio.getCnpj());
                pstmt.setString(3, laboratorio.getEndereco());
                pstmt.setString(4, laboratorio.getTelefone());
                pstmt.setInt(5, laboratorio.getId());
                pstmt.executeUpdate();
            }

            // 2. Exclui todas as associações antigas com laboratórios
            try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirAssociacoes)) {
                pstmt.setInt(1, laboratorio.getId());
                pstmt.executeUpdate();
            }

            // 3. Insere as novas associações com laboratórios
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInserirAssociacao)) {
                for (Medicamento med : laboratorio.getMedicamentoList()) {
                    pstmt.setInt(1, med.getId());
                    pstmt.setInt(2, laboratorio.getId());
                    pstmt.executeUpdate();
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

        return laboratorio;
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sqlExcluirLaboratorio = "DELETE FROM laboratorios WHERE id = ?";
        String sqlExcluirAssociacoes = "DELETE FROM medicamento_laboratorio WHERE laboratorio_id = ?";

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
            try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirLaboratorio)) {
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
