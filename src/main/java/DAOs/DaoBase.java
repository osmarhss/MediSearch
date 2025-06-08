package DAOs;

import java.sql.SQLException;
import java.util.List;

public abstract class DaoBase<T> {

    public abstract List<T> obterTodos();
    public abstract T obterPorId(int id);
    public abstract T adicionar(T objeto);
    public abstract T atualizar(T objeto);
    public abstract boolean deletar(int id);

}
