package services;

import dao.ProjetoDao;
import dominio.Projeto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoService {

    ProjetoDao daopro = new ProjetoDao();

    public void adcionaProjeto(Projeto projeto) throws SQLException {

        daopro.adiciona(projeto);//adcionando no banco

    }

    public void alteraProjeto(Projeto projeto) throws SQLException {

        daopro.altera(projeto);//alterando no banco

    }

    public Projeto buscaPorIdProjeto(Integer id) throws SQLException {

        Projeto projeto = daopro.getProjeto(id);

        return projeto;

    }
    public List<Projeto> ListaProjeto() throws SQLException {

        List<Projeto> projetos = new ArrayList<>();
            
            projetos = daopro.listaProjetos();
        

        return projetos;

    }
    
    public void removeProjeto(Projeto projeto) throws SQLException {
        
        daopro.remove(projeto);//removendo do banco

    }

}
