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
    public List<Medicamento> obterTodos() {
        String sql = "SELECT m.id as med_id, m.substancia as med_sub, m.classeTerapeutica as med_classT," +
                "m.produto as med_produto, m.apresentacao as med_apresentacao, m.precoSugerido as med_PrSugerido " +
                "FROM medicamentos m ";

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
                            medId,
                            rs.getString("med_sub"),
                            rs.getString("med_classT"),
                            rs.getString("med_produto"),
                            rs.getString("med_apresentacao"),
                            rs.getDouble("med_PrSugerido"));

                    medicamentos.add(medicamentoAtual);
                }
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return medicamentos;
    }

    @Override
    public Medicamento obterPorId(int id){
        String sql = "SELECT * FROM medicamentos WHERE id = ?";
        Medicamento medicamento = null;
        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                medicamento = new Medicamento(
                        rs.getInt("id"),
                        rs.getString("substancia"),
                        rs.getString("classeTerapeutica"),
                        rs.getString("produto"),
                        rs.getString("apresentacao"),
                        rs.getDouble("precoSugerido"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return medicamento;
    }

    @Override
    public Medicamento adicionar(Medicamento medicamento){
        String sqlMedicamento = "INSERT INTO medicamentos(substancia, classeTerapeutica, produto, apresentacao, precoSugerido) VALUES(?, ?, ?, ?, ?)";
        Connection conn = DatabaseManager.getConnection();

        try (PreparedStatement pstmtMed = conn.prepareStatement(sqlMedicamento)) {
                pstmtMed.setString(1, medicamento.getSubstancia());
                pstmtMed.setString(2, medicamento.getClasseTerapeutica());
                pstmtMed.setString(3, medicamento.getProduto());
                pstmtMed.setString(4, medicamento.getApresentacao());
                pstmtMed.setDouble(5, medicamento.getPrecoSugerido());
                pstmtMed.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return medicamento;
    }

    @Override
    public Medicamento atualizar(Medicamento medicamento){

        String sqlAtualizarMed = "UPDATE medicamentos SET substancia = ?, classeTerapeutica = ?," +
                "produto = ?, apresentacao = ?, precoSugerido = ? WHERE id = ?";

        Connection conn = DatabaseManager.getConnection();

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
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return medicamento;
    }

    @Override
    public boolean deletar(int id){
        String sqlExcluirMedicamento = "DELETE FROM medicamentos WHERE id = ?";

        Medicamento medicamento = null;
        Connection conn = DatabaseManager.getConnection();

        // 1. Exclui o medicamento da tabela principal

        try (PreparedStatement pstmt = conn.prepareStatement(sqlExcluirMedicamento)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
