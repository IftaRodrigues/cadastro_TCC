
<%@page import="services.BancaService"%>
<%@page import="dominio.BancaAvaliadora"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Bancas</title> 
    </head>
    <body>
        <div id="container">
            
            <div class="div_texbox">
                <p><a href="incluirBanca.jsp">Nova Banca</a></p> 
            </div>
            <div class="div_texbox">
                <p><a href="listaProjeto.jsp">Lista dos Projetos</a></p> 
            </div>

            <div id="top">
                <center><h1>Lista de Bancas</h1></center>
            </div> 

            <div id="leftSide">

                <%
                    List<BancaAvaliadora> bancas = new ArrayList();

                    BancaService servicebanca = new BancaService();

                    bancas = servicebanca.ListaBanca();
                %>


                <table border="1" width="80%">
                    <thead>
                        <tr>
                            <th>Título</th>
                            <th>Avaliador 1</th>
                            <th>Avaliador 2</th>
                            <th>Situação</th>
                            <th>Aluno</th>
                            <th>Orientador</th>
                            <th>Ação</th>
                        </tr>
                    </thead>

                    <%  for (BancaAvaliadora bc : bancas) {%>
                    <tbody> 
                        <tr>
                            
                            <td><% out.print(bc.getProjeto().getTitulo()); %></td>
                            <td><% out.print(bc.getAvaliador1().getNome()); %></td>
                            <td><% out.print(bc.getAvaliador2().getNome()); %></td>
                            <td><% out.print(bc.getSituacao().getDescricao()); %></td>
                            <td><% out.print(bc.getProjeto().getAluno().getNome());%></td>
                            <td><% out.print(bc.getProjeto().getOrientador().getNome());%></td>

                            <td class="op">
                                <a href="/IftaProjeto/editarBanca.jsp?id_banca=<%out.print(bc.getId_banca());%>">Editar</a>
                                <a href="/IftaProjeto/ExcluirBanca?id_banca=<%out.print(bc.getId_banca());%>">Excluir</a>
                            </td>


                        </tr>

                        <% };%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
