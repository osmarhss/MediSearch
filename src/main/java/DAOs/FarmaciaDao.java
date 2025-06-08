package DAOs;

import DbConnection.DatabaseManager;
import Models.Farmacia;
import Models.Laboratorio;
import Models.Medicamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaDao extends DaoBase<Farmacia> {


    @Override
    public List<Farmacia> obterTodos() throws SQLException {
        String sql = "SELECT * FROM farmacias";
        List<Farmacia> farmacias = new ArrayList<>();
        Connection conn = DatabaseManager.getConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Farmacia far = new Farmacia(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("endereco"),
                        rs.getString("telefone")
                );
                farmacias.add(far);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return farmacias;
    }

    @Override
    public Farmacia obterPorId(int id) throws SQLException {
        String sql = "SELECT * FROM farmacias WHERE id = ?";
        Farmacia farmacia = null;
        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                farmacia = new Farmacia(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("endereco"),
                        rs.getString("telefone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return farmacia;
    }

    @Override
    public Farmacia adicionar(Farmacia farmacia) throws SQLException {
        String sqlFarmacia = "INSERT INTO laboratorios(nome, cnpj, endereco, telefone) VALUES(?, ?, ?, ?)";
        String sqlAssociacao = "INSERT INTO medicamento_farmacia(medicamento_id, farmacia_id) VALUES(?, ?)";
        Connection conn = DatabaseManager.getConnection();

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtLab = conn.prepareStatement(sqlFarmacia, Statement.RETURN_GENERATED_KEYS)) {
                pstmtLab.setString(1, farmacia.getNome());
                pstmtLab.setString(2, farmacia.getCnpj());
                pstmtLab.setString(3, farmacia.getEndereco());
                pstmtLab.setString(4, farmacia.getTelefone());
                pstmtLab.executeUpdate();

                ResultSet rs = pstmtLab.getGeneratedKeys();
                if (rs.next()) {
                    farmacia.setId(rs.getInt(1));
                }
            }

            if(farmacia.getMedicamentoList() != null || !farmacia.getMedicamentoList().isEmpty()) {
                try (PreparedStatement pstmtAssoc = conn.prepareStatement(sqlAssociacao)) {

                    for (Medicamento med : farmacia.getMedicamentoList()) {
                        // Id's para tabela intermediária
                        pstmtAssoc.setInt(1, med.getId());
                        pstmtAssoc.setInt(2, farmacia.getId());
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

        return farmacia;
    }

    @Override
    public Farmacia atualizar(Farmacia farmacia) throws SQLException {
        String sqlAtualizarFar = "UPDATE farmacias SET nome = ?, cnpj = ?," +
                "endereco = ?, telefone = ? WHERE id = ?";
        String sqlExcluirAssociacoes = "DELETE FROM medicamento_farmacia WHERE farmacia_id = ?";
        String sqlInserirAssociacao = "INSERT INTO medicamento_farmacia(medicamento_id, farmacia_id) VALUES(?, ?)";

        Connection conn = DatabaseManager.getConnection();

        try {
            // Inicia a transação
            conn.setAutoCommit(false);

            // 1. Atualiza os dados principais do medicamento
            try (PreparedStatement pstmt = conn.prepareStatement(sqlAtualizarFar)) {
                pstmt.setString(1, farmacia.getNome());
                pstmt.setString(2, farmacia.getCnpj());
                pstmt.setString(3, farmacia.getEndereco());
                pstmt.setString(4, farmacia.getTelefone());
                pstmt.setInt(5, farmacia.getId());
                pstmt.executeUpdate();
            }

            if(farmacia.getMedicamentoList() != null || !farmacia.getMedicamentoList().isEmpty()) {
                // 2. Exclui todas as associações antigas com laboratórios
                try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirAssociacoes)) {
                    pstmt.setInt(1, farmacia.getId());
                    pstmt.executeUpdate();
                }

                // 3. Insere as novas associações com laboratórios
                try (PreparedStatement pstmt = conn.prepareStatement(sqlInserirAssociacao)) {
                    for (Medicamento med : farmacia.getMedicamentoList()) {
                        pstmt.setInt(1, farmacia.getId());
                        pstmt.setInt(2, med.getId());
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
        return farmacia;
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sqlExcluirFarmacias = "DELETE FROM farmacias WHERE id = ?";
        String sqlExcluirAssociacoes = "DELETE FROM medicamento_farmacia WHERE farmacia_id = ?";

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
            try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirFarmacias)) {
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
