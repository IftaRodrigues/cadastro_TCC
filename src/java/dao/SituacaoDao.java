package dao;

import db.ConnectionFactory;
import dominio.Situacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SituacaoDao {
    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    String sql;
    
    public Situacao getSituacao(Integer id) throws SQLException {
        
        conn = cf.getConnection();
        
        sql = "select * from situacao where id_situacao=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        Situacao situacao = new Situacao();
        
        while (rs.next()) {
           
            
            situacao.setId_situacao(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("descricao"));
           
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return situacao;
        
    }

    public List<Situacao> listaSituacao() throws SQLException {
        
        List<Situacao> situacoes = new ArrayList<>();
        
        conn = cf.getConnection();
        
        sql = "select * from situacao";
        
        pstm = conn.prepareStatement(sql);
        
        rs = pstm.executeQuery();
        
       
        
        while (rs.next()) {
            Situacao situacao = new Situacao();

            situacao.setId_situacao(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("descricao"));
            
            situacoes.add(situacao);
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return situacoes;
       
    }
}
