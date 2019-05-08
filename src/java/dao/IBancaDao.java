package dao;

import dominio.BancaAvaliadora;
import java.sql.SQLException;
import java.util.List;

public interface IBancaDao {
    public void adiciona(BancaAvaliadora banca) throws SQLException;

    public void altera(BancaAvaliadora banca) throws SQLException;

    public void remove(BancaAvaliadora banca) throws SQLException;

    public BancaAvaliadora getBancaAvaliadora(Integer id) throws SQLException;

    public List<BancaAvaliadora> listaBancaAvaliadora() throws SQLException;
}
