<%@page import="dao.SituacaoDao"%>
<%@page import="dominio.Situacao"%>
<%@page import="dominio.Projeto"%>
<%@page import="dao.ProjetoDao"%>
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
        <title>Incluir Banca</title>
    </head>
    <body>
        <form name="FormIncluir" method="post" action="IncluirBanca" >
            <fieldset>
                <legend>Dados da Banca</legend>


                <div class="div_texbox">
                    <label for="projeto"> Projeto: </label>
                    <%

                        ProjetoDao projetoDao = new ProjetoDao();
                        List<Projeto> projetos = projetoDao.listaProjetos();

                        out.print("<select name=\"opt_projeto\">");
                        for (Projeto p : projetos) {
                            out.print("<option value=" + p.getId_projeto() + ">"
                                    + p.getTitulo() + "</option>");

                        }
                        out.print("</select>");

                    %>
                </div>

                <div class="div_texbox">
                    <label for="avaliador1"> Avaliador 1: </label>
                    <%                        
                        ProfessorDao avaliador1Dao = new ProfessorDao();
                        List<Professor> avali1s = avaliador1Dao.listaProfessores();

                        out.print("<select name=\"opt_avaliador1\">");
                        for (Professor av1 : avali1s) {
                            out.print("<option value=" + av1.getId_professor() + ">"
                                    + av1.getNome() + "</option>");

                        }
                        out.print("</select>");

                    %>
                </div>
                <div class="div_texbox">
                    <label for="avaliador2"> Avaliador 2: </label>
                    <%                        
                        ProfessorDao avaliador2Dao = new ProfessorDao();
                        List<Professor> avali2s = avaliador2Dao.listaProfessores();

                        out.print("<select name=\"opt_avaliador2\">");
                        for (Professor av2 : avali2s) {
                            out.print("<option value=" + av2.getId_professor() + ">"
                                    + av2.getNome() + "</option>");

                        }
                        out.print("</select>");

                    %>
                </div>


                <%                                
                           SituacaoDao situacaoDao = new SituacaoDao();
                           List<Situacao> situacoes = situacaoDao.listaSituacao();

                           out.print("<input type=\"hidden\" name=\"opt_situacao\" value=\"2\" />");


                %>


            </fieldset>
            <div class="botao">
                <br>
                <br>
                <button type="submit">Cadastrar</button>
            </div>
        </form>
                
    </body>
</html>
