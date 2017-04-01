package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NovoRegistro", urlPatterns = {"/novo-registro.html"})
public class NovoRegistro extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("WEB-INF/RegistroVisitante.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        int idade = Integer.parseInt(request.getParameter("idade"));
        
        Logger.getLogger(NovoRegistro.class.getName()).log(Level.INFO, "POST: " +nome+" "+idade);
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527//lppo-2017-1";  
            Connection conexao = DriverManager.getConnection(url, "usuario", "usuario");
            System.out.println("Conexao aberta com sucesso");
            String sql = "INSERT INTO visitante (nome, idade) VALUES ('"
                    +nome+ "',"
                    +idade+ ")";
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate(sql);             
        } catch (Exception e) {
            Logger.getLogger(NovoRegistro.class.getName()).log(Level.SEVERE, "Erro ao inserir o registro!" +e);            
            
        }
        response.sendRedirect("lista.html");
    }
}
