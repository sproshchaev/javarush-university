package com.javarush.morse.model;

public class ProcessingResult {

    private final boolean success;
    private final String message;
    private final String inputPreview;
    private final String outputPreview;


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
