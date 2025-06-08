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
    public List<Farmacia> obterTodos(){
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
            System.out.println(e.getMessage());
        }

        return farmacias;
    }

    @Override
    public Farmacia obterPorId(int id){
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
    public Farmacia adicionar(Farmacia farmacia){
        String sqlFarmacia = "INSERT INTO farmacias(nome, cnpj, endereco, telefone) VALUES(?, ?, ?, ?)";
        Connection conn = DatabaseManager.getConnection();

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
            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return farmacia;
    }

    @Override
    public Farmacia atualizar(Farmacia farmacia){
        String sqlAtualizarFar = "UPDATE farmacias SET nome = ?, cnpj = ?, " +
                "endereco = ?, telefone = ? WHERE id = ?";

        Connection conn = DatabaseManager.getConnection();

            // 1. Atualiza os dados principais do medicamento
        try (PreparedStatement pstmt = conn.prepareStatement(sqlAtualizarFar)) {
                pstmt.setString(1, farmacia.getNome());
                pstmt.setString(2, farmacia.getCnpj());
                pstmt.setString(3, farmacia.getEndereco());
                pstmt.setString(4, farmacia.getTelefone());
                pstmt.setInt(5, farmacia.getId());
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return farmacia;
    }

    @Override
    public boolean deletar(int id){
        String sqlExcluirFarmacias = "DELETE FROM farmacias WHERE id = ?";

        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirFarmacias)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
