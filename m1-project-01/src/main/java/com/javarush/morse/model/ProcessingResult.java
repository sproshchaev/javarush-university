package com.javarush.morse.model;

public class ProcessingResult {

    private final boolean success;      // Успешное выполнение операции
    private final String message;       // Сообщение для пользователя
    private final String inputPreview;  // Что на входе
    private final String outputPreview; // Что на выходе


    public ProcessingResult(boolean success, String message, String inputPreview, String outputPreview) {
        this.success = success;
        this.message = message;
        this.inputPreview = inputPreview;
        this.outputPreview = outputPreview;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getInputPreview() {
        return inputPreview;
    }

    public String getOutputPreview() {
        return outputPreview;
    }
}
