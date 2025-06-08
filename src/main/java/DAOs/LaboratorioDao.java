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
    public Laboratorio obterPorId(int id){
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
    public Laboratorio adicionar(Laboratorio laboratorio){
        String sqlLaboratorio = "INSERT INTO laboratorios(nome, cnpj, endereco, telefone) VALUES(?, ?, ?, ?)";
        Connection conn = DatabaseManager.getConnection();

            try (PreparedStatement pstmtLab = conn.prepareStatement(sqlLaboratorio)) {
                pstmtLab.setString(1, laboratorio.getNome());
                pstmtLab.setString(2, laboratorio.getCnpj());
                pstmtLab.setString(3, laboratorio.getEndereco());
                pstmtLab.setString(4, laboratorio.getTelefone());
                pstmtLab.executeUpdate();

            } catch (SQLException e) {
            System.out.println(e.getMessage());
            }

        return laboratorio;
    }

    @Override
    public Laboratorio atualizar(Laboratorio laboratorio){
        String sqlAtualizarLab = "UPDATE laboratorios SET nome = ?, cnpj = ?, endereco = ?, telefone = ? WHERE id = ?";
        Connection conn = DatabaseManager.getConnection();

        try(PreparedStatement pstmt = conn.prepareStatement(sqlAtualizarLab)) {
                pstmt.setString(1, laboratorio.getNome());
                pstmt.setString(2, laboratorio.getCnpj());
                pstmt.setString(3, laboratorio.getEndereco());
                pstmt.setString(4, laboratorio.getTelefone());
                pstmt.setInt(5, laboratorio.getId());
                pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return laboratorio;
    }

    @Override
    public boolean deletar(int id){
        String sqlExcluirLaboratorio = "DELETE FROM laboratorios WHERE id = ?";

        Connection conn = DatabaseManager.getConnection();

        try {
            // 1. Exclui o medicamento da tabela principal
            try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirLaboratorio)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
