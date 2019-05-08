package dao;

import db.ConnectionFactory;
import dominio.Aluno;
import dominio.AreaPesquisa;
import dominio.BancaAvaliadora;
import dominio.Curso;
import dominio.Professor;
import dominio.Projeto;
import dominio.TipoProjeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDao implements IProjetoDao {

    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public void adiciona(Projeto projeto) throws SQLException {

        conn = cf.getConnection();

        //Inserinco Projeto
        String sql = "insert into projeto(titulo,"
                + "aluno, curso,tipoProjeto,"
                + "areaPesquisa,orientador)"
                + "VALUES (?,?,?,?,?,?)";

        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, projeto.getTitulo());
        pstmt.setInt(2, projeto.getAluno().getId_aluno());
        pstmt.setInt(3, projeto.getCurso().getId_curso());
        pstmt.setInt(4, projeto.getTipoProjeto().getId_tipoProjeto());
        pstmt.setInt(5, projeto.getAreaPesquisa().getId_areaPesquisa());
        pstmt.setInt(6, projeto.getOrientador().getId_professor());

        pstmt.executeUpdate();

        //conn.commit();
        pstmt.close();
        conn.close();

    }

    @Override
    public void altera(Projeto projeto) throws SQLException {

        conn = cf.getConnection();

        conn.setAutoCommit(false);

        try {

            String sql = "UPDATE projeto SET titulo=?,"
                    + "aluno=?, curso=?, tipoProjeto=?,"
                    + "areaPesquisa=?, orientador=?"
                    + "WHERE id_projeto=?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, projeto.getTitulo());
            pstmt.setInt(2, projeto.getAluno().getId_aluno());
            pstmt.setInt(3, projeto.getCurso().getId_curso());
            pstmt.setInt(4, projeto.getTipoProjeto().getId_tipoProjeto());
            pstmt.setInt(5, projeto.getAreaPesquisa().getId_areaPesquisa());
            pstmt.setInt(6, projeto.getOrientador().getId_professor());
            pstmt.setInt(7, projeto.getId_projeto());

            pstmt.executeUpdate();

            conn.commit();

            pstmt.close();
            conn.close();
        } catch (SQLException ex) {

            conn.rollback();
            pstmt.close();
            conn.close();

        }

    }

    @Override
    public void remove(Projeto projeto) throws SQLException {

        conn = cf.getConnection();

        pstmt = conn.prepareStatement(
                "delete from projeto where id_projeto=?");

        pstmt.setInt(1, projeto.getId_projeto());

        pstmt.execute();
        pstmt.close();
        conn.close();

    }

    @Override
    public Projeto getProjeto(Integer id_projeto) throws SQLException {

        conn = cf.getConnection();

        String sql = "select pj.id_projeto, pj.titulo, a.id_aluno, a.nome as aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula, \n"
                + "  c.id_curso, c.nome as curso, c.cargaHoraria, t.id_tipoProjeto, t.descricao as tipoProjeto, r.id_areaPesquisa, r.nome as areaPesquisa, \n"
                + "  o.id_professor, o.nome as orientador, o.cpf as cpf_orientador, a.rg as rg_orientador, o.email as email_orientador, o.registro, o.salario\n"
                + "from projeto pj \n"
                + "inner join aluno a on (a.id_aluno = pj.aluno) \n"
                + "inner join curso c on (c.id_curso=pj.curso)\n"
                + "inner join tipoProjeto t on (t.id_tipoProjeto=pj.tipoProjeto)\n"
                + "inner join areaPesquisa r on (r.id_areaPesquisa=pj.areaPesquisa)\n"
                + "inner join professor o on (o.id_professor=pj.orientador)\n"
                + "where id_projeto=?";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id_projeto);

        rs = pstmt.executeQuery();

        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tipoProjeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();

        while (rs.next()) {

            aluno.setId_aluno(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail("email_aluno");
            aluno.setMatricula(rs.getInt("matricula"));

            curso.setId_curso(rs.getInt("id_curso"));
            curso.setNome(rs.getString("curso"));
            curso.setCargaHoraria(rs.getInt("cargaHoraria"));

            tipoProjeto.setId_tipoProjeto(rs.getInt("id_tipoProjeto"));
            tipoProjeto.setDescricao(rs.getString("tipoProjeto"));

            areaPesquisa.setId_areaPesquisa(rs.getInt("id_areaPesquisa"));
            areaPesquisa.setNome(rs.getString("areaPesquisa"));

            orientador.setId_professor(rs.getInt("id_professor"));
            orientador.setNome(rs.getString("orientador"));
            orientador.setCpf(rs.getString("cpf_orientador"));
            orientador.setRg(rs.getString("rg_orientador"));
            orientador.setEmail("email_orientador");
            orientador.setRegistro(rs.getInt("registro"));
            orientador.setSalario(rs.getFloat("salario"));

            projeto.setId_projeto(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tipoProjeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return projeto;
    }

    @Override
    public List<Projeto> listaProjetos() throws SQLException {
        conn = cf.getConnection();

        String sql = "select pj.id_projeto, pj.titulo, a.id_aluno, a.nome as aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula, \n"
                + "  c.id_curso, c.nome as curso, c.cargaHoraria, t.id_tipoProjeto, t.descricao as tipoProjeto, r.id_areaPesquisa, r.nome as areaPesquisa, \n"
                + "  o.id_professor, o.nome as orientador, o.cpf as cpf_orientador, a.rg as rg_orientador, o.email as email_orientador, o.registro, o.salario\n"
                + "from projeto pj \n"
                + "inner join aluno a on (a.id_aluno = pj.aluno) \n"
                + "inner join curso c on (c.id_curso=pj.curso)\n"
                + "inner join tipoProjeto t on (t.id_tipoProjeto=pj.tipoProjeto)\n"
                + "inner join areaPesquisa r on (r.id_areaPesquisa=pj.areaPesquisa)\n"
                + "inner join professor o on (o.id_professor=pj.orientador)";

        pstmt = conn.prepareStatement(sql);

        //necessário
        rs = pstmt.executeQuery();

        List<Projeto> projetos = new ArrayList<>();

        while (rs.next()) {
            Projeto projeto = new Projeto();
            Aluno aluno = new Aluno();
            Curso curso = new Curso();
            TipoProjeto tipoProjeto = new TipoProjeto();
            AreaPesquisa areaPesquisa = new AreaPesquisa();
            Professor orientador = new Professor();

            aluno.setId_aluno(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail("email_aluno");
            aluno.setMatricula(rs.getInt("matricula"));

            curso.setId_curso(rs.getInt("id_curso"));
            curso.setNome(rs.getString("curso"));
            curso.setCargaHoraria(rs.getInt("cargaHoraria"));

            tipoProjeto.setId_tipoProjeto(rs.getInt("id_tipoProjeto"));
            tipoProjeto.setDescricao(rs.getString("tipoProjeto"));

            areaPesquisa.setId_areaPesquisa(rs.getInt("id_areaPesquisa"));
            areaPesquisa.setNome(rs.getString("areaPesquisa"));

            orientador.setId_professor(rs.getInt("id_professor"));
            orientador.setNome(rs.getString("orientador"));
            orientador.setCpf(rs.getString("cpf_orientador"));
            orientador.setRg(rs.getString("rg_orientador"));
            orientador.setEmail("email_orientador");
            orientador.setRegistro(rs.getInt("registro"));
            orientador.setSalario(rs.getFloat("salario"));

            projeto.setId_projeto(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tipoProjeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);

            projetos.add(projeto);

        }
        rs.close();
        pstmt.close();
        conn.close();

        return projetos;
    }

}
