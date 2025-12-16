package com.javarush.quest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerName = request.getParameter("playerName");
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Искатель приключений";
        }

        HttpSession session = request.getSession();
        session.setAttribute("playerName", playerName);
        session.setAttribute("gamesPlayed", 0);

        response.sendRedirect(request.getContextPath() + "/game?step=start");

    }

}
