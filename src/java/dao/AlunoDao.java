package dao;

import db.ConnectionFactory;
import dominio.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    String sql = null;

    public Aluno getAluno(Integer id) throws SQLException {

        conn = cf.getConnection();

        sql = "select * from aluno where id_aluno=?";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        rs = pstmt.executeQuery();

        Aluno aluno = new Aluno();

        while (rs.next()) {

            
            aluno.setId_aluno(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setRg(rs.getString("rg"));
            aluno.setEmail(rs.getString("email"));
            aluno.setMatricula(rs.getInt("matricula"));

        }

        rs.close();
        pstmt.close();
        conn.close();

        return aluno;
    }

    public List<Aluno> listaAlunos() throws SQLException {

        List<Aluno> alunos = new ArrayList<>();

        conn = cf.getConnection();

        pstmt = conn.prepareStatement("select * from aluno");

        rs = pstmt.executeQuery();

        while (rs.next()) {
            
            Aluno aluno = new Aluno();

            aluno.setId_aluno(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setRg(rs.getString("rg"));
            aluno.setEmail(rs.getString("email"));
            aluno.setMatricula(rs.getInt("matricula"));

            alunos.add(aluno);
        }
        rs.close();
        pstmt.close();
        conn.close();

        return alunos;
    }

}
