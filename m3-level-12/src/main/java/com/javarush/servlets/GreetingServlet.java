package com.javarush.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/demo/gretting", loadOnStartup = 1, initParams = @WebInitParam(name = "greeting", value = "Привет"))
public class GreetingServlet extends HttpServlet {

    private String greeting;

    @Override
    public void init() {
        greeting = getServletConfig().getInitParameter("greeting");
        getServletContext().log("GreetingServlet.init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeGreeting(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        writeGreeting(request, response);
    }

    private void writeGreeting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        response.setContentType("text/plain;charset=UTF-8");
        if (name == null || name.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Укажите параметр name");
            return;
        }

        PrintWriter out = response.getWriter();
        out.println(request.getMethod() + ": " + greeting + ", " + name + "!");
        Object originalUri = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        if (originalUri != null) {
            out.println("Исходный путь: " + originalUri);
        }
    }


}
