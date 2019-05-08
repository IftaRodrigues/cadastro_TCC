package dao;

import dominio.Projeto;
import java.sql.SQLException;
import java.util.List;

public interface IProjetoDao {
     public void adiciona(Projeto projeto) throws SQLException;

    public void altera(Projeto projeto) throws SQLException;

    public void remove(Projeto projeto) throws SQLException;

    public Projeto getProjeto(Integer id) throws SQLException;

    public List<Projeto> listaProjetos() throws SQLException;
}
