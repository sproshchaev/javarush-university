package com.javarush.quest.model;

import java.util.Objects;

public class QuestStep {
    private String id;
    private String text;
    private String option1;
    private String option2;
    private String nextStepId1;
    private String nextStepId2;

    public QuestStep() {
    }

    public QuestStep(String id, String text, String option1, String option2, String nextStepId1, String nextStepId2) {
        this.id = id;
        this.text = text;
        this.option1 = option1;
        this.option2 = option2;
        this.nextStepId1 = nextStepId1;
        this.nextStepId2 = nextStepId2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getNextStepId1() {
        return nextStepId1;
    }

    public void setNextStepId1(String nextStepId1) {
        this.nextStepId1 = nextStepId1;
    }

    public String getNextStepId2() {
        return nextStepId2;
    }

    public void setNextStepId2(String nextStepId2) {
        this.nextStepId2 = nextStepId2;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        QuestStep questStep = (QuestStep) object;
        return Objects.equals(id, questStep.id) && Objects.equals(text, questStep.text) && Objects.equals(option1, questStep.option1) && Objects.equals(option2, questStep.option2) && Objects.equals(nextStepId1, questStep.nextStepId1) && Objects.equals(nextStepId2, questStep.nextStepId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, option1, option2, nextStepId1, nextStepId2);
    }

    @Override
    public String toString() {
        return "QuestStep{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", nextStepId1='" + nextStepId1 + '\'' +
                ", nextStepId2='" + nextStepId2 + '\'' +
                '}';
    }
}
