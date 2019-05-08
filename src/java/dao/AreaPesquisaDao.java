package dao;

import db.ConnectionFactory;
import dominio.AreaPesquisa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaPesquisaDao {

    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    String sql;
    
    public AreaPesquisa getAreaPesquisa(Integer id) throws SQLException {
        conn = cf.getConnection();
        
        sql = "select * from areaPesquisa where id_areaPesquisa=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        
        while (rs.next()) {
           
            
            areaPesquisa.setId_areaPesquisa(rs.getInt("id_areaPesquisa"));
            areaPesquisa.setNome(rs.getString("nome"));
            
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return areaPesquisa;
        
        
    }

    public List<AreaPesquisa> listaAreaPesquisa() throws SQLException {
         List<AreaPesquisa> areaPesquisas = new ArrayList<>();
        
        conn = cf.getConnection();
        
        sql = "select * from areaPesquisa";
        
        pstm = conn.prepareStatement(sql);
        
        rs = pstm.executeQuery();
        
        
        
        while (rs.next()) {
            
            AreaPesquisa areaPesquisa = new AreaPesquisa();
           
            areaPesquisa.setId_areaPesquisa(rs.getInt("id_areaPesquisa"));
            areaPesquisa.setNome(rs.getString("nome"));
            
            areaPesquisas.add(areaPesquisa);
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return areaPesquisas;
       
    }
}
