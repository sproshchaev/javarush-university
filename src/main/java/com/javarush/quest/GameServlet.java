package com.javarush.quest;

import com.javarush.quest.model.QuestStep;
import com.javarush.quest.service.QuestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private final QuestService questService = new QuestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stepId = req.getParameter("step");
        if (stepId == null || stepId.isEmpty()) {
            stepId = "start";
        }

        QuestStep step = questService.getStep(stepId);
        if (step == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Шаг не найден!");
        }

        req.setAttribute("step", step);
        req.getRequestDispatcher("/game.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Получаем параметры
        String answer = req.getParameter("answer");
        String currentStepId = req.getParameter("currentStep");

        // Валидация
        if (answer == null || currentStepId == null) {
            resp.sendRedirect(req.getContextPath() + "/game?step=start");
            return;
        }

        // Получить текущий шаг
        QuestStep currentStep = questService.getStep(currentStepId);
        if (currentStep == null) {
            resp.sendRedirect(req.getContextPath() + "/game?step=start");
            return;
        }

        // Cлед шаг
        String nextStepId;
        if ("1".equals(answer)) {
            nextStepId = currentStep.getNextStepId1();
        } else {
            nextStepId = currentStep.getNextStepId2();
        }

        // Обновляем статистику
        if (questService.isFinalStep(nextStepId)) {
            Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
            if (gamesPlayed == null) {
                gamesPlayed = 0;
            }
            session.setAttribute("gamesPlayed", gamesPlayed + 1);
        }

        // на след шаг
        resp.sendRedirect(req.getContextPath() + "/game?step=" + nextStepId);

    }

}
