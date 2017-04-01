package controller;

import controller.Visitante;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ListaVisitantes", urlPatterns = {"/lista.html"})
public class ListaVisitantes extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        List<Visitante> visitantes = new ArrayList<>();
        
        try {
            //Pegar os dados do banco
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1","usuario","usuario");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT id,nome,idade,entrada,saida FROM visitante");
            while(resultado.next()) {
                Visitante visitante = new Visitante();
                visitante.setId(resultado.getLong("id"));
                visitante.setNome(resultado.getString("nome"));
                visitante.setIdade(resultado.getInt("idade"));
                visitante.setEntrada(resultado.getDate("entrada"));
                visitante.setSaida(resultado.getDate("saida"));
                visitantes.add(visitante);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaVisitantes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaVisitantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("visitantes", visitantes);
        request.getRequestDispatcher("WEB-INF/ListagemVisitante.jsp").forward(request,response);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    
}
