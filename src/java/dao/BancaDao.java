package dao;

import db.ConnectionFactory;
import dominio.Aluno;
import dominio.AreaPesquisa;
import dominio.Avaliacao;
import dominio.BancaAvaliadora;
import dominio.Curso;
import dominio.Professor;
import dominio.Projeto;
import dominio.Situacao;
import dominio.TipoProjeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancaDao implements IBancaDao {

    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public void adiciona(BancaAvaliadora banca) throws SQLException {

        conn = cf.getConnection();

        String sql;

        sql = "Insert into banca_avaliadora(projeto,avaliador1,avaliador2,"
                + "situacao)"
                + "VALUES (?,?,?,?)";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, banca.getProjeto().getId_projeto());
        pstmt.setInt(2, banca.getAvaliador1().getId_professor());
        pstmt.setInt(3, banca.getAvaliador2().getId_professor());
        pstmt.setInt(4, banca.getSituacao().getId_situacao());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();

    }

    @Override
    public void altera(BancaAvaliadora banca) throws SQLException {

        conn = cf.getConnection();

        String sql;

        sql = "UPDATE banca_avaliadora SET projeto=?,"
                + "avaliador1=?, avaliador2=?, situacao=?"
                + "WHERE id_banca=?";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, banca.getProjeto().getId_projeto());
        pstmt.setInt(2, banca.getAvaliador1().getId_professor());
        pstmt.setInt(3, banca.getAvaliador2().getId_professor());
        pstmt.setInt(4, banca.getSituacao().getId_situacao());
        pstmt.setInt(5, banca.getId_banca());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    @Override
    public void remove(BancaAvaliadora banca) throws SQLException {

        conn = cf.getConnection();

        pstmt = conn.prepareStatement(
                "delete from banca_avaliadora where id_banca=?");

        pstmt.setInt(1, banca.getId_banca());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public BancaAvaliadora getBancaAvaliadora(Integer id_banca) throws SQLException {

        conn = cf.getConnection();

        String sql = "select *,\n"
                + "  p1.id_professor as id_avaliador1, p1.nome as avaliador1, p1.cpf as cpf_avaliador1, a.rg as rg_avaliador1, p1.email as email_avaliador1, p1.registro as registro_avaliador1, p1.salario as salario_avaliador1,\n"
                + "  p2.id_professor as id_avaliador2, p2.nome as avaliador2, p2.cpf as cpf_avaliador2, a.rg as rg_avaliador2, p2.email as email_avaliador2, p2.registro as registro_avaliador2, p2.salario as salario_avaliador2,\n"
                + "  s.id_situacao, s.descricao as situacao,\n"
                + "  pj.id_projeto, pj.titulo,\n"
                + "  a.id_aluno, a.nome as aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula, \n"
                + "  c.id_curso, c.nome as curso, c.cargaHoraria, \n"
                + "  t.id_tipoProjeto, t.descricao as tipoProjeto, \n"
                + "  r.id_areaPesquisa, r.nome as areaPesquisa, \n"
                + "  o.id_professor as id_orientador, o.nome as orientador, o.cpf as cpf_orientador, a.rg as rg_orientador, o.email as email_orientador, o.registro as registro_orientador, o.salario as salario_orientador\n"
                + "from banca_avaliadora b\n"
                + "inner join professor p1 on (p1.id_professor= b.avaliador1)\n"
                + "inner join professor p2 on (p2.id_professor= b.avaliador2)\n"
                + "inner join situacao s on (s.id_situacao= b.situacao)\n"
                + "inner join projeto pj on (pj.id_projeto= b.projeto) \n"
                + "inner join aluno a on (a.id_aluno = pj.aluno) \n"
                + "inner join curso c on (c.id_curso=pj.curso)\n"
                + "inner join tipoProjeto t on (t.id_tipoProjeto=pj.tipoProjeto)\n"
                + "inner join areaPesquisa r on (r.id_areaPesquisa=pj.areaPesquisa)\n"
                + "inner join professor o on (o.id_professor=pj.orientador)"
                + "where id_banca=?";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id_banca);

        rs = pstmt.executeQuery();

        BancaAvaliadora banca = new BancaAvaliadora();
        Professor avaliador1 = new Professor();
        Professor avaliador2 = new Professor();
        Situacao situacao = new Situacao();
        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tipoProjeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();

        while (rs.next()) {

            avaliador1.setId_professor(rs.getInt("id_avaliador1"));
            avaliador1.setNome(rs.getString("avaliador1"));
            avaliador1.setCpf(rs.getString("cpf_avaliador1"));
            avaliador1.setRg(rs.getString("rg_avaliador1"));
            avaliador1.setEmail(rs.getString("email_avaliador1"));
            avaliador1.setRegistro(rs.getInt("registro_avaliador1"));
            avaliador1.setSalario(rs.getFloat("salario_avaliador1"));

            avaliador2.setId_professor(rs.getInt("id_avaliador2"));
            avaliador2.setNome(rs.getString("avaliador2"));
            avaliador2.setCpf(rs.getString("cpf_avaliador2"));
            avaliador2.setRg(rs.getString("rg_avaliador2"));
            avaliador2.setEmail(rs.getString("email_avaliador2"));
            avaliador2.setRegistro(rs.getInt("registro_avaliador2"));
            avaliador2.setSalario(rs.getFloat("salario_avaliador2"));

            situacao.setId_situacao(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("situacao"));

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

            orientador.setId_professor(rs.getInt("id_orientador"));
            orientador.setNome(rs.getString("orientador"));
            orientador.setCpf(rs.getString("cpf_orientador"));
            orientador.setRg(rs.getString("rg_orientador"));
            orientador.setEmail("email_orientador");
            orientador.setRegistro(rs.getInt("registro_orientador"));
            orientador.setSalario(rs.getFloat("salario_orientador"));

            projeto.setId_projeto(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tipoProjeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);

            banca.setId_banca(rs.getInt("id_banca"));
            banca.setProjeto(projeto);
            banca.setAvaliador1(avaliador1);
            banca.setAvaliador2(avaliador2);
            banca.setSituacao(situacao);

        }
        rs.close();
        pstmt.close();
        conn.close();

        return banca;
    }

    @Override
    public List<BancaAvaliadora> listaBancaAvaliadora() throws SQLException {
        List<BancaAvaliadora> bancas = new ArrayList<>();

        conn = cf.getConnection();

        String sql = "select *,\n"
                + "  p1.id_professor as id_avaliador1, p1.nome as avaliador1, p1.cpf as cpf_avaliador1, a.rg as rg_avaliador1, p1.email as email_avaliador1, p1.registro as registro_avaliador1, p1.salario as salario_avaliador1,\n"
                + "  p2.id_professor as id_avaliador2, p2.nome as avaliador2, p2.cpf as cpf_avaliador2, a.rg as rg_avaliador2, p2.email as email_avaliador2, p2.registro as registro_avaliador2, p2.salario as salario_avaliador2,\n"
                + "  s.id_situacao, s.descricao as situacao,\n"
                + "  pj.id_projeto, pj.titulo,\n"
                + "  a.id_aluno, a.nome as aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula, \n"
                + "  c.id_curso, c.nome as curso, c.cargaHoraria, \n"
                + "  t.id_tipoProjeto, t.descricao as tipoProjeto, \n"
                + "  r.id_areaPesquisa, r.nome as areaPesquisa, \n"
                + "  o.id_professor as id_orientador, o.nome as orientador, o.cpf as cpf_orientador, a.rg as rg_orientador, o.email as email_orientador, o.registro as registro_orientador, o.salario as salario_orientador\n"
                + "from banca_avaliadora b\n"
                + "inner join professor p1 on (p1.id_professor= b.avaliador1)\n"
                + "inner join professor p2 on (p2.id_professor= b.avaliador2)\n"
                + "inner join situacao s on (s.id_situacao= b.situacao)\n"
                + "inner join projeto pj on (pj.id_projeto= b.projeto) \n"
                + "inner join aluno a on (a.id_aluno = pj.aluno) \n"
                + "inner join curso c on (c.id_curso=pj.curso)\n"
                + "inner join tipoProjeto t on (t.id_tipoProjeto=pj.tipoProjeto)\n"
                + "inner join areaPesquisa r on (r.id_areaPesquisa=pj.areaPesquisa)\n"
                + "inner join professor o on (o.id_professor=pj.orientador)";

        pstmt = conn.prepareStatement(sql);

        rs = pstmt.executeQuery();

        while (rs.next()) {

            BancaAvaliadora banca = new BancaAvaliadora();
            Professor avaliador1 = new Professor();
            Professor avaliador2 = new Professor();
            Situacao situacao = new Situacao();
            Projeto projeto = new Projeto();
            Aluno aluno = new Aluno();
            Curso curso = new Curso();
            TipoProjeto tipoProjeto = new TipoProjeto();
            AreaPesquisa areaPesquisa = new AreaPesquisa();
            Professor orientador = new Professor();

            avaliador1.setId_professor(rs.getInt("id_avaliador1"));
            avaliador1.setNome(rs.getString("avaliador1"));
            avaliador1.setCpf(rs.getString("cpf_avaliador1"));
            avaliador1.setRg(rs.getString("rg_avaliador1"));
            avaliador1.setEmail(rs.getString("email_avaliador1"));
            avaliador1.setRegistro(rs.getInt("registro_avaliador1"));
            avaliador1.setSalario(rs.getFloat("salario_avaliador1"));

            avaliador2.setId_professor(rs.getInt("id_avaliador2"));
            avaliador2.setNome(rs.getString("avaliador2"));
            avaliador2.setCpf(rs.getString("cpf_avaliador2"));
            avaliador2.setRg(rs.getString("rg_avaliador2"));
            avaliador2.setEmail(rs.getString("email_avaliador2"));
            avaliador2.setRegistro(rs.getInt("registro_avaliador2"));
            avaliador2.setSalario(rs.getFloat("salario_avaliador2"));

            situacao.setId_situacao(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("situacao"));

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

            orientador.setId_professor(rs.getInt("id_orientador"));
            orientador.setNome(rs.getString("orientador"));
            orientador.setCpf(rs.getString("cpf_orientador"));
            orientador.setRg(rs.getString("rg_orientador"));
            orientador.setEmail("email_orientador");
            orientador.setRegistro(rs.getInt("registro_orientador"));
            orientador.setSalario(rs.getFloat("salario_orientador"));

            projeto.setId_projeto(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tipoProjeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);

            banca.setId_banca(rs.getInt("id_banca"));
            banca.setProjeto(projeto);
            banca.setAvaliador1(avaliador1);
            banca.setAvaliador2(avaliador2);
            banca.setSituacao(situacao);

            bancas.add(banca);

        }
        rs.close();
        pstmt.close();
        conn.close();

        return bancas;
    }

}
