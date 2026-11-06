package com.javarush.demo.library;

/**
 * Только то что нужно библиотеке
 */
public class LibraryReader {

    private String name;
    private int ticketNumber;
    private int booksOnHand;

    public LibraryReader(String name, int ticketNumber) {
        this.name = name;
        this.ticketNumber = ticketNumber;
    }

    public void takeBook() {
        booksOnHand++;
    }

    public String info() {
        return "Читатель " + name + ", билет " + ticketNumber
                + ", книг на руках: " + booksOnHand;
    }

}
