/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.AlunoDao;
import dao.AreaPesquisaDao;
import dao.CursoDao;
import dao.ProfessorDao;
import dao.ProjetoDao;
import dao.TipoProjetoDao;
import dominio.Aluno;
import dominio.AreaPesquisa;
import dominio.Curso;
import dominio.Professor;
import dominio.Projeto;
import dominio.TipoProjeto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ProjetoService;

/**
 *
 * @author Idê Moraes
 */
public class IncluirProjeto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            ProjetoDao projetoDao = new ProjetoDao();
            Projeto projeto = new Projeto();
            AlunoDao alunoDao = new AlunoDao();
            Aluno aluno = new Aluno();
            CursoDao cursoDao = new CursoDao();
            Curso curso = new Curso();
            TipoProjetoDao tipoProjetoDao = new TipoProjetoDao();
            TipoProjeto tipoProjeto = new TipoProjeto();
            AreaPesquisaDao areaPesquisaDao = new AreaPesquisaDao();
            AreaPesquisa areaPesquisa = new AreaPesquisa();
            ProfessorDao orientadorDao = new ProfessorDao();
            Professor orientador = new Professor();
            ProjetoService proService = new ProjetoService();
            
            projeto.setTitulo(request.getParameter("titulo"));

                    int codAluno = 1, codCurso = 1, codTipoProjeto = 1,
                            codAreaPesquisa = 1, codOrientador = 1;

                    aluno = alunoDao.getAluno(codAluno);
                    curso = cursoDao.getCurso(codCurso);
                    tipoProjeto = tipoProjetoDao.getTipoProjeto(codTipoProjeto);
                    areaPesquisa = areaPesquisaDao.getAreaPesquisa(codAreaPesquisa);
                    orientador = orientadorDao.getProfessor(codOrientador);

                    projeto.setAluno(aluno);
                    projeto.setCurso(curso);
                    projeto.setTipoProjeto(tipoProjeto);
                    projeto.setAreaPesquisa(areaPesquisa);
                    projeto.setOrientador(orientador);
                    
                    try {
                      proService.adcionaProjeto(projeto);
                      response.sendRedirect("listaProjeto.jsp");
                      
                    } catch (Exception e) {
                        out.println("<h2>Erro "+projeto.getTitulo()+"</h2>");
                    }
            
            
        }catch(SQLException e){
           Logger.getLogger(IncluirProjeto.class.getName()).log(Level.SEVERE, null, e); 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
