package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistraSaida", urlPatterns = {"/saida.html"})
public class RegistraSaida extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Visitante visitante = new Visitante();
            //visitante.setId(Integer.parseInt(request.getParameter("id")));

            String sql = null;

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527//lppo-2017-1", "usuario", "usuario");

                Statement operacao = conexao.createStatement();
                sql = "UPDATE visitante SET saida=CURRENT_TIMESTAMP WHERE id="
                         +visitante.getId();
                operacao.executeUpdate(sql);
                System.out.println(sql);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistraSaida.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RegistraSaida.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.sendRedirect("lista.html");

    }
    /*
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        }
    */
}
