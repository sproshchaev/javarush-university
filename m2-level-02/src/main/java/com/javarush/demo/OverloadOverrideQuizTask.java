package com.javarush.demo;

public class OverloadOverrideQuizTask {
    public static void main(String[] args) {
        Object text = "привет";
        Speaker speaker = new LoudSpeaker();

        speaker.say(text);       // 1
        speaker.say("привет");   // 2
        speaker.say((String) text);   // 3
    }
}

class Speaker {
    void say(Object value) {
        System.out.println("Speaker.say(Object): " + value);
    }

    void say(String value) {
        System.out.println("Speaker.say(String): " + value);
    }
}

class LoudSpeaker extends Speaker {
    @Override
    void say(String value) {
        System.out.println("LoudSpeaker.say(String): " + value.toUpperCase());
    }
}
