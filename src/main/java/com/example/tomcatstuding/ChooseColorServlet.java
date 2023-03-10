package com.example.tomcatstuding;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/chooseTextColor")
public class ChooseColorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        coloringText(req,resp);
    }

    public void coloringText(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String text = httpServletRequest.getParameter("justText");
        String color = httpServletRequest.getParameter("color");

        httpServletResponse.setContentType("text/html; charset=UTF-8");
        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.println("<html>");
            printWriter.println("<head><title>qwe</title></head>");
            printWriter.println("<body>");
            printWriter.println("<p style=color:" + color + ">" + text + "</p>");
            printWriter.println("</body>");
            printWriter.println("</html>");
        }
    }
}
