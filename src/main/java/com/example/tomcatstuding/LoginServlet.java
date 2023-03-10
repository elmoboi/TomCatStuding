package com.example.tomcatstuding;

import com.example.tomcatstuding.SQLUtils.SQLRequests;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            userLogin(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void userLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException, ServletException, IOException {
        PrintWriter printWriter = httpServletResponse.getWriter();
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        if(SQLRequests.getUsers().stream().filter(users -> login.equals(users.getLogin())).findAny().filter(users -> password.equals(users.getPassword())).isPresent()) {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/login.html");
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        } else {
            printWriter.println("Invalid password or login!");
        }
    }
}
