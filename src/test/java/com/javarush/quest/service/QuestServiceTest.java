package com.javarush.quest.service;

import com.javarush.quest.model.QuestStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class QuestServiceTest {
    private QuestService questService;

    @BeforeEach
    void setUp() {
        questService = new QuestService();
    }

    @Test
    @DisplayName("Тест получения начального шага")
    void testGetStartStep() {
        QuestStep step = questService.getStep("start");

        assertNotNull(step, "Шаг 'start' не должен быть null");
        assertEquals("start", step.getId(), "ID шага должен быть 'start'");
        assertEquals("Ты потерял память. Принять вызов НЛО?", step.getText());
        assertEquals("Принять вызов", step.getOption1());
        assertEquals("Отклонить вызов", step.getOption2());
        assertEquals("bridge", step.getNextStepId1());
        assertEquals("lose1", step.getNextStepId2());
    }

    @Test
    @DisplayName("Тест получения шага с победой")
    void testGetWinStep() {
        QuestStep step = questService.getStep("win");

        assertNotNull(step, "Шаг 'win' не должен быть null");
        assertTrue(step.getText().contains("Победа"), "Текст должен содержать 'ПОБЕДА'");
        assertNull(step.getOption1(), "У финального шага не должно быть вариантов ответа");
        assertNull(step.getOption2(), "У финального шага не должно быть вариантов ответа");
    }

    @Test
    @DisplayName("Тест получения несуществующего шага")
    void testGetNonExistentStep() {
        QuestStep step = questService.getStep("non-existent");
        assertNull(step, "Несуществующий шаг должен возвращать null");
    }

    @Test
    @DisplayName("Тест проверки финального шага")
    void testIsFinalStep() {
        assertTrue(questService.isFinalStep("win"), "'win' должен быть финальным шагом");
        assertTrue(questService.isFinalStep("lose1"), "'lose1' должен быть финальным шагом");
        assertTrue(questService.isFinalStep("lose2"), "'lose2' должен быть финальным шагом");
        assertTrue(questService.isFinalStep("lose3"), "'lose3' должен быть финальным шагом");

        assertFalse(questService.isFinalStep("start"), "'start' не должен быть финальным шагом");
        assertFalse(questService.isFinalStep("bridge"), "'bridge' не должен быть финальным шагом");
        assertFalse(questService.isFinalStep("captain"), "'captain' не должен быть финальным шагом");

        assertFalse(questService.isFinalStep("non-existent"),
                "Несуществующий шаг не должен считаться финальным");
    }

    @Test
    @DisplayName("Тест логики ветвления")
    void testQuestBranching() {
        QuestStep start = questService.getStep("start");
        QuestStep bridge = questService.getStep("bridge");
        QuestStep captain = questService.getStep("captain");
        QuestStep win = questService.getStep("win");

        assertNotNull(start);
        assertNotNull(bridge);
        assertNotNull(captain);
        assertNotNull(win);

        // Проверяем корректность связей
        assertEquals("bridge", start.getNextStepId1());
        assertEquals("lose1", start.getNextStepId2());

        assertEquals("captain", bridge.getNextStepId1());
        assertEquals("lose2", bridge.getNextStepId2());

        assertEquals("win", captain.getNextStepId1());
        assertEquals("lose3", captain.getNextStepId2());
    }
}