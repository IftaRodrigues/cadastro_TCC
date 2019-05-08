package dao;

import db.ConnectionFactory;
import dominio.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao {

    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    public Professor getProfessor(Integer id) throws SQLException {
         conn = cf.getConnection();

        String sql = "select * from professor where id_professor=?";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        rs = pstmt.executeQuery();

        Professor professor = new Professor();
       

        while (rs.next()) {

            professor.setId_professor(rs.getInt("id_professor"));
            professor.setNome(rs.getString("nome"));
            professor.setCpf(rs.getString("cpf"));
            professor.setRg(rs.getString("rg"));
            professor.setEmail(rs.getString("email"));
            professor.setRegistro(rs.getInt("registro"));
            professor.setSalario(rs.getFloat("salario"));
           

        }

        rs.close();
        pstmt.close();
        conn.close();

        return professor;
        
    }

    public List<Professor> listaProfessores() throws SQLException {
       List<Professor> professores = new ArrayList<>();

        conn = cf.getConnection();

        pstmt = conn.prepareStatement("select * from professor");

        rs = pstmt.executeQuery();

        while (rs.next()) {
            
            
            Professor professor = new Professor();
            professor.setId_professor(rs.getInt("id_professor"));
            professor.setNome(rs.getString("nome"));
            professor.setCpf(rs.getString("cpf"));
            professor.setRg(rs.getString("rg"));
            professor.setEmail(rs.getString("email"));
            professor.setRegistro(rs.getInt("registro"));
            professor.setSalario(rs.getFloat("salario"));
            
            
            professores.add(professor);
        }
        rs.close();
        pstmt.close();
        conn.close();

        return professores;
    }
}
