package DAOs;

import java.sql.SQLException;
import java.util.List;

public abstract class DaoBase<T> {

    public abstract List<T> obterTodos() throws SQLException;
    public abstract T obterPorId(int id) throws SQLException;
    public abstract T adicionar(T objeto) throws SQLException;
    public abstract T atualizar(T objeto) throws SQLException;
    public abstract boolean deletar(int id) throws SQLException;

}
