package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CriaTabela", urlPatterns = {"/CriaTabela"})
public class CriaTabela extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527//lppo-2017-1";  
            Connection conexao = DriverManager.getConnection(url, "usuario", "usuario");
            System.out.println("Conexao aberta com sucesso");
            
            /*
            String sql = "CREATE TABLE reclamacao ("
                    + "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "nome VARCHAR(50) NOT NULL, email VARCHAR(90) NOT NULL,"
                    + "descricao VARCHAR(90) NOT NULL, status INTEGER NOT NULL DEFAULT 0 )";
            */
            
            String sql = "CREATE TABLE visitante("
                    + "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "nome VARCHAR(50) NOT NULL,"
                    + "idade INTEGER NOT NULL,"
                    + "entrada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "saida TIMESTAMP);";
            
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println("Erro ao criar a tabela");
        }
            response.sendRedirect("novo-registro.html");
    }
}