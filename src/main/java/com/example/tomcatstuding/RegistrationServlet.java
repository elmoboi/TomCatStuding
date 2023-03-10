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
import java.sql.*;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            userRegistration(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void userRegistration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, SQLException, ServletException {
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        PrintWriter printWriter = httpServletResponse.getWriter();

        if(userValidationSecondPassword(httpServletRequest,httpServletResponse)) {
            SQLRequests.createUser(login,password);
            printWriter.println("User with login " + login + " registered!");
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/user.html");
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        }
    }

    public Boolean userValidationSecondPassword(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
        String password = httpServletRequest.getParameter("password");
        String passwordValid = httpServletRequest.getParameter("passwordValid");
        PrintWriter printWriter = httpServletResponse.getWriter();

        if(!password.equals(passwordValid)) {
            printWriter.println("Incorrect password validation!");
            return false;
        }
        return true;
    }
}
