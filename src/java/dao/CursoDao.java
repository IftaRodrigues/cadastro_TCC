package dao;

import db.ConnectionFactory;
import dominio.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {

    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    String sql;

    public Curso getCurso(Integer id) throws SQLException {

        conn = cf.getConnection();

        sql = "select * from curso where id_curso=?";

        pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        rs = pstm.executeQuery();

        Curso curso = new Curso();

        while (rs.next()) {

            curso.setId_curso(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome"));
            curso.setCargaHoraria(rs.getInt("cargaHoraria"));

        }
        rs.close();
        pstm.close();
        conn.close();

        return curso;

    }

    public List<Curso> listaCurso() throws SQLException {

        List<Curso> cursos = new ArrayList<>();

        conn = cf.getConnection();

        sql = "select * from curso";

        pstm = conn.prepareStatement(sql);

        rs = pstm.executeQuery();

        while (rs.next()) {

            Curso curso = new Curso();
            // Populando o objeto curso
            curso.setId_curso(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome"));
            curso.setCargaHoraria(rs.getInt("cargaHoraria"));

            cursos.add(curso);
        }

        rs.close();
        pstm.close();
        conn.close();

        return cursos;

    }
}
