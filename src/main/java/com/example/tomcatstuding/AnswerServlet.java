package com.example.tomcatstuding;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(value = "/answer")
public class AnswerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        validAnswer(req, resp);
    }

    public void validAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] cls = request.getParameterValues("c1");

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean a = false;
        for(String c1:cls) {
            if(c1.equals("2")) {
                writer.print("Верно, тип boolean прнимает 2 значения");
                break;
            } else {
                writer.println("Неверно, попробуй еще раз!");
            }
        }
    }
}
