
<%@page import="java.util.List"%>
<%@page import="dominio.Aluno"%>
<%@page import="dao.AlunoDao"%>
<%@page import="dominio.Curso"%>
<%@page import="dao.CursoDao"%>
<%@page import="dominio.TipoProjeto"%>
<%@page import="dao.TipoProjetoDao"%>
<%@page import="dominio.AreaPesquisa"%>
<%@page import="dao.AreaPesquisaDao"%>
<%@page import="dominio.Professor"%>
<%@page import="dao.ProfessorDao"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Incluir Projeto</title>
    </head>
    <body>
        <form name="FormIncluir" method="post" action="IncluirProjeto" >
            <fieldset>
                <legend>Dados do Projeto</legend>


                <div class="div_texbox">
                    <label for="titulo"> Titulo: </label>
                    <input name="titulo" type="text"/>
                </div>


                <div class="div_texbox">
                    <label for="aluno"> Aluno: </label>
                    <%

                        AlunoDao alunoDao = new AlunoDao();
                        List<Aluno> alunos = alunoDao.listaAlunos();

                        out.print("<select name=\"opt_aluno\">");
                        for (Aluno a : alunos) {
                            out.print("<option value=" + a.getId_aluno() + ">"
                                    + a.getNome() + "</option>");

                        }
                        out.print("</select>");

                    %>
                </div>

                <div class="div_texbox">
                    <label for="curso"> Curso: </label>
                    <%                        
                        CursoDao cursoDao = new CursoDao();
                        List<Curso> cursos = cursoDao.listaCurso();

                        out.print("<select name=\"opt_curso\">");
                        for (Curso c : cursos) {
                            out.print("<option value=" + c.getId_curso() + ">"
                                    + c.getNome() + "</option>");

                        }
                        out.print("</select>");

                    %>
                </div>


                <div class="div_texbox">
                    <label for="tipoProjeto"> Tipo de Projeto: </label>
                    <%                        
                        TipoProjetoDao tipoProjetoDao = new TipoProjetoDao();
                        List<TipoProjeto> tipospro = tipoProjetoDao.listaTipoProjeto();

                        out.print("<select name=\"opt_tipoProjeto\">");
                        for (TipoProjeto t : tipospro) {
                            out.print("<option value=" + t.getId_tipoProjeto() + ">"
                                    + t.getDescricao() + "</option>");

                        }
                        out.print("</select>");

                    %>
                </div>


                <div class="div_texbox">
                    <label for="areaDePesquisa"> Area de Pesquisa: </label>
                    <%                        
                        AreaPesquisaDao areaPesquisaDao = new AreaPesquisaDao();
                        List<AreaPesquisa> areaspesquisa = areaPesquisaDao.listaAreaPesquisa();

                        out.print("<select name=\"opt_areaPesquisa\">");
                        for (AreaPesquisa ap : areaspesquisa) {
                            out.print("<option value=" + ap.getId_areaPesquisa() + ">"
                                    + ap.getNome() + "</option>");

                        }
                        out.print("</select>");

                    %>
                </div>



                <div class="div_texbox">
                    <label for="orientador"> Orientador: </label>
                    <%                        
                        ProfessorDao orientadorDao = new ProfessorDao();
                        List<Professor> professores = orientadorDao.listaProfessores();

                        out.print("<select name=\"opt_orientador\">");
                        for (Professor o : professores) {
                            out.print("<option value=" + o.getId_professor() + ">"
                                    + o.getNome() + "</option>");

                        }
                        out.print("</select>");

                    %>                
                </div>

            </fieldset>
            <div class="botao">
                <br>
                <br>
                <button type="submit">Cadastrar</button>
            </div>
        </form>
                
    </body>
</html>
