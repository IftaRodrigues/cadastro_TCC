<%-- 
    Document   : listaTCC
    Created on : 21/08/2018, 15:22:13
    Author     : 02904700137
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Projeto"%>
<%@page import="dao.ProjetoDao"%>
<%@page import="services.ProjetoService"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de TCC</title> 
    </head>
    <body>
        <div id="container">
            
            <div class="div_texbox">
                <p><a href="incluirProjeto.jsp">Novo Projeto</a></p> 
            </div>
            <div class="div_texbox">
                <p><a href="listaBanca.jsp">Lista das Bancas</a></p> 
            </div>
            <div id="top">
                <center><h1>Lista de Projetos</h1></center>
            </div> 

            <div id="leftSide">

                <%
                    List<Projeto> projetos = new ArrayList();

                    ProjetoService servicePro = new ProjetoService();

                    projetos = servicePro.ListaProjeto();
                %>


                <table border="1" width="80%">
                    <thead>
                        <tr>
                            <th>Título</th>
                            <th>Aluno</th>
                            <th>Curso</th>
                            <th>Tipo de Projeto</th>
                            <th>Area de Pesquisa</th>
                            <th>Orientador</th>
                            <th>Ação</th>
                        </tr>
                    </thead>

                    <%  for (Projeto pj : projetos) {%>
                    <tbody> 
                        <tr>
                            <td><% out.print(pj.getTitulo()); %></td>
                            <td><% out.print(pj.getAluno().getNome());%></td>
                            <td><% out.print(pj.getCurso().getNome());%></td>
                            <td><% out.print(pj.getTipoProjeto().getDescricao()); %></td>                            
                            <td><% out.print(pj.getAreaPesquisa().getNome());%></td>
                            <td><% out.print(pj.getOrientador().getNome());%></td>

                            <td class="op">
                                <a href="/IftaProjeto/editarProjeto.jsp?id_projeto=<%out.print(pj.getId_projeto());%>">Editar</a>
                                <a href="/IftaProjeto/ExcluirProjeto?id_projeto=<%out.print(pj.getId_projeto());%>">Excluir</a>
                            </td>


                        </tr>

                        <% };%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
