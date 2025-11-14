package com.javarush.example.slide04state;

// Контекст - документ, который может менять состояние
class Document {
    private State state;

    public Document() {
        // Начальное состояние - Черновик
        this.state = new DraftState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void publish() {
        state.publish(this);
    }

    public void getStatus() {
        state.getStatus();
    }
}

// Интерфейс состояния
interface State {
    void publish(Document document);
    void getStatus();
}

// Конкретное состояние - Черновик
class DraftState implements State {
    @Override
    public void publish(Document document) {
        System.out.println("Публикуем черновик... Переводим в состояние Модерация");
        document.setState(new ModerationState());
    }

    @Override
    public void getStatus() {
        System.out.println("Статус: Черновик");
    }
}

// Конкретное состояние - На модерации
class ModerationState implements State {
    @Override
    public void publish(Document document) {
        System.out.println("Модерация пройдена! Публикуем статью.");
        document.setState(new PublishedState());
    }

    @Override
    public void getStatus() {
        System.out.println("Статус: На модерации");
    }
}

// Конкретное состояние - Опубликован
class PublishedState implements State {
    @Override
    public void publish(Document document) {
        System.out.println("Статья уже опубликована! Нельзя опубликовать снова.");
    }

    @Override
    public void getStatus() {
        System.out.println("Статус: Опубликован");
    }
}

// Демонстрация работы паттерна State
public class Slide04_StatePattern {
    public static void main(String[] args) {
        Document article = new Document();

        article.getStatus(); // Черновик
        article.publish();   // Переводим в модерацию

        article.getStatus(); // На модерации
        article.publish();   // Публикуем

        article.getStatus(); // Опубликован
        article.publish();   // Пытаемся опубликовать снова
    }
}