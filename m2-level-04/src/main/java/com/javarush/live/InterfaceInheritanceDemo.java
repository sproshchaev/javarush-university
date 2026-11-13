package com.javarush.live;

public class InterfaceInheritanceDemo {

    public static void main(String[] args) {
        Notebook notebook = new Notebook();
        notebook.write("Купить хлебушка");

        System.out.println("Прочитано: " + notebook.read());
        System.out.println("Листов: " + notebook.sheets());
    }
}

    interface CanRead {
        String read();
    }

    interface CanWrite {
        void write(String text);
    }

    // Интерфейс с двумя интерфейсами-родителями
    interface Editable extends CanRead, CanWrite {

    }

    class Paper {
        int sheets() {
            return 48;
        }
    }

    class Notebook extends Paper implements Editable {

        private String text = "";

        @Override
        public String read() {
            return text;
        }

        @Override
        public void write(String text) {
            this.text = text;
        }
    }

