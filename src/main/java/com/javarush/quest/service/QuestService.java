package com.javarush.quest.service;

import com.javarush.quest.model.QuestStep;

import java.util.HashMap;
import java.util.Map;

public class QuestService {
    private final Map<String, QuestStep> steps = new HashMap<>();

    public QuestService() {
        initSteps();
    }

    private void initSteps() {
        // Стартовый шаг
        steps.put("start", new QuestStep(
                "start",
                "Ты потерял память. Принять вызов НЛО?",
                "Принять вызов",
                "Отклонить вызов",
                "bridge",
                "lose1"
        ));

        // Второй шаг
        steps.put("bridge", new QuestStep(
                "bridge",
                "Ты принял вызов. Поднимаешься на мостик к капитану?",
                "Подняться на мостик",
                "Отказаться",
                "captain",
                "lose2"
        ));

        // Третий шаг
        steps.put("captain", new QuestStep(
                "captain",
                "Ты поднялся на мостик. Ты кто?",
                "Рассказать правду о себе",
                "Солгать о себе",
                "win",
                "lose3"
        ));

        // Победа
        steps.put("win", new QuestStep(
                "win",
                "Победа!",
                null,
                null,
                null,
                null
        ));

        // Поражение
        steps.put("lose1", new QuestStep(
                "lose1",
                "Поражение!",
                null,
                null,
                null,
                null
        ));

        // Ты не пошел на переговоры
        steps.put("lose2", new QuestStep(
                "lose2",
                "Ты не пошел на переговоры. Поражение!",
                null,
                null,
                null,
                null
        ));

        // Твоя ложь разоблачена
        steps.put("lose3", new QuestStep(
                "lose3",
                "Твоя ложь разоблачена. Поражение!",
                null,
                null,
                null,
                null
        ));

    }

    public QuestStep getStep(String id) {
        return steps.get(id);
    }

    public boolean isFinalStep(String stepId) {
        QuestStep step = steps.get(stepId);
        return step != null && step.getOption1() == null;
    }

}
