package com.javarush.example;

import java.sql.*;

public class TypeMappingDemo {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String password = "sakila";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // Создадим тестовую таблицу с разными типами данных через .executeUpdate()
            statement.executeUpdate("CREATE TEMPORARY TABLE temp_types (" +
                    "id INT PRIMARY KEY, " +
                    "price DECIMAL(5,2), " +
                    "name VARCHAR(20), " +
                    "created_date DATE, " +
                    "created_time TIME, " +
                    "updated TIMESTAMP)");

            // Вставляем тестовые данные через .executeUpdate()
            statement.executeUpdate("INSERT INTO temp_types VALUES " +
                    "(1, 19.99, 'Tovar A', '2025-12-26', '22:13:00', '2025-12-26 22:13:00'), " +
                    "(2, 20.01, 'Tovar B', '2025-12-26', '22:14:00', '2025-12-26 22:14:00')"
            );

            // Читаем данные разными способами .executeQuery()
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM temp_types")) {

                while(resultSet.next()) {
                    //
                    System.out.println(" - getInt():" + resultSet.getInt("id"));
                    System.out.println(" - getInt():" + resultSet.getString("price"));
                    System.out.println(" - getFloat():" + resultSet.getFloat("price"));
                    System.out.println(" - getDouble():" + resultSet.getDouble("price"));

                    System.out.println(" - getString():" + resultSet.getString("name"));

                    System.out.println(" - getDate():" + resultSet.getDate("created_date"));
                    System.out.println(" - getTime():" + resultSet.getTime("created_time"));

                    System.out.println(" - getTimestamp():" + resultSet.getTimestamp("updated"));

                }

                statement.executeUpdate("DROP TEMPORARY TABLE temp_types");

            }
        }

    }



}
