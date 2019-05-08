package services;

import dao.BancaDao;
import dominio.BancaAvaliadora;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancaService {

    BancaDao daobanca = new BancaDao();

    public void adcionaBanca(BancaAvaliadora banca) throws SQLException {

        daobanca.adiciona(banca);//adcionando no banco

    }

    public void alteraBanca(BancaAvaliadora banca) throws SQLException {

        daobanca.altera(banca);//alterando no banco

    }

    public BancaAvaliadora buscaPorIdBanca(Integer id) throws SQLException {
        BancaAvaliadora banca = daobanca.getBancaAvaliadora(id);

        return banca;

    }
    
    public List<BancaAvaliadora> ListaBanca() throws SQLException {

        List<BancaAvaliadora> bancas = new ArrayList<>();
            
            bancas = daobanca.listaBancaAvaliadora();
        

        return bancas;

    }

    public void removeBanca(BancaAvaliadora banca) throws SQLException {

        daobanca.remove(banca);//removendo do banco

    }

}
