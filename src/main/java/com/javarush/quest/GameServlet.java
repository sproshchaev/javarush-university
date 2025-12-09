package com.javarush.quest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    // Переопределение doPost для обработки POST-запросов
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Получить сессию пользователя
        HttpSession session = req.getSession();

        // Получить параметр answer из формы
        String answer = req.getParameter("answer");

        // Проверяем выбран ли ответ?
        if (answer == null) {
            // Если не выбран - сообщение об ошибке
            req.setAttribute("message", "Please enter your answer.");
            // Вернуть на страницу с вопросом
            req.getRequestDispatcher("/question.jsp").forward(req, resp);
        }

        // Обрабатываем ответ
        if ("accept".equals(answer)) {
            session.setAttribute("result", "Вы приняли вызов!");
        } else {
            session.setAttribute("result", "Вы отказались!");
        }

        // Перенаправить на страницу результата
        resp.sendRedirect(req.getContextPath() + "/result.jsp");

    }

}
