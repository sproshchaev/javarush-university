package com.javarush.example;

import java.sql.*;

public class FirstJdbcQuery {

    public static void main(String[] args) throws SQLException {

        // Шаг 1: Параметры подключения к БД
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String password = "sakila";

        Connection connection = null; // Устанавливать соединение с БД
        Statement statement = null;   // Формировать запросы
        ResultSet resultSet = null;   // Получать ответы

        try {
            // Шаг 2: Установить соединение с БД
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Соединение установлено!");

            // Шаг 3: Создание Statement и выполнение запроса
            statement = connection.createStatement();
            String sql = "SELECT actor_id, first_name, last_name FROM actor LIMIT 5";
            resultSet = statement.executeQuery(sql);

            // Получаем метаданные о результате (класс ResultSetMetaData):
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println("Метаданные полученного результата: ");
            System.out.println(" - число колонок: " + resultSetMetaData.getColumnCount());
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.print(" - " + resultSetMetaData.getColumnName(i) + " ");
                System.out.print(" - " + resultSetMetaData.getColumnTypeName(i) + " ");
                System.out.println(" - " + resultSetMetaData.getPrecision(i) + " ");
            }

            System.out.println("\nПервые 5 актеров из БД: ");

            // Шаг 4: Обработка ResultSet
            while (resultSet.next()) {
                // 3 поля из ответа: actor_id, first_name, last_name
                long actorId = resultSet.getLong("actor_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println("id: " + actorId + ", first_name: " + firstName + ", "
                        + "last_name: " + lastName);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с БД: " + e.getMessage());
        } finally {
            // Закрываем ресурсы - в обратном порядке
            resultSet.close();
            statement.close();
            connection.close();
        }
    }

}
